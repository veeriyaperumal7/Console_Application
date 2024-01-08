package com.veeriyaperumal.railwaysreservation.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.veeriyaperumal.railwaysreservation.databaseconnection.JdbcConnection;
import com.veeriyaperumal.railwaysreservation.model.Passenger;
import com.veeriyaperumal.railwaysreservation.model.Ticket;
import com.veeriyaperumal.railwaysreservation.model.Train;

public class Repository {
	private String query;
	private ArrayList<Train> scheduledTrainList = new ArrayList<>();
	private static Repository repository;

	private Repository() {
	}

	public static Repository getInstance() {
		if (repository == null) {
			repository = new Repository();
		}
		return repository;
	}

	public ArrayList<Train> getScheduledTrainList() {
		return scheduledTrainList;
	}

	public void setScheduledTrainList(ArrayList<Train> scheduledTrainList) {
		this.scheduledTrainList = scheduledTrainList;
	}

	public ArrayList<Train> getScheduledTrainList(String bookingDate) {
		scheduledTrainList.clear();
		query = "SELECT * FROM trip_entry WHERE travel_start_date ='" + bookingDate + "' AND trip_status='ONLINE'";
		ResultSet result = JdbcConnection.getInstance().executeSelectQuery(query);
		if (result == null) {
			scheduledTrainList = null;
		} else {
			try {
				while (result.next()) {
					Train train = new Train();
					train.setTripId(result.getInt("trip_id"));
					train.setTrainNo(result.getInt("train_no"));
					train.setTrainName(result.getString("train_name"));
					train.setStartDate(result.getDate("travel_start_date"));
					train.setEndDate(result.getDate("travel_end_date"));
					train.setStartTime(result.getTime("travel_start_time"));
					train.setEndTime(result.getTime("travel_end_time"));
					train.setSeaterAvailableCount(result.getInt("seats_remaining_in_seater"));
					train.setSeaterWaitingListAvailableCount(result.getInt("waitinglist_remaining_in_seater"));
					train.setSeaterRacAvailableCount(result.getInt("rac_remaining_in_seater"));
					train.setSleeperAvailableCount(result.getInt("seats_remaining_in_sleeper"));
					train.setSleeperWaitingListAvailableCount(result.getInt("waitinglist_remaining_in_sleeper"));
					train.setSleeperRacAvailableCount(result.getInt("rac_remaining_in_sleeper"));
					train.setSeaterPrice(result.getFloat("seater_fair"));
					train.setSleeperPrice(result.getFloat("sleeper_fair"));
					train.setDeparturePlace(result.getString("departure_place"));
					train.setArrivalPlace(result.getString("arrival_place"));
					scheduledTrainList.add(train);
				}
			} catch (SQLException e) {
				scheduledTrainList = null;
				JdbcConnection.getInstance().printErrorMessageWithQuery("Error while getting ScheduledTrain", query);
				e.printStackTrace();
			} finally {
				JdbcConnection.getInstance().closeSQLConnection();
			}
		}
		return scheduledTrainList;
	}

	public boolean bookTicketInSeater(Passenger passenger, Train userSelectedTrain) {
		int passengerId = getPassengerId(passenger), rowsAffected = 0;
		passenger.setPassengerId(passengerId);
		query = "insert into booking_entry (trip_id, passenger_id, passenger_paid_amount, passenger_ticket_status, passenger_class,journey_status) "
				+ "values(" + userSelectedTrain.getTripId() + "," + passengerId + ","
				+ userSelectedTrain.getSeaterPrice() + ",'CNF','SEATER','CONFIRMED')";
		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		if (rowsAffected < 1) {
			JdbcConnection.getInstance().printErrorMessageWithQuery("Error while insert into table", query);
		}
		query = "update trip_entry set seats_remaining_in_seater=seats_remaining_in_seater-1 where trip_id="
				+ userSelectedTrain.getTripId();
		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		return (rowsAffected >= 1) ? true : false;
	}

