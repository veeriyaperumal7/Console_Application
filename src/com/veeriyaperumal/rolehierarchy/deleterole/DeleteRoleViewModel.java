package com.veeriyaperumal.rolehierarchy.deleterole;

import java.sql.SQLException;

import com.veeriyaperumal.rolehierarchy.model.Role;
import com.veeriyaperumal.rolehierarchy.repository.Repository;

public class DeleteRoleViewModel {

	DeleteRoleView deleteRoleView;

	public DeleteRoleViewModel(DeleteRoleView deleteRoleView) {
		this.deleteRoleView = deleteRoleView;
	}

	public String deleteRole(Role role) throws SQLException {
		return Repository.getInstance().deleteRole(role);
	}

	public boolean isRolePresent(String name) throws SQLException {
		return (Repository.getInstance().getRoleId(name) != -1) ? true : false;
	}

	public boolean isValidRoleName(String name) {
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
