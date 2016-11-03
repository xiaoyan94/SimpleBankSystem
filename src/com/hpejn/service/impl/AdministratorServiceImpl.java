package com.hpejn.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.hpejn.dao.AdministratorDao;
import com.hpejn.dao.CustomerDao;
import com.hpejn.dao.impl.AdministratorDaoImpl;
import com.hpejn.dao.impl.CustomerDaoImpl;
import com.hpejn.entity.Administrator;
import com.hpejn.entity.Customer;
import com.hpejn.service.AdministratorService;
import com.hpejn.util.InputCheck;
import com.hpejn.util.Jdbcutil;

public class AdministratorServiceImpl implements AdministratorService {

	private static final Scanner scanner = new Scanner(System.in);
	static AdministratorDao administratorDao = new AdministratorDaoImpl();
	static CustomerDao customerDao = new CustomerDaoImpl();

	// 管理员服务窗口
	@Override
	public boolean adminManageWindow(String userName, String userPwd) {

		Administrator admin = null;
		try {
			Jdbcutil.beginTran();
			admin = this.login(userName, userPwd);
			Jdbcutil.commitTran();
		} catch (SQLException e) {
			try {
				Jdbcutil.rollBack();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		if (admin != null) {
			System.out.println("欢迎您！" + admin.getAdminName());
		} else {
			System.out.println("登录失败！请检查用户名和密码！");
			return false;
		}

		while (true) {
			System.out.println("             银行系统【管理员】");
			System.out.println("************************************");
			System.out.println("   1.添加顾客  2.计算储蓄总额  3.退出");
			System.out.println("************************************");
			String selectItem = InputCheck.checkAndGetInput("选项", "^[123]$");
			switch (selectItem) {
			case "1":
				String custName = InputCheck.checkAndGetInput("顾客姓名",
						"[a-zA-Z\u4e00-\u9fa5]{1,20}$");
				String custCard = InputCheck.checkAndGetInput("顾客身份证号",
						"[1-9]\\d{16}(\\d|x|X)");
				String custPwd = InputCheck.checkAndGetInput("初始密码", "\\d{6}");
				double custMoney = Double
						.parseDouble(InputCheck.checkAndGetInput("开户金额",
								"^[0-9]\\d{0,6}\\.?\\d{0,2}$"));

				System.out.println("输入完毕，是否添加？（按c取消添加）");
				String isCommit = scanner.nextLine();
				if (isCommit.equals("c")) {
					System.out.println("取消操作…………");
					break;
				} else {
					Date custDate = new Date();

					String custNumber = "62684"
							+ new SimpleDateFormat("yyyyMMddHHmmss")
									.format(custDate);// 银行卡号
					boolean isAdd = this
							.addCustomer(new Customer(custName, custNumber,
									custPwd, custCard, custMoney, custDate));
					if (isAdd) {
						System.out.println("添加成功！");
					} else {
						System.out.println("添加失败！");
					}
					break;
				}
			case "2":
				this.selectSumaryMoney();
				break;
			case "3":
				System.out.println("退出系统…………");
				return true;
			default:
				break;
			}
			System.out.println("返回管理员服务窗口…………");

		}
	}

	// 管理员登陆
	@Override
	public Administrator login(String adminName, String adminPwd)
			throws SQLException {
		Administrator administrator = null;
		administrator = administratorDao.login(adminName, adminPwd);
		return administrator;
	}

	// 总金额
	@Override
	public double selectSumaryMoney() {
		double sum_money = 0;
		try {
			Jdbcutil.beginTran();
			sum_money = customerDao.selectSumaryMoney();
			Jdbcutil.commitTran();
		} catch (SQLException e) {
			try {
				Jdbcutil.rollBack();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		System.out.println("当前储蓄总额：" + sum_money + "元。");
		return sum_money;
	}

	// 添加顾客
	@Override
	public boolean addCustomer(Customer customer) {

		boolean isAdd = false;
		try {
			Jdbcutil.beginTran();
			isAdd = customerDao.insertCustomer(customer);
			Jdbcutil.commitTran();
		} catch (SQLException e) {
			try {
				Jdbcutil.rollBack();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return isAdd;
	}

}
