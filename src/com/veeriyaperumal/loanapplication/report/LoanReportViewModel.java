package com.veeriyaperumal.loanapplication.report;

import java.util.HashMap;

import com.veeriyaperumal.loanapplication.model.Customer;
import com.veeriyaperumal.loanapplication.model.LoanData;
import com.veeriyaperumal.loanapplication.model.TransactionData;
import com.veeriyaperumal.loanapplication.repository.Repository;

public class LoanReportViewModel {

	LoanReportView loanReportView;
	private HashMap<Integer, Customer> customerList;
	private HashMap<Integer, LoanData> loanList;

	public LoanReportViewModel(LoanReportView loanReportView) {
		this.loanReportView = loanReportView;
	}

	public HashMap<Integer, LoanData> getLoanPendingReport() {
		return Repository.getInstance().getLoanList();
	}

	public HashMap<Integer, Customer> getCustomerListReport() {
		customerList = Repository.getInstance().getCustomerList();
		return customerList;
	}

	public HashMap<Integer, LoanData> getLoanCompletedReport() {
		loanList = Repository.getInstance().getCompletedLoanList();
		return loanList;
	}

	public HashMap<Integer, LoanData> getLoanAllReport() {
		loanList = Repository.getInstance().getAllLoanList();
		return loanList;
	}

	public boolean isValidLoanNo(int userEnteredLoanNumber) {
		return loanList.containsKey(userEnteredLoanNumber);
	}

	public HashMap<Integer, TransactionData> getTransactionReport(int customerLoanNumber) {
		return Repository.getInstance().getTransactionReport(customerLoanNumber);
	}

}
