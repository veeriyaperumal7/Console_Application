package com.veeriyaperumal.rolehierarchy.addrole;

import java.sql.SQLException;

import com.veeriyaperumal.rolehierarchy.base.BaseView;
import com.veeriyaperumal.rolehierarchy.model.Employee;
import com.veeriyaperumal.rolehierarchy.model.Role;

public class AddRoleView extends BaseView {

	private AddRoleViewModel addRoleViewModel;

	public AddRoleView() {
		this.addRoleViewModel = new AddRoleViewModel(this);
	}

	public void addRole() {
		Role newRole = new Role();
		Employee employee = new Employee();
		newRole.setRoleName(getName("Enter role name : "));
		employee.setName(getName("Enter employee name : "));
		try {
			if (addRoleViewModel.addRole(newRole, employee)) {
				printSuccesMessage("Successfully role added");
			} else {
				printSuccesMessage("Not role added");
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
			if (addRoleViewModel.isRoleName(name)) {
				break;
			} else {
				continue;
			}
		} while (true);
		return name;
	}

}