	public boolean bookTicketInRacSeater(Passenger passenger, Train userSelectedTrain) {
		int passengerId = getPassengerId(passenger), rowsAffected = 0;
		passenger.setPassengerId(passengerId);
		query = "insert into booking_entry (trip_id, passenger_id, passenger_paid_amount, passenger_ticket_status, passenger_class,journey_status) "
				+ "values(" + userSelectedTrain.getTripId() + "," + passengerId + ","
				+ userSelectedTrain.getSeaterPrice() + ",'RAC','SEATER','CONFIRMED')";
		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		if (rowsAffected < 1) {
			JdbcConnection.getInstance().printErrorMessageWithQuery("Error while insert into table", query);
		}
		query = "update trip_entry set rac_remaining_in_seater=rac_remaining_in_seater-1 where trip_id="
				+ userSelectedTrain.getTripId();
		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		return (rowsAffected >= 1) ? true : false;
	}

	public boolean bookTicketInWaitingListSeater(Passenger passenger, Train userSelectedTrain) {
		int passengerId = getPassengerId(passenger), rowsAffected = 0;
		passenger.setPassengerId(passengerId);
		query = "insert into booking_entry (trip_id, passenger_id, passenger_paid_amount, passenger_ticket_status, passenger_class,journey_status) "
				+ "values(" + userSelectedTrain.getTripId() + "," + passengerId + ","
				+ userSelectedTrain.getSeaterPrice() + ",'WL','SEATER','CONFIRMED')";
		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		if (rowsAffected < 1) {
			JdbcConnection.getInstance().printErrorMessageWithQuery("Error while insert into table", query);
		}
		query = "update trip_entry set waitinglist_remaining_in_seater=waitinglist_remaining_in_seater-1 where trip_id="
				+ userSelectedTrain.getTripId();
		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		return (rowsAffected >= 1) ? true : false;
	}

	public boolean bookTicketInSleeper(Passenger passenger, Train userSelectedTrain) {
		int passengerId = getPassengerId(passenger), rowsAffected = 0;
		passenger.setPassengerId(passengerId);
		query = "insert into booking_entry (trip_id, passenger_id, passenger_paid_amount, passenger_ticket_status, passenger_class,journey_status) "
				+ "values(" + userSelectedTrain.getTripId() + "," + passengerId + ","
				+ userSelectedTrain.getSleeperPrice() + ",'CNF','SLEEPER','CONFIRMED')";
		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		if (rowsAffected < 1) {
			JdbcConnection.getInstance().printErrorMessageWithQuery("Error while insert into table", query);
		}
		query = "update trip_entry set seats_remaining_in_sleeper=seats_remaining_in_sleeper-1 where trip_id="
				+ userSelectedTrain.getTripId();
		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		return (rowsAffected >= 1) ? true : false;
	}

	public boolean bookTicketInRacSleeper(Passenger passenger, Train userSelectedTrain) {
		int passengerId = getPassengerId(passenger), rowsAffected = 0;
		passenger.setPassengerId(passengerId);
		query = "insert into booking_entry (trip_id, passenger_id, passenger_paid_amount, passenger_ticket_status, passenger_class,journey_status) "
				+ "values(" + userSelectedTrain.getTripId() + "," + passengerId + ","
				+ userSelectedTrain.getSleeperPrice() + ",'RAC','SLEEPER','CONFIRMED')";
		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		if (rowsAffected < 1) {
			JdbcConnection.getInstance().printErrorMessageWithQuery("Error while insert into table", query);
		}
		query = "update trip_entry set rac_remaining_in_sleeper=rac_remaining_in_sleeper-1 where trip_id="
				+ userSelectedTrain.getTripId();
		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		return (rowsAffected >= 1) ? true : false;
	}

