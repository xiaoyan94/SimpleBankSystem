package com.hpejn.util;

import java.sql.SQLException;



public class BaseDao {
	
	public Jdbcutil util = null;
	
	public BaseDao(){
		try {
			if(util==null){
				util = Jdbcutil.createInstance();
				util.connectDB();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		util.closeConn();
		super.finalize();
	}
	
}