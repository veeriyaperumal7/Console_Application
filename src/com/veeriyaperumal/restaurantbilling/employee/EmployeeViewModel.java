package com.veeriyaperumal.restaurantbilling.employee;

import java.sql.SQLException;
import java.util.Currency;
import java.util.List;

import com.veeriyaperumal.restaurantbilling.base.BaseViewModel;
import com.veeriyaperumal.restaurantbilling.model.User;
import com.veeriyaperumal.restaurantbilling.repository.Repository;

public class EmployeeViewModel extends BaseViewModel {

	private EmployeeView employeeView;
	private List<User> employeeList;

	public EmployeeViewModel(EmployeeView employeeView) {
		this.employeeView = employeeView;
	}

	public boolean checkValidName(String name) {
		return isValidName(name);
	}

	public boolean checkValidMobileNumber(String mobileNumber) {
		return isValidMobileNumber(mobileNumber);
	}

	public boolean checkValidPassword(String password) {
		return isValidPassword(password);
	}

	public boolean addEmployee(User user) throws ClassNotFoundException, SQLException {
		return Repository.getInstance().addEmployee(user);
	}

	public List<User> getEmployeeList() throws ClassNotFoundException, SQLException {
		employeeList = Repository.getInstance().getEmployeeList();
		return employeeList;
	}

	public boolean removeEmployee(int userId) throws ClassNotFoundException, SQLException {
		if (Repository.getInstance().getCurrentUser().getUserId() == userId) {
			return false;
		}
		for (User user : employeeList) {
			if (user.getUserId() == userId) {
				return Repository.getInstance().removeUser(userId);
			}
		}
		return false;
	}

}
