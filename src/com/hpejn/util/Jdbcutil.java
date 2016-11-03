package com.hpejn.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Jdbcutil {
	private String jdbcDriver = "com.mysql.jdbc.Driver"; // 数据库驱动程序
	private String strCon = "jdbc:mysql://localhost:3306/bankSys";
	private String username = "root";
	private String password = "root";

	private static Jdbcutil util = null;// 静态成员变量，支持单态模式

	private static Connection conn = null;
	private PreparedStatement pstm = null;

	public static Jdbcutil createInstance() throws ClassNotFoundException {
		if (util == null) {
			util = new Jdbcutil();
			util.initDB();
		}
		return util;
	}

	public void initDB() throws ClassNotFoundException {
		Class.forName(jdbcDriver);
	}

	/**
	 * 连接数据库
	 * 
	 * @throws SQLException
	 */
	public void connectDB() throws SQLException {
		conn = DriverManager.getConnection(strCon, username, password);
	}

	/**
	 * 开启事务
	 * 
	 * @throws SQLException
	 */

	public static void beginTran() throws SQLException {
		conn.setAutoCommit(false);
	}

	/**
	 * 回滚事务
	 * 
	 * @throws SQLException
	 */
	public static void rollBack() throws SQLException {
		conn.rollback();
	}

	/**
	 * 提交事务
	 * 
	 * @throws SQLException
	 */
	public static void commitTran() throws SQLException {
		conn.commit();
	}

	/**
	 * 关闭连接
	 * 
	 * @throws SQLException
	 */
	public void closeConn() throws SQLException {
		System.out.println("close ......");
		if (pstm != null) {
			pstm.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	/**
	 * 设置PrepareStatement对象中的Sql语句的参数
	 * 
	 * @throws SQLException
	 */

	private void setPrepareStatementParams(String sql, Object[] params)
			throws SQLException {
		pstm = conn.prepareStatement(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				pstm.setObject(i + 1, params[i]);
			}
		}
	}

	/**
	 * 执行查询
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数列表
	 * @return 返回ResultSet类型的查询结果
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String sql, Object[] params)
			throws SQLException { // 执行查询数据库接口
		ResultSet rs = null;
		util.setPrepareStatementParams(sql, params); // 填充参数
		rs = pstm.executeQuery(); // 执行查询操作
		return rs;
	}

	public int executeUpdate(String sql, Object[] params) throws SQLException // 执行无返回数据的数据查询，返回值是被改变的书库的数据库项数
	{
		int result = -1;
		util.setPrepareStatementParams(sql, params); // 填充参数
		pstm.executeUpdate(); // 执行更新
		result = 1;
		return result;
	}
}