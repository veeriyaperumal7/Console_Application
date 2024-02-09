package com.veeriyaperumal.flightreservation.canceltickets;

import com.veeriyaperumal.flightreservation.base.BaseView;
import com.veeriyaperumal.flightreservation.model.Ticket;

public class CancelTicketView extends BaseView {
	private CancelTicketViewModel cancelTicketViewModel;

	public CancelTicketView() {
		this.cancelTicketViewModel = new CancelTicketViewModel(this);
	}

	public void cancelTicket() {
		print("Enter the PNR number : ");
		int pnrNumber = getScanner().nextInt();
		getScanner().nextLine();
		print("Are you sure want to cancel tickets?\n1 Yes\n2 No");
		if (getScanner().nextInt() == 2) {
			return;
		}
		cancelTicketViewModel.cancelTicket(pnrNumber);

	}

	void successMessage(Ticket ticket) {
		println("Ticket Cancelled Successfully.Your refund " + ticket.getPrice() + " will be processed soon.");
	}

	void failureMessage() {
		println("Given PNR number invalid....");
	}
}
