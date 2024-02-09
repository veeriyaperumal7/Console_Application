package com.veeriyaperumal.flightreservation.bookedticketcount;

import java.util.HashMap;

import com.veeriyaperumal.flightreservation.model.Schedule;
import com.veeriyaperumal.flightreservation.model.Ticket;
import com.veeriyaperumal.flightreservation.model.User;
import com.veeriyaperumal.flightreservation.repository.Repository;

public class BookedTicketCountViewModel {

	private BookedTicketCountView bookedTicketCountView;

	BookedTicketCountViewModel(BookedTicketCountView bookedTicketCountView) {
		this.bookedTicketCountView = bookedTicketCountView;
	}

	HashMap<Integer, User> getPassengers() {
		return Repository.getInstance().getUsers();
	}

	HashMap<Integer, Schedule> getSchedules() {
		return Repository.getInstance().getSchedules();
	}

	HashMap<Integer, Ticket> getTickets() {
		return Repository.getInstance().getTickets();
	}

}
