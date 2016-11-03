package com.hpejn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.hpejn.dao.BusinessDao;
import com.hpejn.dao.CustomerDao;
import com.hpejn.entity.Customer;
import com.hpejn.util.BaseDao;

public class CustomerDaoImpl extends BaseDao implements CustomerDao {

	static BusinessDao businessDao = new BusinessDaoImpl();

	/**
	 * 编写sql语句，调用jdbcutil修改数据库更新数据操作
	 * 
	 * @throws SQLException
	 */
	public int changeMoney(String custNumber, double custMoney)
			throws SQLException {
		String sql = "UPDATE customer SET custMoney=custMoney+? WHERE custNumber=?";
		Object[] params = new Object[] { custMoney, custNumber };
		return this.util.executeUpdate(sql, params);
	}

	private Customer newCustomerByResultSet(ResultSet resultSet)
			throws SQLException {
		return new Customer(resultSet.getString("custName"),
				resultSet.getString("custNumber"),
				resultSet.getString("custPwd"),
				resultSet.getString("custCard"),
				resultSet.getDouble("custMoney"), resultSet.getDate("custDate"));
	}

	@Override
	public Customer login(String custNumber, String custPwd)
			throws SQLException {
		String sql = "SELECT * From customer WHERE custNumber=? AND custPwd=?";
		Object[] params = new Object[] { custNumber, custPwd };

		ResultSet resultSet = this.util.executeQuery(sql, params);

		Customer customer = null;

		if (resultSet.next()) {
			customer = newCustomerByResultSet(resultSet);
		}
		return customer;
	}

	@Override
	public Customer selectCustomer(String custNumber) throws SQLException {
		String sql = "SELECT * FROM customer WHERE custNumber=?";
		
		Object[] params = new Object[] { custNumber };
		ResultSet resultSet = this.util.executeQuery(sql, params);
		
		Customer customer = null;
		if (resultSet.next()) {
			customer = newCustomerByResultSet(resultSet);
		}
		return customer;
	}

	@Override
	public int changePwd(String custNumber, String newPwd) throws SQLException {
		String sql = "UPDATE customer SET custPwd=? WHERE custNumber=?";
		Object[] params = new Object[] { newPwd, custNumber };
		int affectedRow = 0;
		affectedRow = this.util.executeUpdate(sql, params);

		return affectedRow;
	}

	@Override
	public boolean insertCustomer(Customer customer) throws SQLException {

		Object[] params = new Object[] { customer.getCustNumber(),
				customer.getCustName(), customer.getCustPwd(),
				customer.getCustCard(), customer.getCustMoney(),
				customer.getCustDate() };
		String sql = "insert into customer (custNumber,custName,custPwd,custCard,custMoney,custDate) "
				+ "values(?,?,?,?,?,?)";
		return this.util.executeUpdate(sql, params) > 0;
	}

	@Override
	public double selectSumaryMoney() throws SQLException {

		String sql = "select sum(custMoney) as sumary_money from customer";
		ResultSet rs = this.util.executeQuery(sql, null);
		if (rs.next()) {
			return rs.getDouble("sumary_money");
		}

		return 0;
	}

}
