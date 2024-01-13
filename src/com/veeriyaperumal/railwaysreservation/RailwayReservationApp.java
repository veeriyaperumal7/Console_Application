package com.veeriyaperumal.railwaysreservation;

import java.util.InputMismatchException;

import com.veeriyaperumal.railwaysreservation.booktickets.BookTicketView;
import com.veeriyaperumal.railwaysreservation.cancelticket.CancelTicketView;
import com.veeriyaperumal.railwaysreservation.util.Utility;
import com.veeriyaperumal.railwaysreservation.viewticket.ShowBookedTicketView;

public class RailwayReservationApp {
	private BookTicketView bookTicketView;
	private ShowBookedTicketView showBookedTicketView;
	private CancelTicketView cancelTicketView;

	public RailwayReservationApp() {
		bookTicketView = new BookTicketView(this);
		showBookedTicketView = new ShowBookedTicketView(this);
		cancelTicketView = new CancelTicketView(this);
	}

	public static void main(String[] args) {
		RailwayReservationApp obj = new RailwayReservationApp();
		obj.start();
	}

	private void start() {
		int userInput;
		System.out.println(
				Utility.BOLD + Utility.CYAN + " ====================================================" + Utility.RESET);
		System.out.println(Utility.BOLD + Utility.CYAN + "            " + Utility.ROSECOLOR + "WELCOME TO IRCTC"
				+ Utility.CYAN + "              " + Utility.RESET);
		System.out.println(Utility.BOLD + Utility.CYAN + " ====================================================\n\n"
				+ Utility.RESET);

		do {
			printFeatures();
			userInput = getUserInput(1, 4);
			chooseFeature(userInput);
		} while (userInput != 4);
		System.out.println(
				Utility.BOLD + Utility.CYAN + " ====================================================" + Utility.RESET);
		System.out.println(Utility.BOLD + Utility.CYAN + "      " + Utility.ROSECOLOR
				+ "THANK YOU FOR VISITING US,SEE YOU SOON" + Utility.CYAN + "        ");
		System.out.println(
				Utility.BOLD + Utility.CYAN + " ====================================================" + Utility.RESET);

	}

	private void chooseFeature(int userInput) {
		switch (userInput) {
		case 1:
			bookTicketView.showBooking();
			break;
		case 2:
			cancelTicketView.showCancellation();
			break;
		case 3:
			showBookedTicketView.showBookedTicket();
			break;
		default:
			break;
		}
	}

	private void printFeatures() {
		System.out.println("1 - Book Tickets");
		System.out.println("2 - Cancel Tickets");
		System.out.println("3 - Check Ticket Status");
		System.out.print("4 - Exit\nChoose your option : ");
	}

	private int getUserInput(int minSelection, int maxSelection) {
		int userEnteredChoice = -1;
		do {
			try {
				userEnteredChoice = Utility.getScanner().nextInt();
				if (userEnteredChoice < minSelection || userEnteredChoice > maxSelection) {
					showWrongSelectionMessage("Choose Valid Option : ");
					Utility.getScanner().next();
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				showWrongSelectionMessage("Choose Valid Option : ");
				Utility.getScanner().next();
				continue;
			}
		} while (true);
		return userEnteredChoice;
	}

	public static void showWrongSelectionMessage(String message) {
		System.out.print(Utility.BOLD + Utility.RED + message + Utility.RESET);
	}

}
