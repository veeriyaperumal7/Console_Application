package com.veeriyaperumal.loanapplication.createloan;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

import com.veeriyaperumal.loanapplication.model.Customer;
import com.veeriyaperumal.loanapplication.util.Utility;

public class LoanCreateView {

	private LoanCreateViewModel loanCreateViewModel;

	public LoanCreateView() {
		this.loanCreateViewModel = new LoanCreateViewModel(this);
	}

	public void startCredit() {
		printSeperatorLine();
		switch (chooseCustomer()) {
		case "New Customer":
			loanCreateViewModel.createCustomer();
			loanCreateViewModel.createLoan();
			printSeperatorLine();
			break;
		case "Existing Customer":
			loanCreateViewModel.getCustomerList();
			loanCreateViewModel.createLoan();
			printSeperatorLine();
			break;
		case "Exit":
			return;
		}
	}

	private String chooseCustomer() {
		printSeperatorLine();
		System.out.print("1 - New Customer\n2 - Existing Customer\n3 - Exit\nEnter your choice : ");
		int userChoice = getValidInput(1, 3, "Choose valid option : ");
		if (userChoice == 1) {
			return "New Customer";
		} else if (userChoice == 2) {
			return "Existing Customer";
		} else {
			return "Exit";
		}
	}

	float getLoanAmount() {
		printSeperatorLine();
		printUserWarningMessage("Enter the loan amount.\nNote loan amount must be greater than or equal to 100 : ");
		float userEnteredAmount = -1f;
		do {
			try {
				userEnteredAmount = Utility.getScanner().nextFloat();
				Utility.getScanner().nextLine();
				if (!(userEnteredAmount >= 100)) {
					showWrongSelectionMessage("Loan Amount must be greater than 100");
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				showWrongSelectionMessage("Loan Amount must be greater than 100");
				Utility.getScanner().next();
				continue;
			}
		} while (true);
		return userEnteredAmount;
	}

	float getLoanIntrestpercetage() {
		printSeperatorLine();
		printUserWarningMessage(
				"Enter the intrest percentage.\nNote intrest percentage must be less than or equal to 100");
		float userEnteredAmount = -1f;
		do {
			try {
				userEnteredAmount = Utility.getScanner().nextFloat();
				Utility.getScanner().nextLine();
				if (!((userEnteredAmount <= 100) && (userEnteredAmount >= 0))) {
					showWrongSelectionMessage(
							"Intrest percentage greater than or equal to 0 and less than or equal to 100");
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				showWrongSelectionMessage(
						"Intrest percentage greater than or equal to 0 and less than or equal to 100");
				Utility.getScanner().next();
				continue;
			}
		} while (true);
		return userEnteredAmount;
	}

	LocalDate getCustomerDob() {
		System.out.print("Enter your date of birth (yyyy-MM-dd): ");
		String userEnteredDob = "";
		LocalDate dob;

		do {
			try {
				userEnteredDob = Utility.getScanner().nextLine();
				dob = loanCreateViewModel.isValidDob(userEnteredDob);
				if (dob == null) {
					showWrongSelectionMessage("Enter Valid date of birth (yyyy-MM-dd): ");
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				showWrongSelectionMessage("Enter Valid date of birth (yyyy-MM-dd): ");
				Utility.getScanner().next();
				continue;
			}
		} while (true);

		return dob;
	}

	String getCustomerPlace() {
		System.out.print("Enter your place : ");
		String userEnteredPlace = "";
		do {
			try {
				userEnteredPlace = Utility.getScanner().nextLine();
				if (!loanCreateViewModel.isValidPlace(userEnteredPlace)) {
					showWrongSelectionMessage("Enter Valid  Place : ");
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				showWrongSelectionMessage("Enter Valid  Place : ");
				Utility.getScanner().next();
				continue;
			}
		} while (true);
		return userEnteredPlace;
	}

	String getCustomerMobileNumber() {
		System.out.print("Enter valid 10 digit mobile number : ");
		String userEnteredMobileNumber = "";
		do {
			try {
				userEnteredMobileNumber = Utility.getScanner().nextLine();
				if (!loanCreateViewModel.isValidMobileNumber(userEnteredMobileNumber)) {
					showWrongSelectionMessage("Enter Valid mobile Number : ");
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				showWrongSelectionMessage("Enter Valid mobile Number : ");
				Utility.getScanner().next();
				continue;
			}
		} while (true);
		return userEnteredMobileNumber;
	}

	String getCustomerGender() {
		int userEnteredChoice = -1;
		System.out.print("1 - Transgender\n2 - Female\n3 - Male\nEnter your choice : ");
		do {
			try {
				userEnteredChoice = Utility.getScanner().nextInt();
				Utility.getScanner().nextLine();
				if (!loanCreateViewModel.isValidGender(userEnteredChoice)) {
					showWrongSelectionMessage("Choose a valid option : ");
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				showWrongSelectionMessage("Choose a valid option : ");
				Utility.getScanner().next();
				continue;
			}
		} while (true);
		return (userEnteredChoice == 1) ? "Transgender" : (userEnteredChoice == 2) ? "Female" : "Male";
	}

	String getCustomerName() {
		printSeperatorLine();
		System.out.print("\nEnter Customer Name : ");
		String userEnteredName = "";
		do {
			try {
				userEnteredName = Utility.getScanner().nextLine();
				if (!loanCreateViewModel.isValidName(userEnteredName)) {
					showWrongSelectionMessage("Enter Valid Customer Name : ");
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				showWrongSelectionMessage("Enter Valid Customer Name : ");
				Utility.getScanner().next();
				continue;
			}
		} while (true);
		return userEnteredName;
	}

	public int getValidInput(int min, int max, String message) {
		int userEnteredChoice = -1;
		do {
			try {
				userEnteredChoice = Utility.getScanner().nextInt();
				Utility.getScanner().nextLine();
				if (!(userEnteredChoice >= min) && !(userEnteredChoice <= max)) {
					showWrongSelectionMessage(message);
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				showWrongSelectionMessage(message);
				Utility.getScanner().next();
				continue;
			}
		} while (true);
		return userEnteredChoice;
	}

	public void printSeperatorLine() {
		System.out.println(Utility.BOLD + Utility.CYAN
				+ "\n===========================================================================================================================================================\n"
				+ Utility.RESET);
	}

	private static void printTableSeparator() {
		System.out.println("+-----------------+----------------------+--------------+");
	}

	public void showWrongSelectionMessage(String message) {
		System.out.print(Utility.BOLD + Utility.RED + message + Utility.RESET);
	}

	public void printErrorMessage(String message) {
		System.out.print(Utility.BOLD + Utility.RED + message + Utility.RESET);
	}

	public void printUserWarningMessage(String message) {
		System.out.println(Utility.BOLD + Utility.YELLOW + message + Utility.RESET);
	}

	public void printCustomerList(HashMap<Integer, Customer> customerList) {
		if (customerList == null || customerList.isEmpty()) {
			printUserWarningMessage("There is no customer data available...");
			return;
		}

		printTableSeparator();
		System.out.printf("| %-15s | %-20s | %-12s |%n", "Customer ID", "Name", "Mobile");
		printTableSeparator();

		for (Map.Entry<Integer, Customer> entry : customerList.entrySet()) {
			Customer customer = entry.getValue();
			System.out.printf("| %-15d | %-20s | %-12s |%n", entry.getKey(), customer.getName(),
					customer.getMobileNumber());
		}

		printTableSeparator();
		getChoosenCustomerId();
	}

	private void getChoosenCustomerId() {
		System.out.print("Enter Customer Id : ");
		int userEnteredId = -1;
		do {
			try {
				userEnteredId = Utility.getScanner().nextInt();
				Utility.getScanner().nextLine();
				if (!loanCreateViewModel.isValidCustomerId(userEnteredId)) {
					showWrongSelectionMessage("Choose only a valid customer id : ");
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				showWrongSelectionMessage("Choose only a valid customer id : ");
				Utility.getScanner().next();
				continue;
			}
		} while (true);
		loanCreateViewModel.selectCustomer(userEnteredId);
	}

}
