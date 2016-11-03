package com.hpejn.dao;

import java.sql.SQLException;

import com.hpejn.entity.Customer;

public interface CustomerDao {
	/**
	 * 根据用户账号修改数据库中用户余额信息
	 * 
	 * @param custNumber
	 * @param money
	 * @return
	 * @throws SQLException 
	 */
	public int changeMoney(String custNumber, double money) throws SQLException;

	/**
	 * 调用Jdbcutils方法实现 用户登录接口
	 * 
	 * @param custNumber
	 * @param custPwd
	 * @return Customer对象
	 * @throws SQLException
	 */
	public Customer login(String custNumber, String custPwd)
			throws SQLException;

	/**
	 * 根据给出的客户卡号返回一个Customer对象
	 * 
	 * @param custNumber
	 * @return
	 * @throws SQLException
	 */
	public Customer selectCustomer(String custNumber) throws SQLException;

	/**
	 * 修改数据库中的密码
	 * 
	 * @param custNumber
	 * @param newPwd
	 * @return
	 * @throws SQLException 
	 */
	public int changePwd(String custNumber, String newPwd) throws SQLException;

	/**
	 * 添加顾客
	 * 
	 * @param custName
	 *            顾客姓名
	 * @param custPwd
	 *            初始密码
	 * @param custCard
	 *            顾客身份证
	 * @param custMoney
	 *            开户金额
	 * @return 添加成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean insertCustomer(Customer customer) throws SQLException;

	/**
	 * 查询储蓄总金额
	 * 
	 * @return 返回总金额
	 * @throws SQLException
	 */
	public double selectSumaryMoney() throws SQLException;
}
