package com.hpejn;

import java.util.Scanner;

public class StringLengthTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
//		String string = scanner.nextLine();
//		System.out.println(string.length());

		String custObj = scanner.nextLine();
		custObj = Double.toString(Double.parseDouble(custObj));
		System.out.println(custObj);
		scanner.close();
	}

}
