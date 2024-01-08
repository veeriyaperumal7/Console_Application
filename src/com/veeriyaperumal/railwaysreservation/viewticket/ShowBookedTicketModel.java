package com.veeriyaperumal.railwaysreservation.viewticket;

import com.veeriyaperumal.railwaysreservation.model.Passenger;
import com.veeriyaperumal.railwaysreservation.model.Ticket;
import com.veeriyaperumal.railwaysreservation.model.Train;
import com.veeriyaperumal.railwaysreservation.repository.Repository;

public class ShowBookedTicketModel {
	private Passenger passenger;
	private Train train;
	private Ticket ticket = new Ticket();
	private ShowBookedTicketView showBookedTicketView;

	public ShowBookedTicketModel(ShowBookedTicketView showBookedTicketView) {
		this.showBookedTicketView = showBookedTicketView;
	}

	public boolean isValidPnr(int userEnteredPnr) {
		Repository.getInstance().getTicketData(ticket, userEnteredPnr);
		return (ticket.getTripId() == -1) ? false : true;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public ShowBookedTicketView getShowBookedTicketView() {
		return showBookedTicketView;
	}

	public void setShowBookedTicketView(ShowBookedTicketView showBookedTicketView) {
		this.showBookedTicketView = showBookedTicketView;
	}

}
