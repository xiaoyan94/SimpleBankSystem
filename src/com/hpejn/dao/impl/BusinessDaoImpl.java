package com.hpejn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hpejn.dao.BusinessDao;
import com.hpejn.entity.Business;
import com.hpejn.entity.Customer;
import com.hpejn.util.BaseDao;

public class BusinessDaoImpl extends BaseDao implements BusinessDao {

	@Override
	public int insertBusiness(Business business) throws SQLException {
		String sql = "INSERT INTO business(custNumber,businessType,businessMoney,businessDate) values(?,?,?,?)";
		Object[] params = new Object[] { business.getCustNumber(),
				business.getBusinessType(), business.getBusinessMoney(),
				business.getBusinessDate() };
		return this.util.executeUpdate(sql, params);
	}

	@Override
	public List<Business> selectBussinessByDate(Customer customer, String date)
			throws SQLException {
		String sql = "SELECT * FROM business WHERE custNumber=? AND businessDate like concat('%',?,'%')";
		Object[] params = new Object[] { customer.getCustNumber(), date };
		ResultSet resultSet = this.util.executeQuery(sql, params);
		List<Business> businesses = new ArrayList<Business>();
		while (resultSet.next()) {
			String custNumber = resultSet.getString("custNumber");
			String businessType = resultSet.getString("businessType");
			double businessMoney = resultSet.getDouble("businessMoney");
			Date businessDate = resultSet.getTimestamp("businessDate");
			Business business = new Business(custNumber, businessType,
					businessMoney, businessDate);
			business.setBusinessNumber(resultSet.getInt("businessNumber"));
			businesses.add(business);
		}
		return businesses;
	}
}
