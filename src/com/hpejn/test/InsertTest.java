package com.hpejn.test;
import java.sql.SQLException;

import com.hpejn.util.BaseDao;


public class InsertTest extends BaseDao{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO business(custNumber,businessType,businessMoney,businessDate) values(?,?,?,?)";
		Object[] params = new Object[]{ "1234567890123456789",
				"取款", 100,"2016-11-30 22:39:49" };
		try {
			for (Object object : params) {
				System.out.println(object);
			}
			int rsResultSet = new InsertTest().util.executeUpdate(sql, params);//(sql, params);
			System.out.println(rsResultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
