package com.veeriyaperumal.loanapplication.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

import com.veeriyaperumal.loanapplication.databaseconnection.JdbcConnection;
import com.veeriyaperumal.loanapplication.model.Customer;
import com.veeriyaperumal.loanapplication.model.LoanData;
import com.veeriyaperumal.loanapplication.model.TransactionData;

public class Repository {
	private String query;
	private static Repository repository;
	private HashMap<Integer, Customer> customerList = new HashMap<>();
	private HashMap<Integer, LoanData> loanList = new HashMap<>();
	private HashMap<Integer, TransactionData> transactionList = new HashMap<>();

	private Repository() {
	}

	public static Repository getInstance() {
		if (repository == null) {
			repository = new Repository();
		}
		return repository;
	}

	public boolean addCustomer(Customer customer) {
		int rowsAffected = 0;
		query = "insert into customer_data (customer_id,customer_name, customer_dob, customer_gender,customer_place,customer_mobile,customer_status) "
				+ "values(" + customer.getCustomerId() + ",'" + customer.getName() + "','"
				+ java.sql.Date.valueOf(customer.getDob()) + "','" + customer.getGender() + "','" + customer.getPlace()
				+ "','" + customer.getMobileNumber() + "','ONLINE')";

		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		if (rowsAffected < 1) {
			JdbcConnection.getInstance().printErrorMessageWithQuery("Error while insert into table", query);
		}
		return (rowsAffected >= 1) ? true : false;
	}

