package com.veeriyaperumal.rolehierarchy.numberofuserfromtop;

import com.veeriyaperumal.rolehierarchy.repository.Repository;

public class NumberOfUserFromTopViewModel {
	private NumberOfUserFromTopView numberOfUserFromTopView;

	public NumberOfUserFromTopViewModel(NumberOfUserFromTopView numberOfUserFromTopView) {
		this.numberOfUserFromTopView = numberOfUserFromTopView;
	}

	public boolean isValidName(String name) {
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

	public int getNumberOfUserFromTop(String employeeName) {
		return Repository.getInstance().getNumberOfUserFromTop(employeeName);
	}
	
}
