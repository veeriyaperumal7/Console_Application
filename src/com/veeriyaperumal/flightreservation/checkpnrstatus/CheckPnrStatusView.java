package com.veeriyaperumal.flightreservation.checkpnrstatus;

import java.util.Map;

import com.veeriyaperumal.flightreservation.base.BaseView;
import com.veeriyaperumal.flightreservation.model.Schedule;
import com.veeriyaperumal.flightreservation.model.Ticket;
import com.veeriyaperumal.flightreservation.model.User;

public class CheckPnrStatusView extends BaseView {

	private CheckPnrStatusViewModel checkPnrStatusViewModel;

	public CheckPnrStatusView() {
		this.checkPnrStatusViewModel = new CheckPnrStatusViewModel(this);
	}

	public void checkPnrStatus() {
		print("Enter the PNR number : ");
		int pnrNumber = getScanner().nextInt();
		getScanner().nextLine();
		printTickets(pnrNumber);

	}

	private void printTickets(int pnrNumber) {

		Ticket ticket = checkPnrStatusViewModel.getTicket(pnrNumber);
		if (ticket == null) {
			println("Given Pnr invalid...SO returned to main menu....");
			return;
		}
		Schedule schedule = checkPnrStatusViewModel.getSchedule(ticket.getScheduleId());
		Map<Integer, User> users = checkPnrStatusViewModel.getUsers();

		println("Flight Details \n\nFlight Number : " + schedule.getFlightNumber() + "\nFlight Name : "
				+ schedule.getFlightName() + "\nDeparture Time : " + schedule.getDepatureTime() + "\nArrival Time : "
				+ schedule.getArrivalTime() + "\nFrom : " + ticket.getFromStation() + "\nTo : " + ticket.getToStation()
				+ "\nPNR No : " + ticket.getPnr() + "\nTotal Fare : " + ticket.getPrice() + "\n\nUser Details : ");

		for (int userId : ticket.getPassengers()) {
			println(users.get(userId).toString());
		}
//		for (User user : passengers) {
//			println(user.toString());
//		}

	}

}
