package com.veeriyaperumal.hotelbilling.model;

import java.sql.Time;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Bill {

	private int billNo;
	private LocalDate billDate;
	private Time billTime;
	private List<Dish> purchasedDish = new LinkedList<>();
	private float billPrice;

	public int getBillNo() {
		return billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}

	public LocalDate getBillDate() {
		return billDate;
	}

	public void setBillDate(LocalDate billDate) {
		this.billDate = billDate;
	}

	public Time getBillTime() {
		return billTime;
	}

	public void setBillTime(Time billTime) {
		this.billTime = billTime;
	}

	public List<Dish> getPurchasedDish() {
		return purchasedDish;
	}

	public void setPurchasedDish(List<Dish> purchasedDish) {
		this.purchasedDish = purchasedDish;
	}

	public float getBillPrice() {
		return billPrice;
	}

	public void setBillPrice(float billPrice) {
		this.billPrice = billPrice;
	}
}
