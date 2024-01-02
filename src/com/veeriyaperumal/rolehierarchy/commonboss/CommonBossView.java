package com.veeriyaperumal.rolehierarchy.commonboss;

import com.veeriyaperumal.rolehierarchy.base.BaseView;

public class CommonBossView extends BaseView {
	private CommonBossViewModel commonBossViewModel;

	public CommonBossView() {
		this.commonBossViewModel = new CommonBossViewModel(this);
	}

	public void getCommonBoss() {
		String employeeName1 = getName("Enter user1 : ");
		String employeeName2 = getName("Enter user2 : ");
		String commonBoss = commonBossViewModel.getCommonBoss(employeeName1, employeeName2);
		if (commonBoss.length() >= 1) {
			printSuccesMessage("The common boss is : " + commonBoss);
		} else {
			printUserWarningMessage("There is no common boss found : ");
		}
	}

	private String getName(String message) {
		String name;
		print(message);
		do {
			name = getStringInput("Enter valid name : ");
			if (commonBossViewModel.isValidName(name)) {
				break;
			} else {
				continue;
			}
		} while (true);
		return name;
	}
}
