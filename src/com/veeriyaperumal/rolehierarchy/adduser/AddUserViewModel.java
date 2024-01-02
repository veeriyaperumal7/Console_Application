package com.veeriyaperumal.rolehierarchy.adduser;

import com.veeriyaperumal.rolehierarchy.repository.Repository;

import java.sql.SQLException;

import com.veeriyaperumal.rolehierarchy.model.Employee;
import com.veeriyaperumal.rolehierarchy.model.Role;

public class AddUserViewModel {

	AddUserView addRoleView;

	public AddUserViewModel(AddUserView addRoleView) {
		this.addRoleView = addRoleView;
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

	public boolean addUser(Role role, Employee employee) throws SQLException {
		return Repository.getInstance().addUser(role, employee);
	}

	public Role getRole(String roleName) {
		return Repository.getInstance().getRole(roleName);
	}

}
