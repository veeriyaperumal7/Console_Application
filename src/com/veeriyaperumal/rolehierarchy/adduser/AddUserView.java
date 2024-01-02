package com.veeriyaperumal.rolehierarchy.adduser;

import java.sql.SQLException;

import com.veeriyaperumal.rolehierarchy.base.BaseView;
import com.veeriyaperumal.rolehierarchy.model.Employee;
import com.veeriyaperumal.rolehierarchy.model.Role;

public class AddUserView extends BaseView {

	private AddUserViewModel addUserViewModel;

	public AddUserView() {
		this.addUserViewModel = new AddUserViewModel(this);
	}

	public void addUser() {
		Employee newEmployee = new Employee();
		newEmployee.setName(getName("Enter User Name : "));
		Role role = addUserViewModel.getRole(getName("Enter Role : "));
		if (role == null) {
			printUserWarningMessage("User not added,because given role not found...");
			return;
		}
		try {
			if (addUserViewModel.addUser(role, newEmployee)) {
				printSuccesMessage("Successfully user added...");
			} else {
				printUserWarningMessage("User not added...");
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
			if (addUserViewModel.isValidName(name)) {
				break;
			} else {
				continue;
			}
		} while (true);
		return name;
	}

}
