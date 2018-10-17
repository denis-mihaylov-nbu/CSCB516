package com.nbu.scm.controller;

import com.nbu.scm.bean.Reservation;
import com.nbu.scm.model.ReservationModel;

public class ReservationController {

	public Reservation createReservation(Reservation reservation) throws Exception {
		reservation = ReservationModel.create(reservation);
		return reservation;
	}
	
}
