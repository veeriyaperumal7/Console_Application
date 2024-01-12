package com.veeriyaperumal.hotelbilling.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.veeriyaperumal.hotelbilling.databaseconnection.JdbcConnection;
import com.veeriyaperumal.hotelbilling.model.User;

public class Repository {

	private static Repository repository;
	private String query;
	private ResultSet resultSet;
	private User currentUser;

	public User getCurrentUser() {
		return currentUser;
	}

	private Repository() {

	}

	public static Repository getInstance() {
		if (repository == null) {
			repository = new Repository();
		}
		return repository;
	}

	public boolean isValidUser(User user) throws SQLException, ClassNotFoundException {
		query = "Select * from employee where email_address='" + user.getemailAddresse() + "' and employee_password='"
				+ user.getPassword() + "' and employee_status=1";
		resultSet = JdbcConnection.getInstance().executeSelectQuery(query);
		if (resultSet.next()) {
			currentUser = new User();
			currentUser.setUserId(resultSet.getInt("employee_id"));
			currentUser.setemailAddress(resultSet.getString("email_address"));
			currentUser.setMobileNumber(resultSet.getString("employee_mobile"));
			currentUser.setName(resultSet.getString("employee_name"));
			currentUser.setRole(resultSet.getString("employee_role"));
			return true;
		}

		return false;
	}

}
