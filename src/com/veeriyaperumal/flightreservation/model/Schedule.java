package com.veeriyaperumal.flightreservation.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Schedule implements Serializable {

	private static final long serialVersionUID = 1L;
	private int scheduleId;
	private int flightNumber;
	private LocalDate date;
	private String arrivalTime, depatureTime;
	private String flightName;
	private ArrayList<String> routes = new ArrayList<String>();
	private int seatCount;
	private int bookedCount;
	private float price;

	@Override
	public String toString() {
		return "Flight number : " + flightNumber + "\n" + "Name : " + flightName + "\nDeparture time : " + depatureTime
				+ "\nArrival time : " + arrivalTime + "\nFare :" + String.valueOf(price) + "\nTicket count : "
				+ String.valueOf(seatCount - bookedCount);
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDepatureTime() {
		return depatureTime;
	}

	public void setDepatureTime(String depatureTime) {
		this.depatureTime = depatureTime;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public ArrayList<String> getRoutes() {
		return routes;
	}

	public void setRoutes(ArrayList<String> routes) {
		this.routes = routes;
	}

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public int getBookedCount() {
		return bookedCount;
	}

	public void setBookedCount(int bookedCount) {
		this.bookedCount = bookedCount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
