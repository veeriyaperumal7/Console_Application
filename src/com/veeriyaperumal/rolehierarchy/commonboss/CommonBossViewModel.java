package com.veeriyaperumal.rolehierarchy.commonboss;

import com.veeriyaperumal.rolehierarchy.repository.Repository;

public class CommonBossViewModel {

	private CommonBossView commonBossView;

	public CommonBossViewModel(CommonBossView commonBossView) {
		this.commonBossView = commonBossView;
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

	public String getCommonBoss(String employeeName1, String employeeName2) {
		return Repository.getInstance().getCommonBoss(employeeName1, employeeName2);
	}

}
