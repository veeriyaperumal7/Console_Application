package com.veeriyaperumal.rolehierarchy.rootrole;

import java.sql.SQLException;

import com.veeriyaperumal.rolehierarchy.model.Employee;
import com.veeriyaperumal.rolehierarchy.model.Role;
import com.veeriyaperumal.rolehierarchy.repository.Repository;

public class RootRoleViewModel {

	private RootRoleView rootRoleView;

	public RootRoleViewModel(RootRoleView rootRoleView) {
		this.rootRoleView = rootRoleView;
	}

	public boolean isRoleName(String name) {
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

	public boolean addRootRole(Role rootRole) throws SQLException {
		return Repository.getInstance().addRootRole(rootRole);
	}
	
}
