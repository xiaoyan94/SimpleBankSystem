package com.hpejn.test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hpejn.util.BaseDao;

public class SelectDateTest extends BaseDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// String sql = "SELECT * FROM business WHERE businessNumber=?";
		String sql = "SELECT * FROM business WHERE businessType=?";
		// Object[] p = new Object[]{"29"};
		Object[] p = new Object[] { "测试" };
		try {
			ResultSet resultSet = new SelectDateTest().util
					.executeQuery(sql, p);
			while (resultSet.next()) {
				String date = resultSet.getString("businessDate");
				System.out.println(date + "\t\t");
				
				Time time = resultSet.getTime("businessDate");
				
				/**
				 * TimeStamp 
				 */
				System.out
						.println(resultSet.getTimestamp("businessDate")
								+ "\t"
								+ new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss")
										.format(resultSet
												.getTimestamp("businessDate")));

				//

				Date date2 = resultSet.getDate("businessDate");
				System.out.println(date2
						+ "\t\t"
						+ new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss")
								.format(date2));

				java.sql.Date date3 = resultSet.getDate("businessDate");
				System.out.println(date3
						+ "\t\t"
						+ new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss")
								.format(date3));

				System.out.println("______-_____________-_");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
