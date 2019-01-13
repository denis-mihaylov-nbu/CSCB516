package com.nbu.scm.controller;

import java.sql.Timestamp;
import java.util.List;

import com.nbu.scm.bean.Reservation;
import com.nbu.scm.bean.filter.ReservationFilter;
import com.nbu.scm.model.ReservationModel;

public class ReservationController {

	public Reservation createReservation(Reservation reservation) throws Exception {
		reservation = ReservationModel.create(reservation);
		return reservation;
	}

	public List<Reservation> getByTimestamp(Timestamp timestamp) throws Exception {
		List<Reservation> reservations = ReservationModel.getByTimestamp(timestamp);
		return reservations;
	}

	public List<Reservation> get(ReservationFilter filter) throws Exception {
		List<Reservation> reservations = ReservationModel.get(filter);
		return reservations;
	}
	
}
