package com.hpejn.test;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hpejn.util.BaseDao;
import com.hpejn.util.Jdbcutil;

public class InsertDatetimeTest extends BaseDao {

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		new InsertDatetimeTest().util
				.executeUpdate(
						"INSERT INTO business(custNumber,businessType,businessMoney,businessDate) values(?,?,?,?)",
						new Object[] { "1234567890123456789", "测试", 400,
								new Timestamp(new Date().getTime()) });//TimeStamp 时分秒OK
		
		new InsertDatetimeTest().util
		.executeUpdate(
				"INSERT INTO testt values(?,?)",
				new Object[] {new Date() ,new Date()});
	}

}
