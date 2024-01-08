package com.veeriyaperumal.loanapplication.report;

import java.util.HashMap;
import java.util.InputMismatchException;

import com.veeriyaperumal.inventorymanagement.util.Utility;
import com.veeriyaperumal.loanapplication.model.Customer;
import com.veeriyaperumal.loanapplication.model.LoanData;
import com.veeriyaperumal.loanapplication.model.TransactionData;

public class LoanReportView {

	private LoanReportViewModel loanReportViewModel;

	public LoanReportView() {
		this.loanReportViewModel = new LoanReportViewModel(this);
	}

	public void startReport() {
		printSeparatorLine();
		switch (chooseReport()) {
		case 1:
			showPendingLoanReport();
			break;
		case 2:
			showCompletedLoanReport();
			break;
		case 3:
			loanWiseTransactionReport();
			break;
		case 4:
			loanSummaryReport();
		}
		Utility.printSeperatorLine();
	}

	private void loanSummaryReport() {
		// TODO Auto-generated method stub

	}

	private void loanWiseTransactionReport() {
		printUserWarningMessage("                LOAN TRANSACTION REPORT");
		printSeparatorLine();

		HashMap<Integer, LoanData> loanList = loanReportViewModel.getLoanAllReport();
		HashMap<Integer, Customer> customerList = loanReportViewModel.getCustomerListReport();

		if (loanList == null || loanList.size() < 1) {
			printUserWarningMessage("Loan Data Not Available.");
			return;
		}

		if (customerList == null || customerList.size() < 1) {
			printUserWarningMessage("Customer Data Not Available.");
			return;
		}

		printTableSeparator();
		System.out.printf("| %-9s | %-12s | %-19s | %-21s | %-18s | %-15s | %-15s | %-15s |\n", "Loan No",
				"Customer Id", "Loan Total Amount", "Loan Pending Amount", "Loan Paid Amount", "Customer Name",
				"Customer Mobile", "Loan Issue Date");
		printTableSeparator();
		for (LoanData loanData : loanList.values()) {
			Customer customer = customerList.get(loanData.getCustomerId());

			System.out.printf("| %-9d | %-12d | %-19.2f | %-21.2f | %-18.2f | %-15s | %-15s | %-15s |\n",
					loanData.getLoanNumber(), loanData.getCustomerId(), loanData.getLoanTotalPayableAmount(),
					loanData.getLoanPendingAmount(), loanData.getLoanPaidAmount(), customer.getName(),
					customer.getMobileNumber(), loanData.getLoanIssueDate());

			printTableSeparator();
		}
		printSeparatorLine();
		printTransationReport(loanReportViewModel.getTransactionReport(getCustomerLoanNumber()));

	}

	private void printTransationReport(HashMap<Integer, TransactionData> transactionReport) {
		System.out.println("+---------------+----------------+---------------+-----------------+");
		System.out.println("| TransactionId | Credit Amount  | Debit Amount  | Payment Date    |");
		System.out.println("+---------------+----------------+---------------+-----------------+");

		for (TransactionData transactionData : transactionReport.values()) {
			System.out.printf("| %-13d | %-14.2f | %-13.2f | %-15s |\n", transactionData.getTransactionId(),
					transactionData.getCreditAmount(), transactionData.getDebitAmount(),
					transactionData.getPaymentDate());
		}

		System.out.println("+---------------+----------------+---------------+-----------------+");
		printSeparatorLine();
	}

