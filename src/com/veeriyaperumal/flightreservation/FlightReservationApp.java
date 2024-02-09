package com.veeriyaperumal.flightreservation;

import java.util.ArrayList;

import com.veeriyaperumal.flightreservation.createflightroutes.CreateFlightRouteView;
import com.veeriyaperumal.flightreservation.base.BaseView;
import com.veeriyaperumal.flightreservation.booking.BookingView;
import com.veeriyaperumal.flightreservation.canceltickets.CancelTicketView;
import com.veeriyaperumal.flightreservation.checkpnrstatus.CheckPnrStatusView;
import com.veeriyaperumal.flightreservation.bookedticketcount.BookedTicketCountView;

public class FlightReservationApp extends BaseView {

	private CreateFlightRouteView createFlightRouteView;
	private BookingView bookingView;
	private CheckPnrStatusView checkPnrStatusView;
	private CancelTicketView cancelTicketView;
	private BookedTicketCountView bookedTicketCountView;
	private ArrayList<String> options;

	private FlightReservationApp() {
		this.createFlightRouteView = new CreateFlightRouteView();
		this.bookingView = new BookingView();
		this.checkPnrStatusView = new CheckPnrStatusView();
		this.bookedTicketCountView = new BookedTicketCountView();
		this.options = new ArrayList<>();
	}

	public static void main(String[] args) {
		FlightReservationApp app = new FlightReservationApp();
		app.start();
	}

	private void start() {
		println("WELCOME TO FLIGHT RESERVATION");
		loadOptions();
		int selectedOption;
		do {
			printOptions();
			selectedOption = getScanner().nextInt();
			chooseFeature(selectedOption);
			printSeperatorLine();

		} while (selectedOption != options.size());
		println("THANKS FOR USING FLIGHT RESERVATION");
	}

	private void chooseFeature(int selectedOption) {
		if (selectedOption < 1 || selectedOption > options.size()) {
			print("Please choose valid options : ");
		}
		switch (options.get(selectedOption - 1)) {
		case "Create Flight Route": {
			createFlightRouteView.createRoute();
			break;
		}
		case "Booking": {
			bookingView.bookTickets();
			break;
		}

		case "Get Pnr Status": {
			checkPnrStatusView.checkPnrStatus();
			break;
		}

		case "Cancel Ticket": {
			cancelTicketView.cancelTicket();
			break;
		}

		case "Booked Count": {
             bookedTicketCountView.showBookedCount();
		}
		}
	}

	private void printOptions() {
		int serialNo = 1;
		for (String str : options) {
			println(serialNo++ + " " + str);
		}
		println("Choose valid option : ");
	}

	private void loadOptions() {
		options.add("Create Flight Route");
		options.add("Booking");
		options.add("Get Pnr Status");
		options.add("Cancel Ticket");
		options.add("Booked Count");
		options.add("Exit");

	}
}
