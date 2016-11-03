package com.hpejn.test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDemo {

	/**
	 * Demo
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();//1.注册驱动
			
			//2.连接数据库
			String url = "jdbc:mysql://localhost:3306/bankSys";
			String user = "root";
			String password = "root";
			Connection connection =  DriverManager.getConnection(url, user, password);
			//3.创建执行对象
			Statement stmt = connection.createStatement();
			//4.实现查询功能
			String userName="xiaoming";
			String userPwd="123456";
			String sql = "SELECT * FROM administrator WHERE adminName='"+userName+"' AND adminPwd='"+userPwd+"'";
			ResultSet res = stmt.executeQuery(sql);
			boolean isLoginSuccessful = false;
			int selectCount=0;
			if (res.next()){
				
				selectCount = res.getInt(1);
				System.out.println(selectCount);
				Reader row2 = res.getCharacterStream(2);
				BufferedReader br = new BufferedReader(row2);
				System.out.println(br.readLine());
				System.out.println(res.getString("adminPwd"));
				
				
			}
			if (selectCount>0) {
				System.out.println("登录成功！");
				//isLoginSuccessful = true;
			}else {
				System.out.println("登陆失败！");
			}
			
			if (isLoginSuccessful) {
				//插入
				int affectedRows = stmt.executeUpdate("INSERT INTO administrator VALUES('2','admin','123456')");
				if (affectedRows>0) {
					System.out.println("插入成功！");
				} else {
					System.out.println("插入失败！");
				}
			}
			res.close();
			stmt.close();
			connection.close();
			res=null;
			stmt=null;
			connection=null;
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC驱动未找到！");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("关于数据库访问错误或其他错误信息的异常。 ");
			e.printStackTrace();
		} catch (InstantiationException e) {
			System.out.println("当应用程序试图使用 Class 类中的 newInstance 方法创建一个类的实例，而指定的类对象无法被实例化时，抛出该异常。");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("当应用程序试图反射性地创建一个实例（而不是数组）、设置或获取一个字段，或者调用一个方法，但当前正在执行的方法无法访问指定类、字段、方法或构造方法的定义时，抛出 IllegalAccessException。 ");
			e.printStackTrace();
		}
		
	}

}
