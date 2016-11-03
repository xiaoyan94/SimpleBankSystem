package com.hpejn;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Datatime {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date = new Date(System.currentTimeMillis());
		System.out.println(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(date));

	}

}
