package com.veeriyaperumal.contactapp.model;

public class Contact {

	private String email, name, mobileNumber;
	private boolean contactStatus;

	public Contact() {

	}

	public Contact(String email, String name, String mobileNumber) {
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.name = name;
		this.contactStatus = true;
	}

	@Override
	public String toString() {
		return "Email : " + email + "Name : " + name + " Mobile Number : " + mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public boolean isContactStatus() {
		return contactStatus;
	}

	public void setContactStatus(boolean contactStatus) {
		this.contactStatus = contactStatus;
	}

}
