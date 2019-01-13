package com.nbu.scm.bean.filter;

import java.sql.Timestamp;
import java.time.LocalDate;

public class ReservationFilter {

	private int clubId;
	private String name;
	private Timestamp fromDate;
	private Timestamp toDate;
	private boolean paid;

	public ReservationFilter() {
		super();
	}

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = Timestamp.valueOf(fromDate.atStartOfDay());
	}

	public Timestamp getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = Timestamp.valueOf(toDate.atStartOfDay().plusDays(1));
	}

	public boolean getPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

}
