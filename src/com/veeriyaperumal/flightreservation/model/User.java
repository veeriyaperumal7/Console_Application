package com.veeriyaperumal.flightreservation.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private int userId;
	private String name;
	private int age;
	private String gender;
	private String ticketStatus;

	@Override
	public String toString() {
		return "User Id : " + userId + "\nName : " + name + "\nAge : " + age + "\nGender : " + gender + "\nStatus : "
				+ ticketStatus + "\n";
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
}
