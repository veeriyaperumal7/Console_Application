package com.veeriyaperumal.rolehierarchy.base;

public class BaseViewModel {

	protected boolean isValidName(String name) {
		if (name.length() < 1) {
			return false;
		}
		for (int i = 0; i < name.length(); i++) {
			if ((name.charAt(i) >= 'A' && name.charAt(i) <= 'Z') || (name.charAt(i) >= 'a' && name.charAt(i) <= 'z')
					|| name.charAt(i) == ' ' || name.charAt(i) == '.') {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

}
