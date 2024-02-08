package com.veeriyaperumal.flightreservation.createflightroutes;

import com.veeriyaperumal.flightreservation.base.BaseView;
import com.veeriyaperumal.flightreservation.model.Schedule;

public class CreateFlightRouteView extends BaseView {

	private CreateFlightRouteViewModel createFlightRouteViewModel;

	public CreateFlightRouteView() {
		this.createFlightRouteViewModel = new CreateFlightRouteViewModel(this);
	}

	public void createRoute() {
		print("No.of.schedule");
		int scheduleCount = getScanner().nextInt();
		if (scheduleCount < 1) {
			println("Schedule count less than 1...\nSo return to main menu...");
			return;
		}
		Schedule schedules[] = new Schedule[scheduleCount];
		for (int i = 0; i < scheduleCount; i++) {
			Schedule schedule = new Schedule();
			println("Schedule " + (i + 1) + ":");
			getScheduleDetails(schedule);
			schedules[i] = schedule;
			printSeperatorLine();
		}
		if(createFlightRouteViewModel.addSchedules(schedules)) {
			println("Successfully schedule added....");
		}else {
			println("Not schedule added....");
		}
	}

	private void getScheduleDetails(Schedule schedule) {
		print("\nFlight Number : ");
		schedule.setFlightNumber(getScanner().nextInt());
		getScanner().nextLine();
		print("\nFlight Name : ");
		schedule.setFlightName(getScanner().nextLine());

		print("\nFlight Depature Time : ");
		schedule.setDepatureTime(getScanner().nextLine());

		print("\nFlight Arrival Time : ");
		schedule.setArrivalTime(getScanner().nextLine());

		print("\nFlight Routes : ");
		String routes = getScanner().nextLine();

		String arr[] = routes.split(",");

		for (String str : arr) {
			schedule.getRoutes().add(str);
		}
		print("\nTotal\nSeats : ");
		schedule.setSeatCount(getScanner().nextInt());

		print("\nFare : ");
		schedule.setPrice(getScanner().nextFloat());

	}
}
