package com.veeriyaperumal.rolehierarchy.numberofuserfromtop;

import com.veeriyaperumal.rolehierarchy.base.BaseView;

public class NumberOfUserFromTopView extends BaseView {
	private NumberOfUserFromTopViewModel numberOfUserFromTopViewModel;

	public NumberOfUserFromTopView() {
		this.numberOfUserFromTopViewModel = new NumberOfUserFromTopViewModel(this);
	}

	public void getNumberOfUserFromTop() {
		String employeeName = (getName("Enter user name: "));
		int count = numberOfUserFromTopViewModel.getNumberOfUserFromTop(employeeName);
		if (count != -1) {
			printSuccesMessage("No.of user from top : " + count);
		} else {
			printUserWarningMessage("Given employee not found...");
		}

	}

	private String getName(String message) {
		String name;
		print(message);
		do {
			name = getStringInput("Enter valid name : ");
			if (numberOfUserFromTopViewModel.isValidName(name)) {
				break;
			} else {
				continue;
			}
		} while (true);
		return name;
	}
}
