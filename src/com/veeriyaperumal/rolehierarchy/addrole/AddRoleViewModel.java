package com.veeriyaperumal.rolehierarchy.addrole;

import com.veeriyaperumal.rolehierarchy.repository.Repository;

import java.sql.SQLException;

import com.veeriyaperumal.rolehierarchy.model.Employee;
import com.veeriyaperumal.rolehierarchy.model.Role;

public class AddRoleViewModel {

	AddRoleView addRoleView;

	public AddRoleViewModel(AddRoleView addRoleView) {
		this.addRoleView = addRoleView;
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

	public boolean addRole(Role newRole, Employee employee) throws SQLException {
		return Repository.getInstance().addRole(newRole,employee);
	}

}
