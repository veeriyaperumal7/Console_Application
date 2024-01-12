package com.veeriyaperumal.hotelbilling.base;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public abstract class BaseView extends BaseViewModel {

	private static final String GREEN = "\u001B[32m";
	private static final String YELLOW = "\u001B[33m";
	private static final String CYAN = "\u001B[36m";
	private static final String BOLD = "\u001B[1m";
	private static final String ROSECOLOR = "\u001B[38;5;207m";
	private static final String RED = "\u001B[31m";
	private static final String RESET = "\u001B[0m";
	private Scanner scanner = new Scanner(System.in);

	protected List<String> menuOptions = new ArrayList<>();

	protected Scanner getScanner() {
		return scanner;
	}

	public BaseView() {

	}

	public static void printOptionsTable(List<String> options, String header) {
		if (options == null || options.isEmpty()) {
			System.out.println("No options to display.");
			return;
		}

		System.out.println("+--------------------------------+");
		System.out.printf("| %-30s |\n", header);
		System.out.println("+--------------------------------+");

		int rowNo = 1;
		for (String option : options) {
			System.out.printf("| %-30s |\n", rowNo + " " + option);
			rowNo++;
		}

		System.out.println("+--------------------------------+");
	}

	protected void print(String message) {
		System.out.print(message);
	}

	protected void println(String message) {
		System.out.println(message);
	}

	protected void printHeader(String header) {
		System.out.println(BOLD + CYAN
				+ "\n===========================================================================================================================================================\n"
				+ RESET);
		System.out.println(BOLD + CYAN + "            " + ROSECOLOR + header + CYAN + "              " + RESET);
		System.out.println(BOLD + CYAN
				+ "\n===========================================================================================================================================================\n"
				+ RESET);
	}

	protected void printLineSeperator() {
		System.out.println(BOLD + CYAN
				+ "\n===========================================================================================================================================================\n"
				+ RESET);
	}

	protected void showWrongSelectionMessage(String message) {
		System.out.print(BOLD + RED + message + RESET);
	}

	protected void printErrorMessage(String message) {
		System.out.print(BOLD + RED + message + RESET);
	}

	protected void printUserWarningMessage(String message) {
		System.out.println(BOLD + YELLOW + message + RESET);
	}

	protected void printSuccesMessage(String message) {
		System.out.println(BOLD + GREEN + message + RESET);
	}

	protected int getIntegerInput(String message) {
		int userEnteredChoice = -1;
		do {
			try {
				userEnteredChoice = getScanner().nextInt();
				getScanner().nextLine();
				break;
			} catch (InputMismatchException e) {
				showWrongSelectionMessage(message);
				getScanner().next();
				continue;
			}
		} while (true);
		printLineSeperator();
		return userEnteredChoice;
	}

	protected int getIntegerInput(String message, int minSelectionValue, int maxSelectionValue) {
		int userEnteredChoice = -1;
		do {
			try {
				userEnteredChoice = getScanner().nextInt();
				getScanner().nextLine();
				if (!(userEnteredChoice >= minSelectionValue && userEnteredChoice <= maxSelectionValue)) {
					showWrongSelectionMessage(message);
				} else {
					break;
				}
				break;
			} catch (InputMismatchException e) {
				showWrongSelectionMessage(message);
				getScanner().next();
				continue;
			}
		} while (true);
		printLineSeperator();
		return userEnteredChoice;
	}

	protected float getFloatInput(String message) {
		float userEnteredChoice = -1f;
		do {
			try {
				userEnteredChoice = getScanner().nextFloat();
				getScanner().nextLine();
				break;
			} catch (InputMismatchException e) {
				showWrongSelectionMessage(message);
				getScanner().next();
				continue;
			}
		} while (true);
		printLineSeperator();
		return userEnteredChoice;
	}

	protected float getFloatInput(String message, float minSelectionValue, float maxSelectionValue) {
		float userEnteredChoice = -1f;
		do {
			try {
				userEnteredChoice = getScanner().nextFloat();
				getScanner().nextLine();
				if (!(userEnteredChoice >= minSelectionValue && userEnteredChoice <= maxSelectionValue)) {
					showWrongSelectionMessage(message);
				} else {
					break;
				}
				break;
			} catch (InputMismatchException e) {
				showWrongSelectionMessage(message);
				getScanner().next();
				continue;
			}
		} while (true);
		printLineSeperator();
		return userEnteredChoice;
	}

	protected String getStringInput(String message) {
		String userEnteredChoice = "";
		do {
			try {
				userEnteredChoice = getScanner().nextLine();
				break;
			} catch (InputMismatchException e) {
				showWrongSelectionMessage(message);
				getScanner().next();
				continue;
			}
		} while (true);
		printLineSeperator();
		return userEnteredChoice;
	}

	protected String getEmailInput(String message) {
		String userEnteredEmailAddress = "";
		do {
			try {
				userEnteredEmailAddress = getScanner().nextLine();
				if (isValidEmailAddress(userEnteredEmailAddress)) {
					break;
				} else {
					showWrongSelectionMessage(message);
				}

			} catch (InputMismatchException e) {
				showWrongSelectionMessage(message);
				getScanner().next();
				continue;
			}
		} while (true);
		printLineSeperator();
		return userEnteredEmailAddress;
	}

}