	public int getNewCustomerId() {
		query = "SELECT MAX(customer_id) AS max_customer_id FROM customer_data";
		ResultSet resultSet = JdbcConnection.getInstance().executeSelectQuery(query);
		try {
			if (resultSet.next()) {
				int maxCustomerId = resultSet.getInt("max_customer_id");
				return maxCustomerId + 1;
			} else {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	public boolean addLoan(LoanData loan, Customer customer) {
		int rowsAffected = 0;

		String query = "INSERT INTO loan_data (Customer_id, loan_amount, loan_pending_amount, loan_paid_amount, "
				+ "loan_status, loan_interest_percentage, loan_interest_amount, loan_total_payable_amount, loan_issue_date) "
				+ "VALUES (" + customer.getCustomerId() + ", " + loan.getLoanAmount() + ", "
				+ loan.getLoanPendingAmount() + ", " + loan.getLoanPaidAmount() + ", 'PENDING', "
				+ loan.getLoanInterestPercentage() + ", " + loan.getLoanIntresetAmount() + ", "
				+ loan.getLoanTotalPayableAmount() + ", '" + java.sql.Date.valueOf(loan.getLoanIssueDate()) + "')";

		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		if (rowsAffected < 1) {
			JdbcConnection.getInstance().printErrorMessageWithQuery("Error while insert into table", query);
		}

		query = "INSERT INTO loan_entry (loan_no, Customer_id, loan_credit_amount, loan_entry_status, payment_date) "
				+ "VALUES (" + getMaxLoanNumber() + ", " + customer.getCustomerId() + ", "
				+ loan.getLoanTotalPayableAmount() + ", 'ONLINE', '" + Date.valueOf(LocalDate.now()) + "')";

		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);

		if (rowsAffected < 1) {
			JdbcConnection.getInstance().printErrorMessageWithQuery("Error while insert value into payment entry table",
					query);
		}

		return (rowsAffected >= 1) ? true : false;
	}

	int getMaxLoanNumber() {
		String query = "SELECT MAX(loan_no) AS maxLoanNo FROM loan_data";
		ResultSet resultSet = JdbcConnection.getInstance().executeSelectQuery(query);
		try {
			if (resultSet.next()) {
				int maxCustomerId = resultSet.getInt("maxLoanNo");
				return maxCustomerId;
			} else {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	public HashMap<Integer, Customer> getCustomerList() {
		customerList.clear();
		query = "select * from customer_data where customer_status='ONLINE'";
		ResultSet result = JdbcConnection.getInstance().executeSelectQuery(query);
		try {
			while (result.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(result.getInt("customer_id"));
				customer.setName(result.getString("customer_name"));
				customer.setMobileNumber(result.getString("customer_mobile"));
				customer.setGender(result.getString("customer_gender"));
				customer.setPlace(result.getString("customer_place"));
				customerList.put(customer.getCustomerId(), customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customerList;
	}

	public HashMap<Integer, LoanData> getLoanList() {
		loanList.clear();
		query = "select * from loan_data where loan_status ='PENDING'";
		ResultSet result = JdbcConnection.getInstance().executeSelectQuery(query);
		try {
			while (result.next()) {
				LoanData loanData = new LoanData();
				loanData.setLoanNumber(result.getInt("loan_no"));
				loanData.setCustomerId(result.getInt("customer_id"));
				loanData.setLoanAmount(result.getFloat("loan_amount"));
				loanData.setLoanPendingAmount(result.getFloat("loan_pending_amount"));
				loanData.setLoanPaidAmount(result.getFloat("loan_paid_amount"));
				loanData.setLoanInterestPercentage(result.getFloat("loan_interest_percentage"));
				loanData.setLoanIntresetAmount(result.getFloat("loan_interest_amount"));
				loanData.setLoanTotalPayableAmount(result.getFloat("loan_total_payable_amount"));
				loanData.setLoanIssueDate(result.getDate("loan_issue_date").toLocalDate());
				loanList.put(loanData.getLoanNumber(), loanData);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loanList;
	}

	public HashMap<Integer, LoanData> getCompletedLoanList() {
		loanList.clear();
		query = "select * from loan_data where loan_status ='COMPLETED'";
		ResultSet result = JdbcConnection.getInstance().executeSelectQuery(query);
		try {
			while (result.next()) {
				LoanData loanData = new LoanData();
				loanData.setLoanNumber(result.getInt("loan_no"));
				loanData.setCustomerId(result.getInt("customer_id"));
				loanData.setLoanAmount(result.getFloat("loan_amount"));
				loanData.setLoanPendingAmount(result.getFloat("loan_pending_amount"));
				loanData.setLoanPaidAmount(result.getFloat("loan_paid_amount"));
				loanData.setLoanInterestPercentage(result.getFloat("loan_interest_percentage"));
				loanData.setLoanIntresetAmount(result.getFloat("loan_interest_amount"));
				loanData.setLoanTotalPayableAmount(result.getFloat("loan_total_payable_amount"));
				loanData.setLoanIssueDate(result.getDate("loan_issue_date").toLocalDate());
				loanList.put(loanData.getLoanNumber(), loanData);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loanList;
	}

	public void setLoanList(HashMap<Integer, LoanData> loanList) {
		this.loanList = loanList;
	}

	public boolean debitAmount(LoanData loan, float debitAmount) {
		int rowsAffected = 0;
		query = "INSERT INTO loan_entry (loan_no, Customer_id, loan_paid_amount, loan_entry_status, payment_date) "
				+ "VALUES (" + loan.getLoanNumber() + ", " + loan.getCustomerId() + ", " + debitAmount + ", 'ONLINE', '"
				+ Date.valueOf(LocalDate.now()) + "')";

		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);

		if (rowsAffected < 1) {
			JdbcConnection.getInstance().printErrorMessageWithQuery("Error while insert value into payment entry table",
					query);
		}

		if (loan.getLoanPendingAmount() == debitAmount) {
			query = "UPDATE loan_data SET loan_pending_amount = loan_pending_amount - " + debitAmount
					+ ", loan_paid_amount =loan_paid_amount +" + debitAmount
					+ " , loan_status ='COMPLETED' WHERE loan_no = " + loan.getLoanNumber();
		} else {
			query = "UPDATE loan_data SET loan_pending_amount = loan_pending_amount - " + debitAmount
					+ ", loan_paid_amount =loan_paid_amount +" + debitAmount + " WHERE loan_no = "
					+ loan.getLoanNumber();
		}

		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);

		return (rowsAffected >= 1) ? true : false;
	}

	public HashMap<Integer, LoanData> getAllLoanList() {
		loanList.clear();
		query = "select * from loan_data";
		ResultSet result = JdbcConnection.getInstance().executeSelectQuery(query);
		try {
			while (result.next()) {
				LoanData loanData = new LoanData();
				loanData.setLoanNumber(result.getInt("loan_no"));
				loanData.setCustomerId(result.getInt("customer_id"));
				loanData.setLoanAmount(result.getFloat("loan_amount"));
				loanData.setLoanPendingAmount(result.getFloat("loan_pending_amount"));
				loanData.setLoanPaidAmount(result.getFloat("loan_paid_amount"));
				loanData.setLoanInterestPercentage(result.getFloat("loan_interest_percentage"));
				loanData.setLoanIntresetAmount(result.getFloat("loan_interest_amount"));
				loanData.setLoanTotalPayableAmount(result.getFloat("loan_total_payable_amount"));
				loanData.setLoanIssueDate(result.getDate("loan_issue_date").toLocalDate());
				loanList.put(loanData.getLoanNumber(), loanData);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loanList;
	}

	public HashMap<Integer, TransactionData> getTransactionReport(int customerLoanNumber) {
		transactionList.clear();
		query = "select * from loan_entry where loan_entry_status = 'ONLINE' and loan_no="+customerLoanNumber;
		ResultSet result = JdbcConnection.getInstance().executeSelectQuery(query);
		try {
			while (result.next()) {
				TransactionData transaction = new TransactionData();
				transaction.setTransactionId(result.getInt("loan_entry_id"));
				transaction.setCreditAmount(result.getFloat("loan_credit_amount"));
				transaction.setDebitAmount(result.getFloat("loan_paid_amount"));
				transaction.setPaymentDate(result.getDate("payment_date").toLocalDate());
				transactionList.put(transaction.getTransactionId(), transaction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactionList;
	}

}
