package com.veeriyaperumal.rolehierarchy.deleteuser;

import java.sql.SQLException;

import com.veeriyaperumal.rolehierarchy.base.BaseView;
import com.veeriyaperumal.rolehierarchy.model.Employee;
import com.veeriyaperumal.rolehierarchy.model.Role;

public class DeleteUserView extends BaseView {

	private DeleteUserViewModel deleteUserViewModel;

	public DeleteUserView() {
		this.deleteUserViewModel = new DeleteUserViewModel(this);
	}

	public void deleteUser() {

		String employeeName = (getName("Enter user name to delete : "));

		if (deleteUserViewModel.deleteUser(employeeName)) {
			printSuccesMessage("Successfully user deleted...");
		} else {
			printUserWarningMessage("User not deleted...");
		}

	}

	private String getName(String message) {
		String name;
		print(message);
		do {
			name = getStringInput("Enter valid name : ");
			if (deleteUserViewModel.isValidName(name)) {
				break;
			} else {
				continue;
			}
		} while (true);
		return name;
	}

}
