package com.hpejn.entity;

import java.util.Date;

public class Business {
	private int businessNumber;
	private String custNumber;
	private String businessType;
	private double businessMoney;
	private Date businessDate;

	/**
	 * 构造方法
	 * 
	 * @param businessNumber
	 * @param custNumber
	 * @param businessType
	 * @param businessMoney
	 * @param businessDate
	 */
	public Business(String custNumber, String businessType,
			double businessMoney, Date businessDate) {
		super();
		this.custNumber = custNumber;
		this.businessType = businessType;
		this.businessMoney = businessMoney;
		this.businessDate = businessDate;
	}

	public int getBusinessNumber() {
		return businessNumber;
	}

	public String getCustNumber() {
		return custNumber;
	}

	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public double getBusinessMoney() {
		return businessMoney;
	}

	public void setBusinessMoney(double businessMoney) {
		this.businessMoney = businessMoney;
	}

	public Date getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public void setBusinessNumber(int businessNumber) {
		this.businessNumber = businessNumber;
	}

	@Override
	public String toString() {
		return businessNumber + " \t\t" + custNumber + "\t" + businessType
				+ "\t\t" + businessMoney + "\t\t" + businessDate;
	}

}
