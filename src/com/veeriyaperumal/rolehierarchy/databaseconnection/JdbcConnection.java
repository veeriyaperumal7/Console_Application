package com.veeriyaperumal.rolehierarchy.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.veeriyaperumal.rolehierarchy.base.BaseView;

public class JdbcConnection extends BaseView {
	private String url = "jdbc:mysql://localhost:3306/role_hierarchy";
	private String user = "root";
	private String password = "1234";

	static JdbcConnection jdbcConnection;
	private Connection connection;

	private JdbcConnection() {
	}

	public static JdbcConnection getInstance() {
		if (jdbcConnection == null) {
			jdbcConnection = new JdbcConnection();
		}
		return jdbcConnection;
	}

	private void openSQLConnection() {
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			printErrorMessageAndTerminate(
					"Error while making SQL Connection.Please check and retry.Program terminated automatically");
		}
	}

	public void closeSQLConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			printErrorMessageAndTerminate(
					"Error while closing SQL Connection.Please check and retry.Program terminated automatically");
		}
	}

	public ResultSet executeSelectQuery(String query) {
		openSQLConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			return preparedStatement.executeQuery();
		} catch (SQLException e) {
			printErrorMessageWithQuery("Error while executing this query", query);
			e.printStackTrace();
		}
		return null;
	}

	public int executeInsertOrUpdateQuery(String query) {
		openSQLConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected;
		} catch (SQLException e) {
			printErrorMessageWithQuery("Error while executing this query", query);
			e.printStackTrace();
			return -1;
		}
	}

}
