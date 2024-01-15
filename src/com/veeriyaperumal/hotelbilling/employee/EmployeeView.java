package com.veeriyaperumal.hotelbilling.employee;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

import com.veeriyaperumal.hotelbilling.base.BaseView;
import com.veeriyaperumal.hotelbilling.model.User;

public class EmployeeView extends BaseView {

	private EmployeeViewModel employeeViewModel;

	public EmployeeView() {
		this.employeeViewModel = new EmployeeViewModel(this);
	}

	public void showEmployeeOptions() {
		menuOptions.clear();
		menuOptions.add("Add Employee");
		menuOptions.add("Remove Employee");
		printOptionsTable(menuOptions, "Options");
		print("Choose your options : ");
		chooseFeature(menuOptions.get(getIntegerInput("Choose valid options : ", 1, menuOptions.size()) - 1));
	}

	private void chooseFeature(String option) {
		switch (option) {
		case "Add Employee":
			addEmployee();
			break;
		case "Remove Employee":
			removeEmployee();
			break;
		}
	}

	private void removeEmployee() {
		try {
			printEmployeeTable(employeeViewModel.getEmployeeList(), "Employee List");
			print("Enter user id to remove : ");
			if (employeeViewModel.removeEmployee(getIntegerInput("Enter valid user id : "))) {
				printSuccesMessage("User successfully removed...");
			} else {
				printUserWarningMessage("User not removed.Because entered user id not available...");
			}
		} catch (ClassNotFoundException | SQLException e) {
			printErrorMessage("Error while removeing employee data");
			e.printStackTrace();
		}
	}

	private void addEmployee() {
		String name = getName();
		String mobileNumber = getMobileNumber();
		String role = getRole();
		String password = getPassword();
		String emailId = getEmailId();
		User user = new User(name, mobileNumber, role, password, emailId);
		try {
			employeeViewModel.addEmployee(user);
			printSuccesMessage("Employee added successfully...");
		} catch (ClassNotFoundException | SQLException e) {
			printUserWarningMessage("Employee not added...");
			e.printStackTrace();
		}

	}

	private String getEmailId() {
		print("Enter you email Address : ");
		return getEmailInput("Enter valid email address.");
	}

	private String getPassword() {
		print("Enter password : ");
		String password = getStringInput(
				"Enter valid password.Password must contains mixed of upper ,lower case,special character and numbers : ");
		while (!employeeViewModel.checkValidPassword(password)) {
			showWrongSelectionMessage(
					"Enter valid password.Password must contains mixed of upper ,lower case,special character and numbers : ");
			password = getStringInput(password);
		}
		return password;
	}

	private String getRole() {
		List<String> tempList = new ArrayList<>(List.of("ADMIN", "USER"));
		printOptionsTable(tempList, "Choose Role");
		print("Choose the valid role : ");
		return tempList.get(getIntegerInput("Choose the valid role : ", 1, 2) - 1);
	}

	private String getMobileNumber() {
		print("Enter employee mobile number : ");
		String mobileNumber = getStringInput("Enter valid mobile number : ");
		while (!employeeViewModel.checkValidMobileNumber(mobileNumber)) {
			showWrongSelectionMessage("Enter valid mobile number : ");
			mobileNumber = getStringInput(mobileNumber);
		}
		return mobileNumber;
	}

	private String getName() {
		print("Enter employee name : ");
		String name = getStringInput("Enter valid name : ");
		while (!employeeViewModel.checkValidName(name)) {
			showWrongSelectionMessage("Enter valid name : ");
			name = getStringInput(name);
		}
		return name;
	}

}
