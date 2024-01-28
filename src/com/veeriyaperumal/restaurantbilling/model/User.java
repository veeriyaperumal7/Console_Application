package com.veeriyaperumal.restaurantbilling.model;

public class User {
	private int userId;
	private String name, mobileNumber, role, password, emailAddress;

	public User() {

	}

	public User(String name, String mobileNumber, String role, String password, String emailAddress) {
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.role = role;
		this.password = password;
		this.emailAddress = emailAddress;
	}

	public User(int userId, String name, String mobileNumber, String role, String password, String emailAddress) {
		this.userId = userId;
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.role = role;
		this.password = password;
		this.emailAddress = emailAddress;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
