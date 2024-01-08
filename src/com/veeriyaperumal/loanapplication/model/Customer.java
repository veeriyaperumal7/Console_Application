package com.veeriyaperumal.loanapplication.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class Customer {
	private int customerId;
	private String name, gender, place, mobileNumber;
	private LocalDate dob;

	public Customer(int customerId, String name, String gender, String place, String mobileNumber, LocalDate dob) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.gender = gender;
		this.place = place;
		this.mobileNumber = mobileNumber;
		this.dob = dob;
	}

	public Customer() {

	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}


}
