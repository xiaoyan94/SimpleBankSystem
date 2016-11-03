package com.hpejn.util;

import java.util.Scanner;

public class InputCheck {
	static Scanner scanner = new Scanner(System.in);

	public static String checkAndGetInput(String type, String checkRe) {
		String custObj = null;// 输入内容
		// 正则
		String promptString = type;
		boolean isInputWrong = false;
		while (true)// 格式检验
		{
			if (isInputWrong) {
				System.out.println(promptString + "格式错误！请重新输入！");
			}else {
				System.out.println("请输入" + promptString +":");
			}
			custObj = scanner.nextLine();
			if (!custObj.matches(checkRe)) {
				isInputWrong = true;
			} else {
				break;
			}
		}
		System.out.println("您输入的" + promptString + "：" + custObj);
		return custObj;
	}
}
