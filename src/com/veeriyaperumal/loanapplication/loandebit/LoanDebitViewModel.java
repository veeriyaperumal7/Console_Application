package com.veeriyaperumal.loanapplication.loandebit;

import java.util.HashMap;

import com.veeriyaperumal.loanapplication.model.Customer;
import com.veeriyaperumal.loanapplication.model.LoanData;
import com.veeriyaperumal.loanapplication.repository.Repository;

public class LoanDebitViewModel implements Runnable {

	private LoanDebitView loanDebitView;
	private HashMap<Integer, Customer> customerList;
	private HashMap<Integer, LoanData> loanList;
	private LoanData loan;

	LoanDebitViewModel(LoanDebitView loanDebitView) {
		this.loanDebitView = loanDebitView;
	}

	public void fetchCustomerList() {
		customerList = Repository.getInstance().getCustomerList();
	}

	void fetchLoanData() {
		loanList = Repository.getInstance().getLoanList();
	}

	void getLoanData() {
		loanDebitView.printLoanDetails(loanList, customerList);
	}

	boolean isValidLoanNo(int userEnteredLoanNumber) {
		return loanList.containsKey(userEnteredLoanNumber);
	}

	void debitAmount(float debitAmount) {
		if (Repository.getInstance().debitAmount(loan, debitAmount)) {
			loanDebitView.printUserWarningMessage("Amount debited Successfully...");
		} else {
			loanDebitView.printUserWarningMessage("Amount not debited...");
		}
		loanDebitView.printSeparatorLine();
	}

	LoanData getLoan() {
		return loan;
	}

	void setLoanData(int customerLoanNumber) {
		this.loan = loanList.get(customerLoanNumber);
	}

	boolean isValidDebitAmount(float userEnteredAmount) {
		return (userEnteredAmount > 0 && userEnteredAmount <= loan.getLoanPendingAmount());
	}

	void refreshData() {
		this.run();
	}

	@Override
	public void run() {
		fetchCustomerList();
		fetchLoanData();
	}

}
