package com.veeriyaperumal.restaurantbilling.base;

public abstract class BaseViewModel {

	protected boolean isValidName(String name) {
		if (name.length() < 1) {
			return false;
		}
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (c < 'a' && c > 'z' && c < 'A' && c > 'Z' && c != '.' && c != ' ') {
				return false;
			}
		}
		return true;
	}

	protected boolean isValidEmailAddress(String emailAddress) {
		return (emailAddress.length() > 4 && emailAddress.contains(".com") && emailAddress.contains("@"));
	}

	protected boolean isValidMobileNumber(String mobileNumber) {
		if (mobileNumber.length() != 10) {
			return false;
		}
		for (char c : mobileNumber.toCharArray()) {
			if (c < '0' || c > '9')
				return false;
		}
		return true;
	}

	protected boolean isValidPassword(String password) {
		boolean specialCharacter = false, upperCase = false, lowerCase = false, number = false;
		if (password.length() < 1) {
			return false;
		}
		for (char c : password.toCharArray()) {
			if (c >= '0' && c <= '9')
				number = true;
			else if (c >= 'A' && c <= 'Z')
				upperCase = true;
			else if (c >= 'a' && c <= 'z')
				lowerCase = true;
			else
				specialCharacter = true;

		}
		if (number && upperCase && lowerCase && specialCharacter)
			return true;
		else
			return false;
	}

}
