package com.veeriyaperumal.flightreservation.booking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.veeriyaperumal.flightreservation.model.Schedule;
import com.veeriyaperumal.flightreservation.model.Ticket;
import com.veeriyaperumal.flightreservation.model.User;
import com.veeriyaperumal.flightreservation.repository.Repository;

public class BookingViewModel {

	private BookingView bookingView;
	private ArrayList<Schedule> flightScheduledList;

	public BookingViewModel(BookingView bookingView) {
		this.bookingView = bookingView;
	}

	ArrayList<Schedule> getAvailableSchedules(String fromStation, String toStation) {
		flightScheduledList = new ArrayList<>();
		HashMap<Integer, Schedule> scheduledList = Repository.getInstance().getSchedules();
		for (Map.Entry<Integer, Schedule> entry : scheduledList.entrySet()) {
			Schedule schedule = entry.getValue();
			int index = 0, fromStationIndex = -1, toStationIndex = -1;
			for (String route : schedule.getRoutes()) {
				if (route.equals(fromStation)) {
					fromStationIndex = index;
				} else if (route.equals(toStation)) {
					toStationIndex = index;
				}
				index++;
			}
			if (fromStationIndex != -1 && toStationIndex != -1 && fromStationIndex < toStationIndex) {
				flightScheduledList.add(schedule);
			}
		}

		return flightScheduledList;
	}

	public Ticket bookTickets(Schedule schedule, User[] passengers) {
		return Repository.getInstance().bookTickets(schedule, passengers);

	}

	public Schedule checkIsValidFlightNumber(int flightNumber) {
		for (Schedule schedule : flightScheduledList) {
			if (schedule.getFlightNumber() == flightNumber) {
				return schedule;
			}
		}
		return null;
	}

}
