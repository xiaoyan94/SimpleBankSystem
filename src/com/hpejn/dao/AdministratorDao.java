package com.hpejn.dao;

import java.sql.SQLException;

import com.hpejn.entity.Administrator;


public interface AdministratorDao {
	/**
	 * 管理员登陆
	 * @param adminName 管理员用户名
	 * @param adminPwd 管理员密码
	 * @return 登录成功返回Administrator对象，否则返回null
	 * @throws SQLException
	 */
	public Administrator login(String adminName, String adminPwd)
			throws SQLException;


	


}
