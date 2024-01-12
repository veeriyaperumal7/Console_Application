package com.veeriyaperumal.hotelbilling.model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Bill {

	private int billNo;
	private Date billDate;
	private Time billTime;	
	private ArrayList<Dish> purchasedDish = new ArrayList<>();
	private float billPrice;

	public int getBillNo() {
		return billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Time getBillTime() {
		return billTime;
	}

	public void setBillTime(Time billTime) {
		this.billTime = billTime;
	}

	public ArrayList<Dish> getPurchasedDish() {
		return purchasedDish;
	}

	public void setPurchasedDish(ArrayList<Dish> purchasedDish) {
		this.purchasedDish = purchasedDish;
	}

	public float getBillPrice() {
		return billPrice;
	}

	public void setBillPrice(float billPrice) {
		this.billPrice = billPrice;
	}
}
