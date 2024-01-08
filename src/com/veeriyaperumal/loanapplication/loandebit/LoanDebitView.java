package com.veeriyaperumal.loanapplication.loandebit;

import java.util.HashMap;
import java.util.InputMismatchException;

import com.veeriyaperumal.loanapplication.model.Customer;
import com.veeriyaperumal.loanapplication.model.LoanData;
import com.veeriyaperumal.loanapplication.util.Utility;

public class LoanDebitView {

	private LoanDebitViewModel loanDebitViewModel;

	public LoanDebitView() {
		this.loanDebitViewModel = new LoanDebitViewModel(this);
	}

	public void startDebit() {
		loanDebitViewModel.refreshData();
		loanDebitViewModel.getLoanData();
	}

	void printLoanDetails(HashMap<Integer, LoanData> loanList, HashMap<Integer, Customer> customerList) {
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
		loanDebitViewModel.setLoanData(getCustomerLoanNumber());
		loanDebitViewModel.debitAmount(getDebitAmount());
	}

	private float getDebitAmount() {
		printSeparatorLine();
		printUserWarningMessage("Enter the loan debit amount : ");
		float userEnteredAmount = -1f;
		do {
			try {
				userEnteredAmount = Utility.getScanner().nextFloat();
				Utility.getScanner().nextLine();
				if (!loanDebitViewModel.isValidDebitAmount(userEnteredAmount)) {
					showWrongSelectionMessage("Loan Amount must be greater than 0 and less than pending amount("
							+ loanDebitViewModel.getLoan().getLoanPendingAmount() + ") : ");
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				showWrongSelectionMessage("Loan Amount must be greater than 0 and less than pending amount("
						+ loanDebitViewModel.getLoan().getLoanPendingAmount() + ") : ");
				Utility.getScanner().next();
				continue;
			}
		} while (true);
		return userEnteredAmount;
	}

	int getCustomerLoanNumber() {
		System.out.print("Enter the loan no : ");
		int userEnteredLoanNumber = -1;
		do {
			try {
				userEnteredLoanNumber = Utility.getScanner().nextInt();
				Utility.getScanner().nextLine();
				if (!loanDebitViewModel.isValidLoanNo(userEnteredLoanNumber)) {
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