	public boolean bookTicketInWaitingListSleeper(Passenger passenger, Train userSelectedTrain) {
		int passengerId = getPassengerId(passenger), rowsAffected = 0;
		passenger.setPassengerId(passengerId);
		query = "insert into booking_entry (trip_id, passenger_id, passenger_paid_amount, passenger_ticket_status, passenger_class,journey_status) "
				+ "values(" + userSelectedTrain.getTripId() + "," + passengerId + ","
				+ userSelectedTrain.getSleeperPrice() + ",'WL','SLEEPER','CONFIRMED')";
		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		if (rowsAffected < 1) {
			JdbcConnection.getInstance().printErrorMessageWithQuery("Error while insert into table", query);
		}
		query = "update trip_entry set waitinglist_remaining_in_sleeper=waitinglist_remaining_in_sleeper-1 where trip_id="
				+ userSelectedTrain.getTripId();
		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		return (rowsAffected >= 1) ? true : false;
	}

	public int getPassengerId(Passenger passenger) {
		query = "Select passenger_id from passenger where passenger_name='" + passenger.getName()
				+ "' and passenger_mobile ='" + passenger.getMobileNumber() + "'";
		ResultSet result = JdbcConnection.getInstance().executeSelectQuery(query);
		try {
			if (!result.next()) {
				return createPassengerId(passenger);
			} else {
				try {
					return result.getInt("passenger_id");
				} catch (SQLException e) {
					JdbcConnection.getInstance().printErrorMessageWithQuery("Error while getting passenger id", query);
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConnection.getInstance().closeSQLConnection();
		}
		return -1;
	}

	public int createPassengerId(Passenger passenger) {
		int id = getMaxPassengerId();
		query = "insert into passenger (passenger_id,passenger_name,passenger_dob,passenger_gender,passenger_place,passenger_mobile) values ("
				+ id + ",'" + passenger.getName() + "','" + passenger.getDob() + "','" + passenger.getGender() + "','"
				+ passenger.getPlace() + "','" + passenger.getMobileNumber() + "')";
		if (JdbcConnection.getInstance().executeInsertOrUpdateQuery(query) >= 1) {
			return id;
		} else {
			JdbcConnection.getInstance().printErrorMessageWithQuery("Error while insert new passenger data", query);
			return -1;
		}
	}

	public int getMaxPassengerId() {
		query = "SELECT MAX(passenger_id) AS maxId FROM passenger";
		ResultSet result = JdbcConnection.getInstance().executeSelectQuery(query);
		try {
			result.next();
			return result.getInt("maxId") + 1;
		} catch (SQLException e) {
			JdbcConnection.getInstance().printErrorMessageWithQuery("Error while getting max passenger id", query);
			e.printStackTrace();
		} finally {
			JdbcConnection.getInstance().closeSQLConnection();
		}
		return -1;

	}

	public int getPNR(Passenger currentPassenger, Train userSelectedTrain) {
		query = "SELECT MAX(pnr_no) AS maxPnr FROM booking_entry where passenger_id="
				+ currentPassenger.getPassengerId() + " and trip_id=" + userSelectedTrain.getTripId();
		ResultSet result = JdbcConnection.getInstance().executeSelectQuery(query);
		try {
			result.next();
			return result.getInt("maxPnr");
		} catch (SQLException e) {
			JdbcConnection.getInstance().printErrorMessageWithQuery("Error while getting max passenger id", query);
			e.printStackTrace();
		} finally {
			JdbcConnection.getInstance().closeSQLConnection();
		}
		return -1;

	}

	public void getTicketData(Ticket ticket, int pnr) {
		ticket.setTripId(-1);
		query = "SELECT * FROM booking_entry where pnr_no=" + pnr;
		ResultSet result = JdbcConnection.getInstance().executeSelectQuery(query);
		if (result == null) {
			ticket = null;
		} else {
			try {
				while (result.next()) {
					ticket.setPnrNo(result.getInt("pnr_no"));
					ticket.setTripId(result.getInt("trip_id"));
					ticket.setPassengerId(result.getInt("passenger_id"));
					ticket.setPassengerPaidAmount(result.getFloat("passenger_paid_amount"));
					ticket.setPassengerTicketStatus(result.getString("passenger_ticket_status"));
					ticket.setPassengerClass(result.getString("passenger_class"));
					ticket.setJourneyStatus(result.getString("journey_status"));
				}
				Passenger passenger = getPassengerData(ticket.getPassengerId());
				Train train = getTripData(ticket.getTripId());
				ticket.setPassengerName(passenger.getName());
				ticket.setPassengerMobile(passenger.getMobileNumber());
				ticket.setDeparturePlace(train.getDeparturePlace());
				ticket.setArrivalPlace(train.getArrivalPlace());
				ticket.setStartDate(train.getStartDate());
				ticket.setStartTime(train.getStartTime());
				ticket.setTrainName(train.getTrainName());
				ticket.setTrainNo(train.getTrainNo());
			} catch (SQLException e) {
				ticket = null;
				JdbcConnection.getInstance().printErrorMessageWithQuery("Error while getting Pnr data", query);
				e.printStackTrace();
			} finally {
				JdbcConnection.getInstance().closeSQLConnection();
			}
		}
	}

	public void getTicketData(Ticket ticket, int pnr, String cancel) {
		ticket.setTripId(-1);
		query = "SELECT * FROM booking_entry where pnr_no=" + pnr + " and journey_status='CONFIRMED'";
		ResultSet result = JdbcConnection.getInstance().executeSelectQuery(query);
		if (result == null) {
			ticket = null;
		} else {
			try {
				while (result.next()) {
					ticket.setPnrNo(result.getInt("pnr_no"));
					ticket.setTripId(result.getInt("trip_id"));
					ticket.setPassengerId(result.getInt("passenger_id"));
					ticket.setPassengerPaidAmount(result.getFloat("passenger_paid_amount"));
					ticket.setPassengerTicketStatus(result.getString("passenger_ticket_status"));
					ticket.setPassengerClass(result.getString("passenger_class"));
					ticket.setJourneyStatus(result.getString("journey_status"));
				}
				Passenger passenger = getPassengerData(ticket.getPassengerId());
				Train train = getTripData(ticket.getTripId());
				ticket.setPassengerName(passenger.getName());
				ticket.setPassengerMobile(passenger.getMobileNumber());
				ticket.setDeparturePlace(train.getDeparturePlace());
				ticket.setArrivalPlace(train.getArrivalPlace());
				ticket.setStartDate(train.getStartDate());
				ticket.setStartTime(train.getStartTime());
				ticket.setTrainName(train.getTrainName());
				ticket.setTrainNo(train.getTrainNo());
			} catch (SQLException e) {
				ticket = null;
				JdbcConnection.getInstance().printErrorMessageWithQuery("Error while getting Pnr data", query);
				e.printStackTrace();
			} finally {
				JdbcConnection.getInstance().closeSQLConnection();
			}
		}
	}

	public Passenger getPassengerData(int passengerId) {
		Passenger passenger = new Passenger();
		query = "SELECT * FROM passenger where passenger_id=" + passengerId;
		ResultSet result = JdbcConnection.getInstance().executeSelectQuery(query);
		if (result == null) {
			passenger = null;
		} else {
			try {
				while (result.next()) {
					passenger.setName(result.getString("passenger_name"));
					passenger.setMobileNumber(result.getString("passenger_mobile"));
				}
			} catch (SQLException e) {
				passenger = null;
				JdbcConnection.getInstance().printErrorMessageWithQuery("Error while getting Pnr data", query);
				e.printStackTrace();
			} finally {
				JdbcConnection.getInstance().closeSQLConnection();
			}
		}
		return passenger;
	}

	public Train getTripData(int tripId) {
		Train train = new Train();
		query = "SELECT * FROM trip_entry where trip_id=" + tripId;
		ResultSet result = JdbcConnection.getInstance().executeSelectQuery(query);
		if (result == null) {
			train = null;
		} else {
			try {
				while (result.next()) {
					train.setTrainNo(result.getInt("train_no"));
					train.setTrainName(result.getString("train_name"));
					train.setStartDate(result.getDate("travel_start_date"));
					train.setStartTime(result.getTime("travel_start_time"));
					train.setArrivalPlace(result.getString("arrival_place"));
					train.setDeparturePlace(result.getString("departure_place"));
				}
			} catch (SQLException e) {
				train = null;
				JdbcConnection.getInstance().printErrorMessageWithQuery("Error while getting Pnr data", query);
				e.printStackTrace();
			} finally {
				JdbcConnection.getInstance().closeSQLConnection();
			}
		}
		return train;
	}

	public void cancelTicket(Ticket ticket) {

		int rowsAffected;
		if (ticket.getPassengerClass().equals("SEATER")) {
			if (ticket.getPassengerTicketStatus().equals("CNF")) {
				query = "update trip_entry set seats_remaining_in_seater=seats_remaining_in_seater+1 where trip_id="
						+ ticket.getTripId() + " and train_no=" + ticket.getTrainNo();
				rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
				if (rowsAffected < 1) {
					JdbcConnection.getInstance().printErrorMessageWithQuery("Error while update cancel ticket count",
							query);
				}
				query = "update booking_entry set journey_status='CANCELLED' where pnr_no = " + ticket.getPnrNo()
						+ " and trip_id=" + ticket.getTripId();
				rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
				if (rowsAffected < 1) {
					JdbcConnection.getInstance().printErrorMessageWithQuery("Error while update cancel ticket status",
							query);
				}
				promoteTicketRacToConfirmation("Seater", ticket);
				promoteTicketWaitingListToRac("Seater", ticket);

			} else if (ticket.getPassengerTicketStatus().equals("RAC")) {
				query = "update trip_entry set rac_remaining_in_seater=rac_remaining_in_seater+1 where trip_id="
						+ ticket.getTripId() + " and train_no=" + ticket.getTrainNo();
				rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
				if (rowsAffected < 1) {
					JdbcConnection.getInstance().printErrorMessageWithQuery("Error while update cancel ticket count",
							query);
				}
				query = "update booking_entry set journey_status='CANCELLED' where pnr_no = " + ticket.getPnrNo()
						+ " and trip_id=" + ticket.getTripId();
				rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
				if (rowsAffected < 1) {
					JdbcConnection.getInstance().printErrorMessageWithQuery("Error while update cancel ticket status",
							query);
				}
				promoteTicketWaitingListToRac("Seater", ticket);

			} else {
				query = "update trip_entry set waitinglist_remaining_in_seater=waitinglist_remaining_in_seater+1 where trip_id="
						+ ticket.getTripId() + " and train_no=" + ticket.getTrainNo();
				rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
				if (rowsAffected < 1) {
					JdbcConnection.getInstance().printErrorMessageWithQuery("Error while update cancel ticket count",
							query);
				}
				query = "update booking_entry set journey_status='CANCELLED' where pnr_no = " + ticket.getPnrNo()
						+ " and trip_id=" + ticket.getTripId();
				rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
				if (rowsAffected < 1) {
					JdbcConnection.getInstance().printErrorMessageWithQuery("Error while update cancel ticket status",
							query);
				}
			}
		} else {
			if (ticket.getPassengerTicketStatus().equals("CNF")) {
				query = "update trip_entry set seats_remaining_in_sleeper=seats_remaining_in_sleeper+1 where trip_id="
						+ ticket.getTripId() + " and train_no=" + ticket.getTrainNo();
				rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
				if (rowsAffected < 1) {
					JdbcConnection.getInstance().printErrorMessageWithQuery("Error while update cancel ticket count",
							query);
				}
				query = "update booking_entry set journey_status='CANCELLED' where pnr_no = " + ticket.getPnrNo()
						+ " and trip_id=" + ticket.getTripId();
				rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
				if (rowsAffected < 1) {
					JdbcConnection.getInstance().printErrorMessageWithQuery("Error while update cancel ticket status",
							query);
				}
				promoteTicketRacToConfirmation("Sleeper", ticket);
				promoteTicketWaitingListToRac("Sleeper", ticket);

			} else if (ticket.getPassengerTicketStatus().equals("RAC")) {
				query = "update trip_entry set rac_remaining_in_sleeper=rac_remaining_in_sleeper+1 where trip_id="
						+ ticket.getTripId() + " and train_no=" + ticket.getTrainNo();
				rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
				if (rowsAffected < 1) {
					JdbcConnection.getInstance().printErrorMessageWithQuery("Error while update cancel ticket count",
							query);
				}
				query = "update booking_entry set journey_status='CANCELLED' where pnr_no = " + ticket.getPnrNo()
						+ " and trip_id=" + ticket.getTripId();
				rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
				if (rowsAffected < 1) {
					JdbcConnection.getInstance().printErrorMessageWithQuery("Error while update cancel ticket status",
							query);
				}
				promoteTicketWaitingListToRac("Sleeper", ticket);

			} else {
				query = "update trip_entry set waitinglist_remaining_in_sleeper=waitinglist_remaining_in_sleeper+1 where trip_id="
						+ ticket.getTripId() + " and train_no=" + ticket.getTrainNo();
				rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
				if (rowsAffected < 1) {
					JdbcConnection.getInstance().printErrorMessageWithQuery("Error while update cancel ticket count",
							query);
				}
				query = "update booking_entry set journey_status='CANCELLED' where pnr_no = " + ticket.getPnrNo()
						+ " and trip_id=" + ticket.getTripId();
				rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
				if (rowsAffected < 1) {
					JdbcConnection.getInstance().printErrorMessageWithQuery("Error while update cancel ticket status",
							query);
				}
			}
		}
	}

	private void promoteTicketRacToConfirmation(String trainClass, Ticket ticket) {
		int rowsAffected;
		if (trainClass.equals("Seater")) {
			query = "select * from booking_entry where passenger_ticket_status='RAC' and trip_id=" + ticket.getTripId()
					+ " and journey_status='CONFIRMED' and passenger_class='SEATER'";
			ResultSet result = JdbcConnection.getInstance().executeSelectQuery(query);
			if (result == null) {
				return;
			} else {
				try {
					while (result.next()) {
						query = "update booking_entry set passenger_ticket_status='CNF' where pnr_no="
								+ result.getInt("pnr_no");
						rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
						if (rowsAffected < 1) {
							JdbcConnection.getInstance()
									.printErrorMessageWithQuery("Error while change ticket rac to cnf", query);
						}
						query = "update trip_entry set rac_remaining_in_seater=rac_remaining_in_seater+1 where trip_id="
								+ ticket.getTripId() + " and train_no=" + ticket.getTrainNo();
						rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
						if (rowsAffected < 1) {
							JdbcConnection.getInstance().printErrorMessageWithQuery(
									"Error while update rac count after ticet cancelled.", query);
						}
					}
				} catch (SQLException e) {

					JdbcConnection.getInstance().printErrorMessageWithQuery("Error while moving ticket rac to cnf",
							query);
					e.printStackTrace();
				} finally {
					JdbcConnection.getInstance().closeSQLConnection();
				}
			}
		} else {
			query = "select * from booking_entry where passenger_ticket_status='RAC' and trip_id=" + ticket.getTripId()
					+ " and journey_status='CONFIRMED' and passenger_class='SLEEPER'";
			ResultSet result = JdbcConnection.getInstance().executeSelectQuery(query);
			if (result == null) {
				return;
			} else {
				try {
					while (result.next()) {
						query = "update booking_entry set passenger_ticket_status='CNF' where pnr_no="
								+ result.getInt("pnr_no");
						rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
						if (rowsAffected < 1) {
							JdbcConnection.getInstance()
									.printErrorMessageWithQuery("Error while change ticket rac to cnf", query);
						}
						query = "update trip_entry set rac_remaining_in_sleeper=rac_remaining_in_sleeper+1 where trip_id="
								+ ticket.getTripId() + " and train_no=" + ticket.getTrainNo();
						rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
						if (rowsAffected < 1) {
							JdbcConnection.getInstance().printErrorMessageWithQuery(
									"Error while update rac count after ticet cancelled.", query);
						}
						break;
					}
				} catch (SQLException e) {

					JdbcConnection.getInstance().printErrorMessageWithQuery("Error while moving ticket rac to cnf",
							query);
					e.printStackTrace();
				} finally {
					JdbcConnection.getInstance().closeSQLConnection();
				}
			}
		}
	}

	private void promoteTicketWaitingListToRac(String trainClass, Ticket ticket) {
		int rowsAffected;
		if (trainClass.equals("Seater")) {
			query = "select * from booking_entry where passenger_ticket_status='WL' and trip_id=" + ticket.getTripId()
					+ " and journey_status='CONFIRMED' and passenger_class='SEATER'";
			ResultSet result = JdbcConnection.getInstance().executeSelectQuery(query);
			if (result == null) {
				return;
			} else {
				try {
					while (result.next()) {
						query = "update booking_entry set passenger_ticket_status='RAC' where pnr_no="
								+ result.getInt("pnr_no");
						rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
						if (rowsAffected < 1) {
							JdbcConnection.getInstance()
									.printErrorMessageWithQuery("Error while change ticket waitinglist to rac", query);
						}
						query = "update trip_entry set waitinglist_remaining_in_seater=waitinglist_remaining_in_seater+1 where trip_id="
								+ ticket.getTripId() + " and train_no=" + ticket.getTrainNo();
						rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
						if (rowsAffected < 1) {
							JdbcConnection.getInstance().printErrorMessageWithQuery(
									"Error while update rac count after ticet cancelled.", query);
						}
						break;
					}
				} catch (SQLException e) {

					JdbcConnection.getInstance()
							.printErrorMessageWithQuery("Error while moving ticket waitinglist to rac", query);
					e.printStackTrace();
				} finally {
					JdbcConnection.getInstance().closeSQLConnection();
				}
			}
		} else {
			query = "select * from booking_entry where passenger_ticket_status='WL' and trip_id=" + ticket.getTripId()
					+ " and journey_status='CONFIRMED' and passenger_class='SLEEPER'";
			ResultSet result = JdbcConnection.getInstance().executeSelectQuery(query);
			if (result == null) {
				return;
			} else {
				try {
					while (result.next()) {
						query = "update booking_entry set passenger_ticket_status='RAC' where pnr_no="
								+ result.getInt("pnr_no");
						rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);

						if (rowsAffected < 1) {
							JdbcConnection.getInstance()
									.printErrorMessageWithQuery("Error while moving ticket waitinglist to rac", query);
						}
						query = "update trip_entry set waitinglist_remaining_in_sleeper=waitinglist_remaining_in_sleeper+1 where trip_id="
								+ ticket.getTripId() + " and train_no=" + ticket.getTrainNo();
						rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
						if (rowsAffected < 1) {
							JdbcConnection.getInstance().printErrorMessageWithQuery(
									"Error while update rac count after ticet cancelled.", query);
						}
						break;
					}
				} catch (SQLException e) {

					JdbcConnection.getInstance()
							.printErrorMessageWithQuery("Error while moving ticket waitinglist to rac", query);
					e.printStackTrace();
				} finally {
					JdbcConnection.getInstance().closeSQLConnection();
				}
			}
		}
	}

}
