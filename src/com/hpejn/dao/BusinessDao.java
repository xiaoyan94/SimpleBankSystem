package com.hpejn.dao;

import java.sql.SQLException;
import java.util.List;

import com.hpejn.entity.Business;
import com.hpejn.entity.Customer;

public interface BusinessDao {
	/**
	 * 把业务流水操作写入数据库中的business表中
	 * 
	 * @param business
	 * @return
	 * @throws SQLException
	 */
	public int insertBusiness(Business business) throws SQLException;

	/**
	 * 按时间查询流水表
	 * @param customer
	 * @param date
	 * @return 返回Business集合
	 * @throws SQLException
	 */
	public List<Business> selectBussinessByDate(Customer customer,String date) throws SQLException;
}
