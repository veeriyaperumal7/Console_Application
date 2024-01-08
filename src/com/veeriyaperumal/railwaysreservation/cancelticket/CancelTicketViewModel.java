package com.veeriyaperumal.railwaysreservation.cancelticket;

import com.veeriyaperumal.railwaysreservation.model.Ticket;
import com.veeriyaperumal.railwaysreservation.repository.Repository;

public class CancelTicketViewModel {

	CancelTicketView cancelTicketView;
	private Ticket ticket = new Ticket();

	public CancelTicketViewModel(CancelTicketView cancelTicketView) {
		this.cancelTicketView = cancelTicketView;
	}

	public boolean isValidPnr(int userEnteredPnr) {
		Repository.getInstance().getTicketData(ticket, userEnteredPnr, "");
		return (ticket.getTripId() == -1) ? false : true;
	}

	public void cancelTicket() {
		Repository.getInstance().cancelTicket(ticket);
		cancelTicketView.printSeperatorLine();
		cancelTicketView.printUserWarningMessage("Ticket Successfully Cancelled");
		cancelTicketView.printSeperatorLine();
		cancelTicketView.showTicket();
	}

	public CancelTicketView getCancelTicketView() {
		return cancelTicketView;
	}

	public void setCancelTicketView(CancelTicketView cancelTicketView) {
		this.cancelTicketView = cancelTicketView;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}
