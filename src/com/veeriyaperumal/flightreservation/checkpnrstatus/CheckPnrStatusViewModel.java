package com.veeriyaperumal.flightreservation.checkpnrstatus;

import java.util.Map;

import com.veeriyaperumal.flightreservation.model.Schedule;
import com.veeriyaperumal.flightreservation.model.Ticket;
import com.veeriyaperumal.flightreservation.model.User;
import com.veeriyaperumal.flightreservation.repository.Repository;

public class CheckPnrStatusViewModel {

	private CheckPnrStatusView checkPnrStatusView;

	public CheckPnrStatusViewModel(CheckPnrStatusView checkPnrStatusView) {
		this.checkPnrStatusView = checkPnrStatusView;
	}

	public Ticket getTicket(int pnrNumber) {
		return Repository.getInstance().getTicket(pnrNumber);
	}

	public Schedule getSchedule(int scheduleId) {
		return Repository.getInstance().getSchedule(scheduleId);
	}

	public Map<Integer, User> getUsers() {
		return Repository.getInstance().getUsers();
	}

}
