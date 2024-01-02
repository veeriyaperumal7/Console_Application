package com.veeriyaperumal.rolehierarchy.addsubrole;

import java.sql.SQLException;

import com.veeriyaperumal.rolehierarchy.model.Role;
import com.veeriyaperumal.rolehierarchy.repository.Repository;

public class AddSubRoleViewModel {
	AddSubRoleView addSubRoleView;

	public AddSubRoleViewModel(AddSubRoleView addSubRoleView) {
		this.addSubRoleView = addSubRoleView;
	}

	public boolean isValidSubRoleName(String name) {
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

	public boolean addSubRole(Role subRole, Role reportingRole) throws SQLException {
		return Repository.getInstance().addSubRole(subRole,reportingRole);
	}

	public Role getReportingRole(String roleName) {
		return  Repository.getInstance().getRole(roleName);
	}

	
}
