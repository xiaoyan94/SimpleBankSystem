package com.hpejn.service;

import com.hpejn.entity.Business;
import com.hpejn.entity.Customer;

/**
 * 根据客户对像，更新数据库中客户的余额信息
 * 
 * @author yan
 */
public interface CustomerService {
	
	/**
	 * customer窗口
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	public boolean customerManageWindow(String userName, String userPwd);
	/**
	 * 取款 根据客户对像，更新数据库中客户的余额信息
	 * 
	 * @param cust
	 * @param money
	 * @return
	 */
	public Business moneyOut(Customer cust, double money);

	/**
	 * 用户登录
	 * 
	 * @param custNumber
	 * @param custPwd
	 * @return
	 */
	public Customer login(String custNumber, String custPwd);

	/**
	 * 查询余额
	 * 
	 * @return
	 */
	public double getBalance();

	/**
	 * 存款
	 * 
	 * @param money
	 * @return
	 */
	public Business moneyIn(Customer cust, double money);

	/**
	 * 转账
	 * 
	 * @param destCustNumber
	 *            对方账户
	 * @param money
	 * @return 成功返回一个流水，否则返回null
	 */
	public Business transferMoney(Customer customer, String destCustNumber,
			double money);

	/**
	 * 修改密码
	 * 
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	public boolean changePwd(Customer customer, String oldPwd, String newPwd);

	/**
	 * 打印账单
	 * @param customer
	 * @param yy_MM
	 */
	public void selectMonthBills(Customer customer,String yy_MM);
}
