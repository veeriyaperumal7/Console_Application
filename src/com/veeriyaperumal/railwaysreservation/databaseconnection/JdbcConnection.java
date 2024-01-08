package com.veeriyaperumal.railwaysreservation.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.veeriyaperumal.railwaysreservation.util.Utility;

public class JdbcConnection {
	private String url = "jdbc:mysql://localhost:3306/railwayreservation";
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

	public void printErrorMessageAndTerminate(String errorMessage) {
		System.out.print(Utility.BOLD + Utility.RED + errorMessage + Utility.RESET);
		System.exit(0);
	}

	public void printErrorMessageWithQuery(String message, String query) {
		System.out.print(Utility.BOLD + Utility.RED + message + " : " + Utility.YELLOW + query + Utility.RESET);
	}
	
}
