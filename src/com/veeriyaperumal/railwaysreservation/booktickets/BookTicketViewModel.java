package com.veeriyaperumal.railwaysreservation.booktickets;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.veeriyaperumal.railwaysreservation.model.Passenger;
import com.veeriyaperumal.railwaysreservation.model.Train;
import com.veeriyaperumal.railwaysreservation.repository.Repository;
import com.veeriyaperumal.railwaysreservation.util.Utility;

public class BookTicketViewModel {
	private final int MAX_DAYS = 8;// This refer how many advance days we can book from now current date+1 to
	// MAX_DAYS
	private BookTicketView bookTicketView;
	private Train userSelectedTrain;
	private Passenger currentPassenger;

	public BookTicketViewModel(BookTicketView bookTicketView) {
		this.bookTicketView = bookTicketView;
	}

	public BookTicketView getBookTicketView() {
		return bookTicketView;
	}

	public void setBookTicketView(BookTicketView bookTicketView) {
		this.bookTicketView = bookTicketView;
	}

	public ArrayList<Train> getScheduledTrainList() {
		return Repository.getInstance().getScheduledTrainList();
	}

	public int getMAX_DAYS() {
		return MAX_DAYS;
	}

	public boolean isValidBookingDate(int userEnteredChoice) {
		if (userEnteredChoice >= 1 && userEnteredChoice <= MAX_DAYS) {
			return true;
		}
		return false;
	}

	public boolean isValidTrain(int userEnteredChoice) {
		for (Train train : Repository.getInstance().getScheduledTrainList()) {
			if (train.getTrainNo() == userEnteredChoice) {
				return true;
			}
		}
		return false;
	}

	ArrayList<Train> getScheduledTrain(String bookingDate) {
		return Repository.getInstance().getScheduledTrainList(bookingDate);
	}

	private int getTotalSeatsAvailableInSeater() {
		return userSelectedTrain.getSeaterAvailableCount() + userSelectedTrain.getSeaterRacAvailableCount()
				+ userSelectedTrain.getSeaterWaitingListAvailableCount();
	}

	private int getSeaterTicketCount() {
		bookTicketView.printSeperatorLine();
		System.out.print("Enter the how many ticket count : ");
		int passengerCount = bookTicketView.getValidInput(1, 5, "Ticket count must be greater than 0 and less than 6.");
		int availableSeatsInTrainCount = getTotalSeatsAvailableInSeater();
		if (passengerCount > availableSeatsInTrainCount) {
			bookTicketView.printUserWarningMessage("Currently available ticket count is " + availableSeatsInTrainCount
					+ ".\nSo are you okay with current ticket count ?\n1 - YES\n2- NO");
			if (bookTicketView.getValidInput(1, 2, "Choose valid option.") == 1) {
				return availableSeatsInTrainCount;
			} else {
				return -1;
			}
		} else {
			return passengerCount;
		}
	}

	public void bookSeater() {
		int passengerCount = getSeaterTicketCount();
		String ticketStatus = "";
		boolean isBooked = true;
		if (passengerCount == -1) {
			return;
		}

		for (int i = 1; i <= passengerCount; i++) {
			if (userSelectedTrain.getSeaterAvailableCount() >= 1) {
				if (Repository.getInstance().bookTicketInSeater(createPassenger(), userSelectedTrain)) {
					userSelectedTrain.setSeaterAvailableCount(userSelectedTrain.getSeaterAvailableCount() - 1);
					ticketStatus = "Seater - Confirmed";
					setPNR(currentPassenger, userSelectedTrain);
				} else {
					bookTicketView.printErrorMessage("Error while booking a ticket.");
					isBooked = false;
				}

			} else if (userSelectedTrain.getSeaterRacAvailableCount() >= 1) {
				if (Repository.getInstance().bookTicketInRacSeater(createPassenger(), userSelectedTrain)) {
					userSelectedTrain.setSeaterRacAvailableCount(userSelectedTrain.getSeaterRacAvailableCount() - 1);
					ticketStatus = "Seater - RAC";
					setPNR(currentPassenger, userSelectedTrain);
				} else {
					bookTicketView.printErrorMessage("Error while booking a ticket.");
					isBooked = false;
				}

			} else if (userSelectedTrain.getSeaterWaitingListAvailableCount() >= 1) {
				if (Repository.getInstance().bookTicketInWaitingListSeater(createPassenger(), userSelectedTrain)) {
					userSelectedTrain.setSeaterWaitingListAvailableCount(
							userSelectedTrain.getSeaterWaitingListAvailableCount() - 1);
					ticketStatus = "Seater - Waiting List";
					setPNR(currentPassenger, userSelectedTrain);
				} else {
					bookTicketView.printErrorMessage("Error while booking a ticket.");
					isBooked = false;
				}
			} else {
				System.out.print(
						Utility.BOLD + Utility.CYAN + "Currently no seats available in that sorry." + Utility.RESET);
				break;
			}

			bookTicketView.printSeperatorLine();
			if (isBooked) {
				bookTicketView.printUserWarningMessage("Ticket Successfully booked for " + currentPassenger.getName()
						+ "\nPNR : " + currentPassenger.getPnr() + "\nStatus : " + ticketStatus);
			} else {
				bookTicketView.printUserWarningMessage(
						"Ticket  booking falied for " + currentPassenger.getName() + "\nStatus : Failed");
			}
			bookTicketView.printSeperatorLine();
		}

	}

