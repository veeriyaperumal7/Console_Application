package com.veeriyaperumal.hotelbilling.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import com.veeriyaperumal.hotelbilling.databaseconnection.JdbcConnection;
import com.veeriyaperumal.hotelbilling.model.User;
import com.veeriyaperumal.hotelbilling.model.Bill;
import com.veeriyaperumal.hotelbilling.model.Dish;

public class Repository {

	private static Repository repository;
	private String query;
	private ResultSet resultSet;
	private User currentUser;
	private ArrayList<Dish> dishList;

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

	public ArrayList<Dish> getDishList() throws ClassNotFoundException, SQLException {
		if (dishList == null) {
			loadDishes();
		}
		return dishList;
	}

	private void loadDishes() throws ClassNotFoundException, SQLException {
		dishList = new ArrayList<Dish>();
		query = "Select * from dish where dish_status=1";
		resultSet = JdbcConnection.getInstance().executeSelectQuery(query);
		while (resultSet.next()) {
			Dish dish = new Dish();
			dish.setDishId(resultSet.getInt("dish_id"));
			dish.setDishName(resultSet.getString("dish_name"));
			dish.setImagePath(resultSet.getString("dish_imagepath"));
			dish.setPrice(resultSet.getFloat("dish_price"));
			dishList.add(dish);
		}

	}

	public int getNewBillNo() throws ClassNotFoundException, SQLException {
		query = "Select max(bill_no) as maxBillNo from billing";
		resultSet = JdbcConnection.getInstance().executeSelectQuery(query);
		if (resultSet.next()) {
			return resultSet.getInt("maxBillNo") + 1;
		}
		return 1;
	}

	public boolean finishBill(Bill currentBill) throws ClassNotFoundException, SQLException {
		int billNo = currentBill.getBillNo(), rowsAffected = 0;
		for (Dish dish : currentBill.getPurchasedDish()) {
			query = "insert into billing_details (bill_no, dish_id, quantity, subtotal, billing_detail__status) "
					+ "values(" + billNo + "," + dish.getDishId() + "," + dish.getQuantity() + ","
					+ dish.getPrice() * dish.getQuantity() + ",1)";
			rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
			if (rowsAffected < 1) {
				return false;
			}
		}
		query = "INSERT INTO billing (bill_no, user_id, billing_date, billing_time, total_amount, bill_status) "
				+ "VALUES (" + billNo + ", " + currentUser.getUserId() + ", '" + currentBill.getBillDate()
				+ "', CURRENT_TIME(), " + currentBill.getBillPrice() + ", 1)";
		return JdbcConnection.getInstance().executeInsertOrUpdateQuery(query) >= 1;
	}

}
