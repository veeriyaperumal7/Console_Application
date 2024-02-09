package com.veeriyaperumal.flightreservation.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;
	private int pnr;
	private int ScheduleId;
	private ArrayList<Integer> passengers = new ArrayList();
	private String ticketStatus, fromStation, toStation;
	private float price;

	public int getPnr() {
		return pnr;
	}

	public void setPnr(int pnr) {
		this.pnr = pnr;
	}

	public int getScheduleId() {
		return ScheduleId;
	}

	public void setScheduleId(int scheduleId) {
		ScheduleId = scheduleId;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public ArrayList<Integer> getPassengers() {
		return passengers;
	}

	public void setPassengers(ArrayList<Integer> passengers) {
		this.passengers = passengers;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getFromStation() {
		return fromStation;
	}

	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}

	public String getToStation() {
		return toStation;
	}

	public void setToStation(String toStation) {
		this.toStation = toStation;
	}

}
