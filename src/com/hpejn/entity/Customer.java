package com.hpejn.entity;

import java.util.Date;

public class Customer {
	private String custName;
	private String custNumber;
	private String custPwd;
	private String custCard;
	private double custMoney;
	private Date custDate;

	public Customer(String custName, String custNumber, String custPwd,
			String custCard, double custMoney, Date custDate) {
		super();
		this.custName = custName;
		this.custNumber = custNumber;
		this.custPwd = custPwd;
		this.custCard = custCard;
		this.custMoney = custMoney;
		this.custDate = custDate;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustNumber() {
		return custNumber;
	}

	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}

	public String getCustPwd() {
		return custPwd;
	}

	public void setCustPwd(String custPwd) {
		this.custPwd = custPwd;
	}

	public String getCustCard() {
		return custCard;
	}

	public void setCustCard(String custCard) {
		this.custCard = custCard;
	}

	public double getCustMoney() {
		return custMoney;
	}

	public void setCustMoney(double custMoney) {
		this.custMoney = custMoney;
	}

	public Date getCustDate() {
		return custDate;
	}

	public void setCustDate(Date custDate) {
		this.custDate = custDate;
	}

}
