package com.veeriyaperumal.loanapplication.createloan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import com.veeriyaperumal.loanapplication.model.Customer;
import com.veeriyaperumal.loanapplication.model.LoanData;
import com.veeriyaperumal.loanapplication.repository.Repository;

public class LoanCreateViewModel {

	private LoanCreateView loanCreateView;
	private Customer currentCustomer;
	private LoanData loan;
	private HashMap<Integer, Customer> customerList;

	LoanCreateViewModel(LoanCreateView loanCreateView) {
		this.loanCreateView = loanCreateView;
	}

	boolean isValidName(String customerName) {
		if (customerName.length() < 1) {
			return false;
		}
		return true;
	}

	boolean isValidGender(int userEnteredChoice) {
		if (userEnteredChoice >= 1 && userEnteredChoice <= 3) {
			return true;
		}
		return false;
	}

	boolean isValidMobileNumber(String number) {
		if (number.length() != 10) {
			return false;
		}
		for (char c : number.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	boolean isValidPlace(String userEnteredPlace) {
		if (userEnteredPlace.length() < 1) {
			return true;
		}
		return true;
	}

	LocalDate isValidDob(String userEnteredDob) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dob;

		try {
			dob = LocalDate.parse(userEnteredDob, formatter);

			// Check if the age is at least 18
			LocalDate eighteenYearsAgo = LocalDate.now().minusYears(18);
			if (dob.isAfter(eighteenYearsAgo)) {
				System.out.println("Age must be 18 years or older.");
				return null;
			}

			return dob;
		} catch (DateTimeParseException e) {
			System.out.println("Invalid Date of Birth format. Please enter in the format yyyy-MM-dd.");
			return null;
		}

	}

	void createCustomer() {
		currentCustomer = new Customer();
		currentCustomer.setName(loanCreateView.getCustomerName());
		currentCustomer.setMobileNumber(loanCreateView.getCustomerMobileNumber());
		currentCustomer.setDob(loanCreateView.getCustomerDob());
		currentCustomer.setGender(loanCreateView.getCustomerGender());
		currentCustomer.setPlace(loanCreateView.getCustomerPlace());
		currentCustomer.setCustomerId(Repository.getInstance().getNewCustomerId());
		loanCreateView.printSeperatorLine();
		if (Repository.getInstance().addCustomer(currentCustomer)) {
			loanCreateView.printUserWarningMessage("Customer data saved successfully...");
		} else {
			loanCreateView.printUserWarningMessage("Customer data not saved...");
		}
		loanCreateView.printSeperatorLine();
	}

	void createLoan() {
		loan = new LoanData();
		loan.setLoanIssueDate(LocalDate.now());
		loan.setLoanAmount(loanCreateView.getLoanAmount());
		loan.setLoanInterestPercentage(loanCreateView.getLoanIntrestpercetage());
		loan.setLoanIntresetAmount(calculateInterest(loan.getLoanAmount(), loan.getLoanInterestPercentage()));
		loan.setLoanTotalPayableAmount(loan.getLoanAmount() + loan.getLoanIntresetAmount());
		loan.setLoanPendingAmount(loan.getLoanTotalPayableAmount());
		loanCreateView.printSeperatorLine();
		if (Repository.getInstance().addLoan(loan, currentCustomer)) {
			loanCreateView.printUserWarningMessage("Loan data saved successfully...");
		} else {
			loanCreateView.printUserWarningMessage("Loan data not saved...");
		}
		loanCreateView.printSeperatorLine();
	}

	private float calculateInterest(float loanAmount, float interestRate) {
		// Convert interest rate percentage to decimal
		float interestRateDecimal = interestRate / 100.0f;

		float interestAmount = interestRateDecimal * loanAmount;

		return interestAmount;
	}

	void getCustomerList() {
		customerList = Repository.getInstance().getCustomerList();
		loanCreateView.printCustomerList(customerList);
	}

	public boolean isValidCustomerId(int customerId) {

		return customerList.containsKey(customerId);
	}

	public void selectCustomer(int userEnteredId) {
		currentCustomer = customerList.get(userEnteredId);
	}
}
