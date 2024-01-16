package com.veeriyaperumal.hotelbilling.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
		query = "Select * from employee where email_address='" + user.getEmailAddress() + "' and employee_password='"
				+ user.getPassword() + "' and employee_status=1";
		resultSet = JdbcConnection.getInstance().executeSelectQuery(query);
		if (resultSet.next()) {
			currentUser = new User();
			currentUser.setUserId(resultSet.getInt("employee_id"));
			currentUser.setEmailAddress(resultSet.getString("email_address"));
			currentUser.setMobileNumber(resultSet.getString("employee_mobile"));
			currentUser.setName(resultSet.getString("employee_name"));
			currentUser.setRole(resultSet.getString("employee_role"));
			return true;
		}

		return false;
	}

	public ArrayList<Dish> getDishList() throws ClassNotFoundException, SQLException {
		loadDishes();
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

	public boolean addEmployee(User user) throws ClassNotFoundException, SQLException {
		query = "INSERT INTO employee (email_address, employee_password, employee_status, employee_mobile, employee_name, employee_role) VALUES ('"
				+ user.getEmailAddress() + "', '" + user.getPassword() + "', " + true + ", '" + user.getMobileNumber()
				+ "', '" + user.getName() + "', '" + user.getRole() + "')";
		return JdbcConnection.getInstance().executeInsertOrUpdateQuery(query) >= 1;
	}

	public List<User> getEmployeeList() throws ClassNotFoundException, SQLException {
		List<User> employeeList = new ArrayList<>();
		query = "select * from employee where employee_status=1";
		resultSet = JdbcConnection.getInstance().executeSelectQuery(query);
		while (resultSet.next()) {
			int userId = resultSet.getInt("employee_id");
			String name = resultSet.getString("employee_name");
			String email = resultSet.getString("email_address");
			String password = resultSet.getString("employee_password");
			String mobileNumber = resultSet.getString("employee_mobile");
			String role = resultSet.getString("employee_role");
			User user = new User(userId, name, mobileNumber, role, password, email);
			employeeList.add(user);
		}
		return employeeList;
	}

	public boolean removeUser(int userId) throws ClassNotFoundException, SQLException {
		query = "update employee set employee_status = 0 where employee_id=" + userId;
		return JdbcConnection.getInstance().executeInsertOrUpdateQuery(query) >= 1;
	}

	public boolean cancelBill(int billNumber) throws ClassNotFoundException, SQLException {
		query = "update billing set bill_status=0 where bill_no=" + billNumber;
		if (JdbcConnection.getInstance().executeInsertOrUpdateQuery(query) >= 1) {
			query = "update billing_details set billing_detail__status=0 where bill_no=" + billNumber;
			return JdbcConnection.getInstance().executeInsertOrUpdateQuery(query) >= 1;
		} else {
			return false;
		}
	}

	public Bill getBill(int billNumber) throws ClassNotFoundException, SQLException {
		Bill bill = null;
		query = "SELECT billing.billing_date, billing.total_amount, billing_details.bill_no, billing_details.dish_id, "
				+ "dish.dish_name, billing_details.quantity, billing_details.subtotal FROM billing_details "
				+ "INNER JOIN billing ON billing_details.bill_no = billing.bill_no "
				+ "INNER JOIN dish ON billing_details.dish_id = dish.dish_id WHERE billing_details.bill_no = "
				+ billNumber;

		resultSet = JdbcConnection.getInstance().executeSelectQuery(query);
		while (resultSet.next()) {
			if (bill == null) {
				bill = new Bill();
				bill.setBillNo(resultSet.getInt("bill_no"));
				bill.setBillDate(resultSet.getDate("billing_date").toLocalDate());
				bill.setBillTime(resultSet.getTime("billing_date"));
				bill.setBillPrice(resultSet.getFloat("total_amount"));
			}

			Dish dish = new Dish();
			dish.setDishId(resultSet.getInt("dish_id"));
			dish.setDishName(resultSet.getString("dish_name"));
			dish.setQuantity(resultSet.getInt("quantity"));
			dish.setPrice(resultSet.getFloat("subtotal") / dish.getQuantity());
			bill.getPurchasedDish().add(dish);
		}

		return bill;
	}

	public ArrayList<String> getSalesReport(LocalDate fromDate, LocalDate toDate)
			throws ClassNotFoundException, SQLException {
		query = "Select count(bill_no) as billcount , sum(total_amount) as totalamount from billing where bill_status=1 ";
		if (fromDate != null && toDate != null) {
			query += " and billing_date >='" + fromDate.toString() + "' and billing_date<='" + toDate.toString() + "' ";
		}
		ArrayList<String> result = new ArrayList<>();
		resultSet = JdbcConnection.getInstance().executeSelectQuery(query);

		while (resultSet.next()) {
			result.add(resultSet.getInt("billcount") + "");
			result.add(resultSet.getFloat("totalamount") + "");
		}
		return result;
	}

	public ArrayList<Dish> getDishWiseSalesReport() throws ClassNotFoundException, SQLException {
		ArrayList<Dish> report = new ArrayList<>();
		query = "SELECT dish.dish_name as dish_name, " + "SUM(billing_details.quantity) as quantity, "
				+ "SUM(billing_details.subtotal) as subtotal " + "FROM billing_details "
				+ "INNER JOIN dish ON billing_details.dish_id = dish.dish_id "
				+ "WHERE billing_details.billing_detail__status = 1 " + "GROUP BY dish.dish_id ";
		resultSet = JdbcConnection.getInstance().executeSelectQuery(query);
		while (resultSet.next()) {
			Dish dish = new Dish();
			dish.setDishName(resultSet.getString("dish_name"));
			dish.setQuantity(resultSet.getInt("quantity"));
			dish.setPrice(resultSet.getFloat("subtotal"));
			report.add(dish);
		}
		return report;
	}

	public boolean addDish(Dish newDish) throws ClassNotFoundException, SQLException {
		query = "insert into dish (dish_name,dish_price,dish_status) values ('" + newDish.getDishName() + "',"
				+ newDish.getPrice() + ",1)";
		return JdbcConnection.getInstance().executeInsertOrUpdateQuery(query) >= 1;
	}

	public boolean updateDishData(Dish userSelectedDish) throws ClassNotFoundException, SQLException {
		query = "update dish set dish_name='" + userSelectedDish.getDishName() + "', dish_price = "
				+ userSelectedDish.getPrice() + " where dish_id = " + userSelectedDish.getDishId();
		return JdbcConnection.getInstance().executeInsertOrUpdateQuery(query) >= 1;
	}

	public boolean removeDish(Dish userSelectedDish) throws ClassNotFoundException, SQLException {
		query = "update dish set dish_status=0 where dish_id = " + userSelectedDish.getDishId();
		return JdbcConnection.getInstance().executeInsertOrUpdateQuery(query) >= 1;
	}

}
