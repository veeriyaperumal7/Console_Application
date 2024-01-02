package com.veeriyaperumal.rolehierarchy.rootrole;

import java.sql.SQLException;

import com.veeriyaperumal.rolehierarchy.base.BaseView;
import com.veeriyaperumal.rolehierarchy.model.Role;

public class RootRoleView extends BaseView {

	private RootRoleViewModel rootRoleViewModel;

	public RootRoleView() {
		this.rootRoleViewModel = new RootRoleViewModel(this);
	}

	public void addRootRole() {
		Role newRole = new Role();
		newRole.setRoleName(getRoleName("Enter Valid Root Role Name : "));
		try {
			if (rootRoleViewModel.addRootRole(newRole)) {
				printSuccesMessage("Root role added successfully...");
			} else {
				printUserWarningMessage("Root role not added...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String getRoleName(String message) {
		String name;
		do {
			print(message);
			name = getStringInput(message);
			if (rootRoleViewModel.isRoleName(name)) {
				break;
			} else {
				continue;
			}
		} while (true);
		return name;
	}
}