	int getCustomerLoanNumber() {
		System.out.print("Enter the loan no : ");
		int userEnteredLoanNumber = -1;
		do {
			try {
				userEnteredLoanNumber = Utility.getScanner().nextInt();
				Utility.getScanner().nextLine();
				if (!loanReportViewModel.isValidLoanNo(userEnteredLoanNumber)) {
					showWrongSelectionMessage("Enter valid loan no : ");
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				showWrongSelectionMessage("Enter valid loan no : ");
				Utility.getScanner().next();
				continue;
			}
		} while (true);
		return userEnteredLoanNumber;
	}

	private void showCompletedLoanReport() {

		printUserWarningMessage("                COMPLETED LOAN REPORT");
		printSeparatorLine();

		HashMap<Integer, LoanData> loanList = loanReportViewModel.getLoanCompletedReport();
		HashMap<Integer, Customer> customerList = loanReportViewModel.getCustomerListReport();
		if (loanList == null || loanList.size() < 1) {
			printUserWarningMessage("Loan Data Not Available.");
			return;
		}

		if (customerList == null || customerList.size() < 1) {
			printUserWarningMessage("Customer Data Not Available.");
			return;
		}

		printTableSeparator();
		System.out.printf("| %-9s | %-12s | %-19s | %-21s | %-18s | %-15s | %-15s | %-15s |\n", "Loan No",
				"Customer Id", "Loan Total Amount", "Loan Pending Amount", "Loan Paid Amount", "Customer Name",
				"Customer Mobile", "Loan Issue Date");
		printTableSeparator();
		for (LoanData loanData : loanList.values()) {
			Customer customer = customerList.get(loanData.getCustomerId());

			System.out.printf("| %-9d | %-12d | %-19.2f | %-21.2f | %-18.2f | %-15s | %-15s | %-15s |\n",
					loanData.getLoanNumber(), loanData.getCustomerId(), loanData.getLoanTotalPayableAmount(),
					loanData.getLoanPendingAmount(), loanData.getLoanPaidAmount(), customer.getName(),
					customer.getMobileNumber(), loanData.getLoanIssueDate());

			printTableSeparator();
		}
		printSeparatorLine();
	}

	private void showPendingLoanReport() {
		printUserWarningMessage("                PENDING LOAN REPORT");
		printSeparatorLine();
		HashMap<Integer, LoanData> loanList = loanReportViewModel.getLoanPendingReport();
		HashMap<Integer, Customer> customerList = loanReportViewModel.getCustomerListReport();
		if (loanList == null || loanList.size() < 1) {
			printUserWarningMessage("Loan Data Not Available.");
			return;
		}

		if (customerList == null || customerList.size() < 1) {
			printUserWarningMessage("Customer Data Not Available.");
			return;
		}

		printTableSeparator();
		System.out.printf("| %-9s | %-12s | %-19s | %-21s | %-18s | %-15s | %-15s | %-15s |\n", "Loan No",
				"Customer Id", "Loan Total Amount", "Loan Pending Amount", "Loan Paid Amount", "Customer Name",
				"Customer Mobile", "Loan Issue Date");
		printTableSeparator();
		for (LoanData loanData : loanList.values()) {
			Customer customer = customerList.get(loanData.getCustomerId());

			System.out.printf("| %-9d | %-12d | %-19.2f | %-21.2f | %-18.2f | %-15s | %-15s | %-15s |\n",
					loanData.getLoanNumber(), loanData.getCustomerId(), loanData.getLoanTotalPayableAmount(),
					loanData.getLoanPendingAmount(), loanData.getLoanPaidAmount(), customer.getName(),
					customer.getMobileNumber(), loanData.getLoanIssueDate());

			printTableSeparator();
		}
	}

	private int chooseReport() {
		int userEnteredChoice = -1;
		System.out.print(
				"1 - Pending Loan Report\n2 - Completed Loan Report\n3 - Loan Wise Transaction Report\nChoose your Option : ");
		do {
			try {
				userEnteredChoice = Utility.getScanner().nextInt();
				Utility.getScanner().nextLine();
				if (!(userEnteredChoice >= 1 && userEnteredChoice <= 3)) {
					Utility.showWrongSelectionMessage("Choose Valid option : ");
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				Utility.showWrongSelectionMessage("Choose Valid option : ");
				Utility.getScanner().next();
				continue;
			}
		} while (true);
		Utility.printSeperatorLine();
		return userEnteredChoice;
	}

	void printSeparatorLine() {
		System.out.println(Utility.BOLD + Utility.CYAN
				+ "\n===========================================================================================================================================================\n"
				+ Utility.RESET);
	}

	void printTableSeparator() {
		System.out.println(
				"+-----------+--------------+---------------------+-----------------------+--------------------+-----------------+-----------------+-----------------+");
	}

	void showWrongSelectionMessage(String message) {
		System.out.print(Utility.BOLD + Utility.RED + message + Utility.RESET);
	}

	void printErrorMessage(String message) {
		System.out.print(Utility.BOLD + Utility.RED + message + Utility.RESET);
	}

	void printUserWarningMessage(String message) {
		System.out.println(Utility.BOLD + Utility.YELLOW + message + Utility.RESET);
	}

}
