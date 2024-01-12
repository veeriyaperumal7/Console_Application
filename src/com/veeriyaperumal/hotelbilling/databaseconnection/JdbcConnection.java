package com.veeriyaperumal.hotelbilling.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcConnection {
	private String url = "jdbc:mysql://localhost:3306/hotelbilling";
	private String user = "root";
	private String password = "1234";

	static JdbcConnection jdbcConnection;
	private Connection connection;

	private JdbcConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		openSQLConnection();
	}

	public static JdbcConnection getInstance() throws SQLException, ClassNotFoundException {
		if (jdbcConnection == null) {
			jdbcConnection = new JdbcConnection();
		}
		return jdbcConnection;
	}

	private void openSQLConnection() throws SQLException {
		try {
			
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw e;
		}
	}

	public void closeSQLConnection() throws SQLException {
		try {
			connection.close();
		} catch (SQLException e) {
			throw e;
		}
	}

	public ResultSet executeSelectQuery(String query) throws SQLException {
		openSQLConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			return preparedStatement.executeQuery();
		} catch (SQLException e) {
			throw e;
		}
	}

	public int executeInsertOrUpdateQuery(String query) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected;
		} catch (SQLException e) {

			e.printStackTrace();
			return -1;
		}
	}
}
