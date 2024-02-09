package com.veeriyaperumal.flightreservation.bookedticketcount;

import java.util.HashMap;

import com.veeriyaperumal.flightreservation.model.User;

public class BookedTicketCountView {
	
	private BookedTicketCountViewModel bookedTicketCountViewModel;
	
	public BookedTicketCountView() {
		this.bookedTicketCountViewModel = new BookedTicketCountViewModel(this);
	}

	public void showBookedCount() {
		HashMap<Integer,User> passengers =  bookedTicketCountViewModel.getPassengers();
		
	}

}
