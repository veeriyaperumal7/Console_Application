package com.veeriyaperumal.flightreservation.searchpassenger;

import java.util.HashMap;
import java.util.Map;

import com.veeriyaperumal.flightreservation.model.Schedule;
import com.veeriyaperumal.flightreservation.model.Ticket;
import com.veeriyaperumal.flightreservation.model.User;
import com.veeriyaperumal.flightreservation.repository.Repository;

public class SearchPassengerViewModel {

	private SearchPassengerView searchPassengerView;

	SearchPassengerViewModel(SearchPassengerView searchPassengerView) {
		this.searchPassengerView = searchPassengerView;
	}

	public void searchPassenger(int passengerId) {
		User user = Repository.getInstance().getPassenger(passengerId);
		if (user == null) {
			searchPassengerView.failureMessage();
			return;
		}
		for (Map.Entry<Integer, Ticket> entry : Repository.getInstance().getTickets().entrySet()) {
			Ticket ticket = entry.getValue();
			for (int userId : ticket.getPassengers()) {
				if (userId == user.getUserId()) {
					searchPassengerView.successMeassage(user,
							Repository.getInstance().getSchedule(ticket.getScheduleId()).getFlightNumber());
					return;
				}
			}
		}
		searchPassengerView.failureMessage();
	}
}
