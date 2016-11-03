package com.hpejn.test;


import java.util.Scanner;

public class BankSystem {

	private static Scanner scanner;

	/**
	 * 程序入口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		while (true) {
			System.out.println("----------银行系统-----------");
			System.out.println("       ****1.管理员****        ");
			System.out.println("       ****2.顾  客****        ");
			System.out.println("       ****0.退  出****        ");
			System.out.println("---------------------------- ");
			System.out.print("请选择：");
			int selectItem = scanner.nextInt();
			System.out.println("请输入用户名：");
//			String userName = scanner.nextLine();
			System.out.println("请输入密码：");
//			String userPwd = scanner.nextLine();
			
			if (selectItem == 0) {
				System.out.println("Bye!");
				break;
			}
			switch (selectItem) {
			case 1:
				adminManageWindow();
				break;
			case 2:
				customerManageWindow();
				break;
			default:
				break;
			}

		}
		scanner.close();

	}

	public static void adminManageWindow() {
		while (true) {
			System.out.println("             银行系统【管理员】");
			System.out.println("************************************");
			System.out.println("   1.添加顾客  2.计算储蓄总额  3.退出");
			System.out.println("************************************");
			int selectItem = scanner.nextInt();
			switch (selectItem) {
			case 1:
				// MoneyOut
				break;
			case 2:

				break;
			case 3:
				return;
			default:
				break;
			}

		}
	}

	public static void customerManageWindow() {
		while (true) {
			System.out.println("             银行系统【顾客】");
			System.out.println("************************************");
			System.out.println("  1.存款 2.取款 3.查询余额 4.转账 ");
			System.out.println("   5.修改密码 6.查询月账单 7.退出 ");
			System.out.println("************************************");
			int selectItem = scanner.nextInt();
			switch (selectItem) {
			case 1:
				// MoneyOut
				break;
			case 2:

				break;
			case 3:
				break;
			case 4:
				break;

			case 5:
				break;

			case 6:
				break;

			case 7:
				return;
			default:
				break;
			}

		}
	}
}
