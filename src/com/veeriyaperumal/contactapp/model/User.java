package com.veeriyaperumal.contactapp.model;

public class User {

	private String email, password, secret;
	private boolean status;

	public User() {
		
	}
	
	public User(String email, String password, String secret) {
		this.email = email;
		this.password = password;
		this.secret = secret;
		this.setStatus(true);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String userName) {
		this.email = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
