package com.hpejn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.hpejn.dao.AdministratorDao;
import com.hpejn.entity.Administrator;
import com.hpejn.util.BaseDao;


public class AdministratorDaoImpl extends BaseDao implements AdministratorDao {

	public Administrator login(String adminName, String adminPwd)
			throws SQLException {
		String sql = "SELECT adminName From administrator WHERE adminName=? AND adminPwd=?";
		Object[] params = new Object[] { adminName, adminPwd };

		ResultSet resultSet = this.util.executeQuery(sql, params);

		Administrator administrator = null;

		if (resultSet.next()) {
			administrator = new Administrator();
			administrator.setAdminName(resultSet.getString("adminName"));
		}
		return administrator;
	}

}
