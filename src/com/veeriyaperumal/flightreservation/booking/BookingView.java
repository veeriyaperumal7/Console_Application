package com.veeriyaperumal.flightreservation.booking;

import java.util.ArrayList;

import com.veeriyaperumal.flightreservation.base.BaseView;
import com.veeriyaperumal.flightreservation.model.Schedule;
import com.veeriyaperumal.flightreservation.model.Ticket;
import com.veeriyaperumal.flightreservation.model.User;

public class BookingView extends BaseView {

	private BookingViewModel bookingViewModel;

	public BookingView() {
		this.bookingViewModel = new BookingViewModel(this);
	}

	public void bookTickets() {
		print("From Station : ");
		String fromStation = getScanner().nextLine();
		print("To Station : ");
		String toStation = getScanner().nextLine();
		printAvailableSchedules(fromStation, toStation);

	}

	private void printAvailableSchedules(String fromStation, String toStation) {
		ArrayList<Schedule> availbleSchedule = bookingViewModel.getAvailableSchedules(fromStation, toStation);
		if (availbleSchedule.size() < 1) {
			println("Flights are not available from given station....");
			return;
		}
		println("Available flights : \n");
		for (Schedule schedule : availbleSchedule) {
			println(schedule.toString());
			printSeperatorLine();
		}
		print("\nEnter Flight number : ");
		int flightNumber = getScanner().nextInt();
		getScanner().nextLine();
		Schedule schedule = bookingViewModel.checkIsValidFlightNumber(flightNumber);
		if (schedule == null) {
			println("The enterd flight number is wrong...So returned to main menu....");
			return;
		}
		User user[] = getPassengerData();
		if (user == null) {
			return;
		}
		println("The total ticket price is : " + schedule.getPrice() * user.length);
		if (getPayment().equals("CANCEL")) {
			print("Ticket cancelled...");
			return;
		}
		Ticket ticket = bookingViewModel.bookTickets(schedule, user, fromStation, toStation);
		if (ticket.getPassengers().size() < 1) {
			println("Ticket booking failed...");
		} else {
			println("Ticket(s) booked successfully...\nTicket Details\n");
			printTickets(ticket, schedule, user);
		}

	}

	private void printTickets(Ticket ticket, Schedule schedule, User[] passengers) {

		println("Flight Details \n\nFlight Number : " + schedule.getFlightNumber() + "\nFlight Name : "
				+ schedule.getFlightName() + "\nDeparture Time : " + schedule.getDepatureTime() + "\nArrival Time : "
				+ schedule.getArrivalTime() + "\nFrom : " + ticket.getFromStation() + "\nTo : " + ticket.getToStation()
				+ "\nPNR No : " + ticket.getPnr() + "\nTotal Fare : " + ticket.getPrice() + "\n\nUser Details : ");

		for (User user : passengers) {
			println(user.toString());
		}

	}

	private String getPayment() {
		println("1.Pay\n2.Cancel\nChoose your option : ");
		if (getScanner().nextInt() == 1) {
			getScanner().nextLine();
			return "Pay";
		} else {
			getScanner().nextLine();
			return "Cancel";
		}
	}

	private User[] getPassengerData() {
		print("No.of.Passenger : ");
		int passengerCount = getScanner().nextInt();
		if (passengerCount < 1) {
			println("Passenger count less than 1...\nSo return to main menu...");
			return null;
		}
		getScanner().nextLine();
		User passengers[] = new User[passengerCount];
		for (int i = 0; i < passengerCount; i++) {
			User passenger = new User();
			print("Name : ");
			passenger.setName(getScanner().nextLine());
			print("Age : ");
			passenger.setAge(getScanner().nextInt());
			print("Gender : ");
			getScanner().nextLine();
			passenger.setGender(getScanner().nextLine());
			print("Id : ");
			passenger.setUserId(getScanner().nextInt());
			getScanner().nextLine();
			passengers[i] = passenger;
			printSeperatorLine();

		}
		return passengers;
	}

}
