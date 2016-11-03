package com.hpejn.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.hpejn.dao.CustomerDao;
import com.hpejn.dao.impl.BusinessDaoImpl;
import com.hpejn.dao.impl.CustomerDaoImpl;
import com.hpejn.entity.Business;
import com.hpejn.entity.Customer;
import com.hpejn.service.CustomerService;
import com.hpejn.util.InputCheck;
import com.hpejn.util.Jdbcutil;

public class CustomerServiceImpl implements CustomerService {
	private BusinessDaoImpl businessDao = new BusinessDaoImpl();
	private CustomerDao customerDao = new CustomerDaoImpl();

	@Override
	public boolean customerManageWindow(String userName, String userPwd) {

		Customer customer = null;
		Business business = null;
		Object[] obj = null;
		customer = this.login(userName, userPwd);
		if (customer != null) {
			System.out.println("欢迎您！" + customer.getCustName());
		} else {
			System.out.println("登录失败！请检查用户名和密码！");
			return false;
		}

		// login success
		while (true) {
			System.out.println("             银行系统【顾客】");
			System.out.println("************************************");
			System.out.println("  1.存款 2.取款 3.查询余额 4.转账 ");
			System.out.println("   5.修改密码 6.查询月账单 7.退出 ");
			System.out.println("************************************");
			String selectItem = InputCheck.checkAndGetInput("选项", "^[1-7]$");
			switch (selectItem) {
			case "1":
				obj = moneyInAndOut(customer, business, "存款");
				customer = (Customer) obj[0];
				business = (Business) obj[1];
				break;
			case "2":
				obj = moneyInAndOut(customer, business, "取款");
				customer = (Customer) obj[0];
				business = (Business) obj[1];
				break;
			case "3":
				System.out.println("您的账户余额为：" + customer.getCustMoney() + "元。");
				break;
			case "4":
				String transferMoneyString = InputCheck.checkAndGetInput(
						"转账金额", "^\\d{1,7}\\.?\\d{0,2}$");
				double transferMoney = Double.parseDouble(transferMoneyString);

				String destCustNumber = InputCheck.checkAndGetInput("对方银行账户",
						"^62684\\d{14}$");
				business = transferMoney(customer, destCustNumber,
						transferMoney);
				if (business != null) {
					customer.setCustMoney(customer.getCustMoney()
							+ business.getBusinessMoney());
					System.out.println("操作成功：" + business.getBusinessType()
							+ business.getBusinessMoney() + "元");
				} else {
					System.out.println("操作失败！请重新检查输入！");
				}
				break;
			case "5":
				String oldPwd = InputCheck.checkAndGetInput("当前密码", "^\\d{6}$");
				String newPwd = InputCheck.checkAndGetInput("新密码", "^\\d{6}$");
				if (changePwd(customer, oldPwd, newPwd)) {
					System.out.println("密码修改成功！");
				} else {
					System.out.println("修改失败，密码错误！");
				}
				break;

			case "6":
				String yy = InputCheck.checkAndGetInput("年份", "^\\d{4}$");
				String MM = InputCheck.checkAndGetInput("月份",
						"^(\\d)|([1][012])$");
				if (MM.length() == 1) {
					MM = "0" + MM;
				}
				selectMonthBills(customer, yy + "-" + MM);
				System.out.println("功能尚未完善～～");
				break;

			case "7":
				return true;
			default:
				break;
			}

		}
	}

