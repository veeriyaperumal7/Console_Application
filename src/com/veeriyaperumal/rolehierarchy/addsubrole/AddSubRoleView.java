package com.veeriyaperumal.rolehierarchy.addsubrole;

import java.sql.SQLException;

import com.veeriyaperumal.rolehierarchy.addsubrole.AddSubRoleViewModel;
import com.veeriyaperumal.rolehierarchy.base.BaseView;
import com.veeriyaperumal.rolehierarchy.model.Employee;
import com.veeriyaperumal.rolehierarchy.model.Role;

public class AddSubRoleView extends BaseView {

	private AddSubRoleViewModel addSubRoleViewModel;

	public AddSubRoleView() {
		this.addSubRoleViewModel = new AddSubRoleViewModel(this);
	}

	public void addSubRole() {
		print("How many operations : ");
		int operationCount = getIntegerInput("Enter the valid input : ");
		while (operationCount > 0) {
			Role subRole = new Role();
			Role reportingRole = new Role();
			subRole.setRoleName(getName("Enter sub role name : "));
			reportingRole.setRoleName(getName("Enter reporting role name : "));
			try {
				if (addSubRoleViewModel.addSubRole(subRole, reportingRole)) {
					printSuccesMessage("Successfully sub role added");
				} else {
					printSuccesMessage("Not sub role added");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			operationCount--;
		}
	}

	private String getName(String message) {
		String name;
		print(message);
		do {
			name = getStringInput("Enter valid name : ");
			if (addSubRoleViewModel.isValidSubRoleName(name)) {
				break;
			} else {
				continue;
			}
		} while (true);
		return name;
	}

}
