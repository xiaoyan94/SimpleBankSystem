package com.hpejn.service;

import java.sql.SQLException;

import com.hpejn.entity.Administrator;
import com.hpejn.entity.Customer;

public interface AdministratorService {
	/**
	 * 管理员登陆
	 * 
	 * @param adminName
	 *            管理员用户名
	 * @param adminPwd
	 *            管理员密码
	 * @return 登录成功返回Administrator对象，否则返回null
	 * @throws SQLException
	 */
	public Administrator login(String adminName, String adminPwd)
			throws SQLException;

	/**
	 * 管理员窗口
	 * 
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	public boolean adminManageWindow(String userName, String userPwd);

	/**
	 * 添加顾客
	 * 
	 * @param customer
	 * @return 添加成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean addCustomer(Customer customer);

	/**
	 * 查询储蓄总金额
	 * 
	 * @return 返回总金额
	 * @throws SQLException
	 */
	public double selectSumaryMoney();

}
