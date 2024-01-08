package com.veeriyaperumal.railwaysreservation.model;

import java.sql.Time;
import java.util.Date;

public class Train {
	private int trainNo, seaterAvailableCount, sleeperAvailableCount, seaterWaitingListAvailableCount,
			seaterRacAvailableCount, sleeperWaitingListAvailableCount, sleeperRacAvailableCount, seaterCarraigeCount,
			sleeperCarraigeCount, sleeperCountPerCarriage, seaterCountPerCarriage,tripId;

	private String trainName, departurePlace, arrivalPlace;
	private Date startDate, endDate;
	private Time startTime, endTime;
	private float seaterPrice, sleeperPrice;

	public int getSeaterWaitingListAvailableCount() {
		return seaterWaitingListAvailableCount;
	}

	public void setSeaterWaitingListAvailableCount(int seaterWaitingListAvailableCount) {
		this.seaterWaitingListAvailableCount = seaterWaitingListAvailableCount;
	}

	public int getSeaterRacAvailableCount() {
		return seaterRacAvailableCount;
	}

	public void setSeaterRacAvailableCount(int seaterRacAvailableCount) {
		this.seaterRacAvailableCount = seaterRacAvailableCount;
	}

	public int getSleeperWaitingListAvailableCount() {
		return sleeperWaitingListAvailableCount;
	}

	public void setSleeperWaitingListAvailableCount(int sleeperWaitingListAvailableCount) {
		this.sleeperWaitingListAvailableCount = sleeperWaitingListAvailableCount;
	}

	public int getSleeperRacAvailableCount() {
		return sleeperRacAvailableCount;
	}

	public void setSleeperRacAvailableCount(int sleeperRacAvailableCount) {
		this.sleeperRacAvailableCount = sleeperRacAvailableCount;
	}

	public int getSeaterAvailableCount() {
		return seaterAvailableCount;
	}

	public void setSeaterAvailableCount(int seaterAvailableCount) {
		this.seaterAvailableCount = seaterAvailableCount;
	}

	public int getSleeperAvailableCount() {
		return sleeperAvailableCount;
	}

	public void setSleeperAvailableCount(int sleeperAvailableCount) {
		this.sleeperAvailableCount = sleeperAvailableCount;
	}

	public int getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
	}

	public int getSeaterCarraigeCount() {
		return seaterCarraigeCount;
	}

	public void setSeaterCarraigeCount(int seaterCarraigeCount) {
		this.seaterCarraigeCount = seaterCarraigeCount;
	}

	public int getSleeperCarraigeCount() {
		return sleeperCarraigeCount;
	}

	public void setSleeperCarraigeCount(int sleeperCarraigeCount) {
		this.sleeperCarraigeCount = sleeperCarraigeCount;
	}

	public int getSleeperCountPerCarriage() {
		return sleeperCountPerCarriage;
	}

	public void setSleeperCountPerCarriage(int sleeperCountPerCarriage) {
		this.sleeperCountPerCarriage = sleeperCountPerCarriage;
	}

	public int getSeaterCountPerCarriage() {
		return seaterCountPerCarriage;
	}

	public void setSeaterCountPerCarriage(int seaterCountPerCarriage) {
		this.seaterCountPerCarriage = seaterCountPerCarriage;
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

	public float getSleeperPrice() {
		return sleeperPrice;
	}

	public void setSleeperPrice(float sleeperPrice) {
		this.sleeperPrice = sleeperPrice;
	}

	public float getSeaterPrice() {
		return seaterPrice;
	}

	public void setSeaterPrice(float seaterPrice) {
		this.seaterPrice = seaterPrice;
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

	public int getTripId() {
		return tripId;
	}

	public void setTripId(int tripId) {
		this.tripId = tripId;
	}

}