	/**
	 * 调用CusomerDao层方法修改客户余额，调用businessDao层方法记录业务流水
	 */
	public Business moneyOut(Customer customer, double money) {
		String custNumber = customer.getCustNumber();
		Business business = null;
		try {
			Jdbcutil.beginTran();
			if (customer.getCustMoney() < money) {
				System.out.println("余额不足！");
				return null;
			}
			int affectedRow = customerDao.changeMoney(custNumber, 0 - money);
			if (affectedRow > 0) {
				business = new Business(custNumber, "取款", 0 - money, new Date());
				businessDao.insertBusiness(business);
				Jdbcutil.commitTran();
			}
		} catch (SQLException e1) {
			try {
				Jdbcutil.rollBack();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			e1.printStackTrace();
		}

		System.out.println("取款中…………");
		return business;
	}

	@Override
	public Customer login(String custNumber, String custPwd) {
		Customer customer = null;
		try {
			Jdbcutil.beginTran();
			customer = customerDao.login(custNumber, custPwd);
			Jdbcutil.commitTran();
		} catch (SQLException e) {
			try {
				Jdbcutil.rollBack();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public Business moneyIn(Customer cust, double money) {
		String custNumber = cust.getCustNumber();
		Business business = null;
		try {
			Jdbcutil.beginTran();
			int affectedRow = customerDao.changeMoney(custNumber, money);
			if (affectedRow > 0) {
				business = new Business(custNumber, "存款", money, new Date());
				businessDao.insertBusiness(business);
			}
			Jdbcutil.commitTran();
		} catch (SQLException e1) {
			try {
				Jdbcutil.rollBack();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			e1.printStackTrace();
		}
		System.out.println("存款中…………");
		return business;
	}

	@Override
	public Business transferMoney(Customer customer, String destCustNumber,
			double money) {

		Business businessOfCustomer = null;
		Business businessOfDestCustomer = null;

		if (customer.getCustMoney() < money) {
			System.out.println("余额不足！");
			return null;
		}

		try {
			Jdbcutil.beginTran();
			Customer destCustomer = null;
			int destAffectedRow = 0;
			destCustomer = customerDao.selectCustomer(destCustNumber);
			if (destCustomer == null) {
				return null;// 没找到对方账户
			}
			destAffectedRow = customerDao.changeMoney(destCustNumber, money);

			String custNumber = customer.getCustNumber();
			int custAffectedRow = customerDao
					.changeMoney(custNumber, 0 - money);

			if (custAffectedRow > 0 && destAffectedRow > 0) {
				businessOfCustomer = new Business(custNumber, "转账", 0 - money,
						new Date());
				businessOfDestCustomer = new Business(destCustNumber, "转账",
						money, new Date());
				if (businessDao.insertBusiness(businessOfCustomer) > 0
						&& businessDao.insertBusiness(businessOfDestCustomer) > 0) {
					Jdbcutil.commitTran();
				}
			}
		} catch (SQLException e2) {
			try {
				Jdbcutil.rollBack();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			e2.printStackTrace();
		}
		System.out.println("转账中…………");
		return businessOfCustomer;
	}

	@Override
	public boolean changePwd(Customer cust, String oldPwd, String newPwd) {
		// TODO custOldPwd空指针异常处理！！
		String custOldPwd = cust.getCustPwd();
		if (custOldPwd.equals(oldPwd)) {
			try {
				Jdbcutil.beginTran();
				customerDao.changePwd(cust.getCustNumber(), newPwd);
				Jdbcutil.commitTran();
			} catch (SQLException e) {
				try {
					Jdbcutil.rollBack();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	@Override
	public double getBalance() {

		return 0;
	}

	public Object[] moneyInAndOut(Customer customer, Business business,
			String businessType) {
		String inMoneyString = InputCheck.checkAndGetInput(businessType + "金额",
				"^\\d{1,7}\\.?\\d{0,2}$");
		double money = Double.parseDouble(inMoneyString);
		switch (businessType) {
		case "存款":
			business = moneyIn(customer, money);
			break;
		case "取款":
			business = moneyOut(customer, money);
			break;
		default:
			break;
		}
		if (business != null) {
			customer.setCustMoney(customer.getCustMoney()
					+ business.getBusinessMoney());
			System.out.println("操作成功：" + business.getBusinessType()
					+ business.getBusinessMoney() + "元");
		}

		return new Object[] { customer, business };
	}

	@Override
	public void selectMonthBills(Customer customer, String date) {

		try {
			Jdbcutil.beginTran();
			List<Business> businesses = businessDao.selectBussinessByDate(
					customer, date);
			System.out.println("-------------------------------------"
					+ date.substring(0, 4) + "年" + date.substring(5)
					+ "月的账单------------------------------------------");
			System.out.println("业务流水号：\t银行账号：\t\t业务类型：\t业务金额：\t业务日期： ");
			for (Business business : businesses) {
				System.out.println(business);

			}
			System.out
					.println("---------------------------------------------------------------------------------------------");
			Jdbcutil.commitTran();
		} catch (SQLException e) {
			try {
				Jdbcutil.rollBack();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

}
