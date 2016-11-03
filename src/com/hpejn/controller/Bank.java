package com.hpejn.controller;

import java.util.Scanner;

import com.hpejn.service.impl.AdministratorServiceImpl;
import com.hpejn.service.impl.CustomerServiceImpl;
import com.hpejn.util.InputCheck;

public class Bank {

	static AdministratorServiceImpl adminService = new AdministratorServiceImpl();
	static CustomerServiceImpl custService = new CustomerServiceImpl();
	static Scanner scanner = new Scanner(System.in);

	/**
	 * 程序入口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		String selectItem = null;
		String selectItemRe = "^[0-2]$";
		while (true) {
			System.out.println("--------------银行系统----------------");
			System.out.println("          ****1.管理员****        ");
			System.out.println("          ****2.顾  客****        ");
			System.out.println("          ****0.退  出****        ");
			System.out.println("------------------------------------");
			selectItem = InputCheck.checkAndGetInput("选项", selectItemRe);

			// 服务窗口
			switch (selectItem) {

			case "1":// 管理员

				boolean isAdminLoginSuccessful = loginAdminAndCust("admin");
				if (isAdminLoginSuccessful) {
					System.out.println("欢迎再次使用，谢谢！");
//					return;
				}
				break;

			case "2":// 顾客
				boolean isCustLoginSuccessful = loginAdminAndCust("customer");
				if (isCustLoginSuccessful) {
					System.out.println("欢迎再次使用，谢谢！");
				}
				break;

			default:
				System.out.println("欢迎再次使用本系统，谢谢！");
				return;
			}
		}
	}

	private static boolean loginAdminAndCust(String type) {

		String userNameRe = null;
		String userPwdRe = "^[0-9]{6}$";
		String userName = null;
		String userPwd = null;
		if (type.equals("admin")) {
			userNameRe = "^[0-9A-Za-z]{6,10}$";
		} else {
			userNameRe = "^[1-9]\\d{18}";// 银行卡登录
		}
		// 检验用户名和密码格式
		userName = InputCheck.checkAndGetInput("用户名", userNameRe);
		userPwd = InputCheck.checkAndGetInput("密码", userPwdRe);
		boolean isLoginSuccessful;
		if (type.equals("admin")) {
			isLoginSuccessful = adminService.adminManageWindow(userName,
					userPwd);

		} else {// 顾客登陆
			isLoginSuccessful = custService.customerManageWindow(userName, userPwd);
		}
		return isLoginSuccessful;

	}


}
