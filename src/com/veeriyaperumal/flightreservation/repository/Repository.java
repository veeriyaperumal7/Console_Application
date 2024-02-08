package com.veeriyaperumal.flightreservation.repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import com.veeriyaperumal.flightreservation.model.Schedule;
import com.veeriyaperumal.flightreservation.model.Ticket;
import com.veeriyaperumal.flightreservation.model.User;

public class Repository implements Serializable {

	private static final long serialVersionUID = 1L;
	public static Repository repository;

	private HashMap<Integer, Schedule> schedules;
	private HashMap<Integer, User> users;
	private HashMap<Integer, Ticket> tickets;

	public HashMap<Integer, Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(HashMap<Integer, Schedule> schedules) {
		this.schedules = schedules;
	}

	public HashMap<Integer, User> getUsers() {
		return users;
	}

	public void setUsers(HashMap<Integer, User> users) {
		this.users = users;
	}

	public HashMap<Integer, Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(HashMap<Integer, Ticket> tickets) {
		this.tickets = tickets;
	}

	private Repository() {
		this.schedules = new HashMap();
		this.users = new HashMap();
		this.tickets = new HashMap();
	}

	public static Repository getInstance() {
		if (repository == null) {
			loadRepository();
		}
		return repository;
	}

	private static void loadRepository() {
		try {
			ObjectInputStream objInputStream = new ObjectInputStream(new FileInputStream("database.ser"));
			repository = (Repository) objInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			try {
				FileOutputStream fos = new FileOutputStream("database.ser");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			repository = new Repository();
		}
	}

	public boolean addSchedules(Schedule[] scheduleArray) {

		try {
			for (Schedule schedule : scheduleArray) {
				schedule.setScheduleId(schedules.size() + 1);
				schedules.put(schedule.getScheduleId(), schedule);
			}
			updateInDataBase();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private void updateInDataBase() {
		try {
			ObjectOutputStream objOutputStream = new ObjectOutputStream(new FileOutputStream("database.ser"));
			objOutputStream.writeObject(repository);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Ticket bookTickets(Schedule schedule, User[] passengers) {
		Ticket ticket = new Ticket();
		ticket.setPnr(1000 + tickets.size() + 1);
		ticket.setScheduleId(schedule.getScheduleId());
		ticket.setTicketStatus("CONFIRMED");
		for (User passenger : passengers) {
			if (schedule.getBookedCount() < schedule.getSeatCount()) {
				passenger.setTicketStatus("CNF");
			} else {
				passenger.setTicketStatus("WL");
			}
			schedule.setBookedCount(schedule.getBookedCount() + 1);
			users.put(passenger.getUserId(), passenger);
			ticket.getPassengers().add(passenger.getUserId());
		}
		tickets.put(ticket.getPnr(), ticket);
		return ticket;
	}
}
