package com.veeriyaperumal.flightreservation.searchpassenger;

import com.veeriyaperumal.flightreservation.base.BaseView;
import com.veeriyaperumal.flightreservation.model.User;

public class SearchPassengerView extends BaseView{

	private SearchPassengerViewModel searchPassengerViewModel;

	public SearchPassengerView() {
		this.searchPassengerViewModel = new SearchPassengerViewModel(this);
	}

	public void searchPassenger() {
		print("Enter the Passenger Id : ");
		int passengerId = getScanner().nextInt();
		getScanner().nextLine();
		searchPassengerViewModel.searchPassenger(passengerId);
	}

	public void failureMessage() {
		println("Given passenger id not found...");
	}

	public void successMeassage(User user, int flightNumber) {
		println(user.toString()+"\nFlight Number : "+flightNumber);
	}

}
