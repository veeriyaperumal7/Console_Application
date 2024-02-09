package com.veeriyaperumal.flightreservation.canceltickets;

import java.util.HashMap;

import com.veeriyaperumal.flightreservation.model.Schedule;
import com.veeriyaperumal.flightreservation.model.Ticket;
import com.veeriyaperumal.flightreservation.model.User;
import com.veeriyaperumal.flightreservation.repository.Repository;

public class CancelTicketViewModel {

	private CancelTicketView cancelTicketView;

	public CancelTicketViewModel(CancelTicketView cancelTicketView) {
		this.cancelTicketView = cancelTicketView;
	}

	public void cancelTicket(int pnrNumber) {
		Ticket ticket = Repository.getInstance().getTicket(pnrNumber);
		if (ticket == null) {
			cancelTicketView.failureMessage();
		}
		HashMap<Integer, User> passengers = Repository.getInstance().getUsers();
		Schedule schedule = Repository.getInstance().getSchedule(ticket.getScheduleId());

		ticket.setTicketStatus("CANCELLED");
		for (int userId : ticket.getPassengers()) {
			passengers.get(userId).setTicketStatus("CANCELLED");
		}
		schedule.setBookedCount(schedule.getBookedCount() - ticket.getPassengers().size());
		cancelTicketView.successMessage(ticket);
	}

}
