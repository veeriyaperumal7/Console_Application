package com.veeriyaperumal.flightreservation.createflightroutes;

import com.veeriyaperumal.flightreservation.model.Schedule;
import com.veeriyaperumal.flightreservation.repository.Repository;

public class CreateFlightRouteViewModel {

	private CreateFlightRouteView createFlightRouteView;

	public CreateFlightRouteViewModel(CreateFlightRouteView createFlightRouteView) {
		this.createFlightRouteView = createFlightRouteView;
	}

	public boolean addSchedules(Schedule[] schedules) {
		return Repository.getInstance().addSchedules(schedules);
	}

}