	private void setPNR(Passenger currentPassenger, Train userSelectedTrain) {
		currentPassenger.setPnr(Repository.getInstance().getPNR(currentPassenger, userSelectedTrain));

	}

	private int getTotalSeatsAvailableInSleeper() {
		return userSelectedTrain.getSleeperAvailableCount() + userSelectedTrain.getSleeperRacAvailableCount()
				+ userSelectedTrain.getSleeperWaitingListAvailableCount();
	}

	private int getSleeperTicketCount() {
		bookTicketView.printSeperatorLine();
		System.out.print("Enter the how many ticket count : ");
		int passengerCount = bookTicketView.getValidInput(1, 5, "Ticket count must be greater than 0 and less than 6.");
		int availableSeatsInTrainCount = getTotalSeatsAvailableInSleeper();
		if (passengerCount > availableSeatsInTrainCount) {
			bookTicketView.printUserWarningMessage("Currently available ticket count is " + availableSeatsInTrainCount
					+ ".\nSo are you okay with current ticket count ?\n1 - YES\n 2- NO");
			if (bookTicketView.getValidInput(1, 2, "Choose valid option.") == 1) {
				return availableSeatsInTrainCount;
			} else {
				return -1;
			}
		} else {
			return passengerCount;
		}
	}

	public void bookSleeper() {
		int passengerCount = getSleeperTicketCount();
		String ticketStatus = "";
		boolean isBooked = true;
		if (passengerCount == -1) {
			return;
		}

		for (int i = 1; i <= passengerCount; i++) {
			if (userSelectedTrain.getSleeperAvailableCount() >= 1) {
				if (Repository.getInstance().bookTicketInSleeper(createPassenger(), userSelectedTrain)) {
					userSelectedTrain.setSleeperAvailableCount(userSelectedTrain.getSleeperAvailableCount() - 1);
					ticketStatus = "Sleeper - Confirmed";
					setPNR(currentPassenger, userSelectedTrain);
				} else {
					bookTicketView.printErrorMessage("Error while booking a ticket.");
					isBooked = false;
				}

			} else if (userSelectedTrain.getSleeperRacAvailableCount() >= 1) {
				if (Repository.getInstance().bookTicketInRacSleeper(createPassenger(), userSelectedTrain)) {
					userSelectedTrain.setSleeperRacAvailableCount(userSelectedTrain.getSleeperRacAvailableCount() - 1);
					ticketStatus = "Sleeper - RAC";
					setPNR(currentPassenger, userSelectedTrain);
				} else {
					bookTicketView.printErrorMessage("Error while booking a ticket.");
					isBooked = false;
				}

			} else if (userSelectedTrain.getSleeperWaitingListAvailableCount() >= 1) {
				if (Repository.getInstance().bookTicketInWaitingListSleeper(createPassenger(), userSelectedTrain)) {
					userSelectedTrain.setSleeperWaitingListAvailableCount(
							userSelectedTrain.getSleeperWaitingListAvailableCount() - 1);
					ticketStatus = "Sleeper - Waiting List";
					setPNR(currentPassenger, userSelectedTrain);
				} else {
					bookTicketView.printErrorMessage("Error while booking a ticket.");
					isBooked = false;
				}
			} else {
				System.out.print(
						Utility.BOLD + Utility.CYAN + "Currently no seats available in that sorry." + Utility.RESET);
				break;
			}

			bookTicketView.printSeperatorLine();
			if (isBooked) {
				bookTicketView.printUserWarningMessage("Ticket Successfully booked for " + currentPassenger.getName()
						+ "\nPNR : " + currentPassenger.getPnr() + "\nStatus : " + ticketStatus);
			} else {
				bookTicketView.printUserWarningMessage(
						"Ticket  booking falied for " + currentPassenger.getName() + "\nStatus : Failed");
			}
			bookTicketView.printSeperatorLine();
		}
	}

	public Train getUserSelectedTrain() {
		return userSelectedTrain;
	}

	public void setUserSelectedTrain(Train userSelectedTrain) {
		this.userSelectedTrain = userSelectedTrain;
	}

	public boolean isValidName(String passengerName) {
		if (passengerName.length() < 1) {
			return false;
		}
		return true;
	}

	public boolean isValidGender(int userEnteredChoice) {
		if (userEnteredChoice >= 1 && userEnteredChoice <= 3) {
			return true;
		}
		return false;
	}

	public boolean isValidMobileNumber(String number) {
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

	public boolean isValidPlace(String userEnteredPlace) {
		if (userEnteredPlace.length() < 1) {
			return true;
		}
		return true;
	}

	public LocalDate isValidDob(String userEnteredDob) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dob;

		try {
			dob = LocalDate.parse(userEnteredDob, formatter);
			if (dob.isAfter(LocalDate.now())) {
				System.out.println("Future dates are not allowed.");
				return null;
			}

			return dob;
		} catch (DateTimeParseException e) {
			System.out.println("Invalid Date of Birth format. Please enter in the format yyyy-MM-dd.");
			return null;
		}
	}

	public Passenger createPassenger() {
		currentPassenger = new Passenger();
		currentPassenger.setName(bookTicketView.getPassengerName());
		currentPassenger.setGender(bookTicketView.getPassengerGender());
		currentPassenger.setMobileNumber(bookTicketView.getPassengerMobileNumber());
		currentPassenger.setPlace(bookTicketView.getPassengerPlace());
		currentPassenger.setDob(bookTicketView.getPassengerDob());
		return currentPassenger;
	}

}
