package com.hpejn.test;



import java.sql.*;



public class AdministratorDAO {

	private String sql;
	private Connection con;
	private ResultSet rs;
	private PreparedStatement ps;

	public AdministratorDAO() {
		sql = null;
		con = null;
		rs = null;
		ps = null;
	}

	/**
	 * 添加顾客
	 * @param custNumber
	 * @param custName
	 * @param custPwd
	 * @param custCard
	 * @param custMoney
	 * @param custDate
	 * @return
	 */
	public int addCustomer(String custNumber, String custName, String custPwd,
			String custCard, double custMoney, Date custDate) {

		sql = "insert into customer (custNumber,custName,custPwd,custCard,custMoney,custDate) "
				+ "values(?,?,?,?,?,?)";

		System.out.println(sql);

		try {

			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, custNumber);
			ps.setString(2, custName);
			ps.setString(3, custPwd);
			ps.setString(4, custCard);
			ps.setDouble(5, custMoney);
//			ps.setDate(6, custDate);
			return ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(con);

		}
		return 0;

	}
	
	public double selectSumaryMoney() {

		sql = "select sum(custMoney) as sumary_money from customer";
		System.out.println(sql);

		try {

			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				return rs.getDouble("sumary_money");
			}

//			while (rs.next()) {
//
//				System.out.println(rs.getInt(1));
//				System.out.println(rs.getString(2));
//
//			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
			JdbcUtil.close(con);

		}
		return 0;

	}

//	public void delete(String custNumber) {
//
//		sql = "delete from customer where id=2";
//		System.out.println(sql);
//
//		try {
//
//			con = JdbcUtil.getConnection();
//			ps = con.prepareStatement(sql);
//			ps.executeUpdate();
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//
//		} finally {
//			JdbcUtil.close(ps);
//			JdbcUtil.close(con);
//
//		}
//	}
	/**
	 * 更新顾客
	 */
//	public void update() {
//
//		sql = "update yuchen_user set name='liumang' where id=1";
//		System.out.println(sql);
//
//		try {
//
//			con = JdbcUtil.getConnection();
//			ps = con.prepareStatement(sql);
//			ps.executeUpdate();
//		} catch (Exception e) {
//
//			e.printStackTrace();
//
//		} finally {
//
//			JdbcUtil.close(ps);
//			JdbcUtil.close(con);
//
//		}
//	}



	public static void main(String[] args) {

		AdministratorDAO ud = new AdministratorDAO();
		System.out.println("SumMoney："+ud.selectSumaryMoney());
	}
}