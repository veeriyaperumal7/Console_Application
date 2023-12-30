package com.veeriyaperumal.rolehierarchy.deleterole;

import java.sql.SQLException;

import com.veeriyaperumal.rolehierarchy.base.BaseView;
import com.veeriyaperumal.rolehierarchy.model.Role;

public class DeleteRoleView extends BaseView {

	private DeleteRoleViewModel deleteRoleViewModel;

	public DeleteRoleView() {
		this.deleteRoleViewModel = new DeleteRoleViewModel(this);
	}

	public void deleteRole() {
		Role role = new Role();
		role.setRoleName(getName("Enter role name : "));
		String redirectedName = "";
		try {
			if (!deleteRoleViewModel.isRolePresent(role.getRoleName())) {
				printUserWarningMessage("This role not present.");
				return;
			}
			redirectedName = deleteRoleViewModel.deleteRole(role);
			if (redirectedName.length() >= 1) {
				printSuccesMessage("Successfully role deleted and redirected to " + redirectedName);
			} else {
				printSuccesMessage("Not role deleted");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String getName(String message) {
		String name;
		print(message);
		do {
			name = getStringInput("Enter valid name : ");
			if (deleteRoleViewModel.isValidRoleName(name)) {
				break;
			} else {
				continue;
			}
		} while (true);
		return name;
	}
}
