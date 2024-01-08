package com.veeriyaperumal.railwaysreservation.model;

import java.sql.Time;
import java.util.Date;

public class Ticket {
	private int pnrNo, tripId, passengerId, trainNo;
	private float passengerPaidAmount;
	private String passengerTicketStatus, passengerClass, journeyStatus, trainName, departurePlace, arrivalPlace,
			passengerName, passengerMobile;
	private Date startDate, endDate;
	private Time startTime, endTime;

	public int getPnrNo() {
		return pnrNo;
	}

	public void setPnrNo(int pnrNo) {
		this.pnrNo = pnrNo;
	}

	public int getTripId() {
		return tripId;
	}

	public void setTripId(int tripId) {
		this.tripId = tripId;
	}

	public int getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public float getPassengerPaidAmount() {
		return passengerPaidAmount;
	}

	public void setPassengerPaidAmount(float passengerPaidAmount) {
		this.passengerPaidAmount = passengerPaidAmount;
	}

	public String getPassengerTicketStatus() {
		return passengerTicketStatus;
	}

	public void setPassengerTicketStatus(String passengerTicketStatus) {
		this.passengerTicketStatus = passengerTicketStatus;
	}

	public String getPassengerClass() {
		return passengerClass;
	}

	public void setPassengerClass(String passengerClass) {
		this.passengerClass = passengerClass;
	}

	public String getJourneyStatus() {
		return journeyStatus;
	}

	public void setJourneyStatus(String journeyStatus) {
		this.journeyStatus = journeyStatus;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getDeparturePlace() {
		return departurePlace;
	}

	public void setDeparturePlace(String departurePlace) {
		this.departurePlace = departurePlace;
	}

	public String getArrivalPlace() {
		return arrivalPlace;
	}

	public void setArrivalPlace(String arrivalPlace) {
		this.arrivalPlace = arrivalPlace;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public int getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
	}

	public String getPassengerMobile() {
		return passengerMobile;
	}

	public void setPassengerMobile(String passengerMobile) {
		this.passengerMobile = passengerMobile;
	}

}
