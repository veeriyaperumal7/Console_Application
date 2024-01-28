package com.veeriyaperumal.bikeshowroom;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.veeriyaperumal.bikeshowroom.model.Bike;

public class BikeShowroomView {
	private Scanner read = new Scanner(System.in);
	private Bike bike;
	private BikeViewModel bikeViewModel;

	//// ANSI escape codes for formatting color in console
	private static final String RESET = "\u001B[0m";
	private static final String GREEN = "\u001B[32m";
	private static final String YELLOW = "\u001B[33m";
	private static final String CYAN = "\u001B[36m";
	private static final String BOLD = "\u001B[1m";
	private static final String ROSECOLOR = "\u001B[38;5;207m";
	private static final String RED = "\u001B[31m";

	public BikeShowroomView() {
		this.bikeViewModel = new BikeViewModel(this);
	}

	public void start() {
		System.out.println(BOLD + CYAN + " ====================================================" + RESET);
		System.out.println(BOLD + CYAN + " |           " + ROSECOLOR + "WELCOME TO BIKE SHOW ROOM" + CYAN
				+ "              |" + RESET);
		System.out.println(BOLD + CYAN + " ====================================================\n\n" + RESET);
		boolean isExit = false;
		do {
			isExit = printChoices(bikeViewModel.getChoices());
		} while (!isExit);
		System.out.println(BOLD + CYAN + " ====================================================" + RESET);
		System.out.println(
				BOLD + CYAN + "      " + ROSECOLOR + "THANK YOU FOR VISITING US,SEE YOU SOON" + CYAN + "        ");
		System.out.println(BOLD + CYAN + " ====================================================" + RESET);
	}

	public boolean printChoices(List<String> choices) {
		int serialNo = 1;
		String topicName = "";
		if (choices == null) {
			return true;
		}
		switch (bikeViewModel.getTopOfChatHistory().getLevel()) {
		case 0:
			topicName = "BIKE COMPANY NAME";
			break;
		case 1:
			topicName = "BIKE SEGMENT";
			break;
		case 2:
			topicName = "BIKE NAMES";
			break;
		}
		if (bikeViewModel.getTopOfChatHistory().getLevel() != 3) { // Avoid for third level.Because bike data print in
																	// separate function so no need to print here.
			System.out.println(BOLD + CYAN + " ====================================================" + RESET);
			System.out.println(BOLD + CYAN + "                   " + ROSECOLOR + topicName + CYAN
					+ "                    " + RESET);
			System.out.println(BOLD + CYAN + " ====================================================" + RESET);
		}
		for (String options : choices) {
			System.out.println(" " + BOLD + serialNo++ + "." + options + RESET);
		}
		System.out.print(BOLD + CYAN + " Enter your choice : ");
		bikeViewModel.chooseLevel(getUserInput());
		return false;
	}

	public void printBikeData(String bikeData) {
		System.out.println(BOLD + CYAN + " ====================================================" + RESET);
		System.out.println(BOLD + CYAN + "                   " + ROSECOLOR + "BIKE DETAILS" + CYAN
				+ "                    " + RESET);
		System.out.println(BOLD + CYAN + " ====================================================" + RESET);
		System.out.println(BOLD + GREEN + "                "
				+ bikeViewModel.getTopOfChatHistory().getBikeName().toUpperCase() + RESET);
		System.out.println(BOLD + YELLOW + bikeData + "\n" + RESET);
	}

	public int getUserInput() {
		int userEnteredChoice = -1;
		do {
			try {
				userEnteredChoice = read.nextInt();
				if (bikeViewModel.validateInput(userEnteredChoice)) {
					break;
				}
				System.out.print(BOLD + RED + "Choose valid option : " + RESET);
			} catch (InputMismatchException e) {
				System.out.print(BOLD + RED + "Choose valid option : " + RESET);
				read.next();
				continue;
			}
		} while (true);
		return userEnteredChoice;
	}

	public void printErrorMessage(String errorMessage) {
		System.err.println(errorMessage);
	}
}