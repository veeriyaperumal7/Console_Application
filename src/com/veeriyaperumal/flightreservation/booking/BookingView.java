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
		Schedule schedule =  bookingViewModel.checkIsValidFlightNumber(flightNumber);
		if(schedule==null) {
			println("The enterd flight number is wrong...So returned to main menu....");
		}
		User user[] = getPassengerData();
		if (user == null) {
			return;
		}
		getPayment
		Ticket ticket = bookingViewModel.bookTickets(schedule,user);
	
		
	}

	private User[] getPassengerData() {
		print("No.of.Passenger : ");
		int passengerCount = getScanner().nextInt();
		if (passengerCount < 1) {
			println("Passenger count less than 1...\nSo return to main menu...");
			return null;
		}
		User passengers[] = new User[passengerCount];
		for (int i = 0; i < passengerCount; i++) {
			User passenger = new User();
			print("Name : ");
			passenger.setName(getScanner().nextLine());
			print("/nAge : ");
			passenger.setAge(getScanner().nextInt());
			print("/nGender : ");
			passenger.setGender(getScanner().nextLine());
			print("/nId : ");
			passenger.setUserId(getScanner().nextInt());
			passengers[i] = passenger;
			printSeperatorLine();

		}
		return passengers;
	}

}
