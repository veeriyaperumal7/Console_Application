package com.veeriyaperumal.flightreservation.model;

import java.io.Serializable;

public class Ticket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pnr;
	private int ScheduleId;
	private int userId;
	private String ticketStatus;

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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

}
