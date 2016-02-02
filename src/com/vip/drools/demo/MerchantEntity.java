package com.vip.drools.demo;

public class MerchantEntity {
	// 商户名称
	private String merchantName;
	// 是否是活动日
	private boolean activityDay;
	// 费用
	private double fee;
	// 当月发货次数
	private int sellNums;
	// 当月退货次数
	private int backNums;
	// 当月销售金额
	private double sellMoney;
	// 当月退货总金额
	private double backMoney;

	public void recordLog(String name, String type) {
		System.out.println("对 " + name + " 的属性 " + type + " 的费用操作记录");
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public boolean isActivityDay() {
		return activityDay;
	}

	public void setActivityDay(boolean activityDay) {
		this.activityDay = activityDay;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public int getSellNums() {
		return sellNums;
	}

	public void setSellNums(int sellNums) {
		this.sellNums = sellNums;
	}

	public int getBackNums() {
		return backNums;
	}

	public void setBackNums(int backNums) {
		this.backNums = backNums;
	}

	public double getSellMoney() {
		return sellMoney;
	}

	public void setSellMoney(double sellMoney) {
		this.sellMoney = sellMoney;
	}

	public double getBackMoney() {
		return backMoney;
	}

	public void setBackMoney(double backMoney) {
		this.backMoney = backMoney;
	}
}
