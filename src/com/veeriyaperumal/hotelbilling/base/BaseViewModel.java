package com.veeriyaperumal.hotelbilling.base;

abstract class BaseViewModel {

	protected boolean isValidName(String name) {
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (c < 'a' && c > 'z' && c < 'A' && c > 'Z' && c != '.' && c != ' ') {
				return false;
			}
		}
		return true;
	}

	protected boolean isValidEmailAddress(String emailAddress) {
		return (emailAddress.length() > 4 && emailAddress.contains(".com"));
	}

}
