package com.nbu.scm.bean;

public class Reservation {

	private int id;
	private Timestamp timestamp;
	private Club club;
	private Court court;
	private String name;

	public Reservation() {
		super();
	}

	public Reservation(int id, Timestamp date, Club club, Court court, String name) {
		super();
		this.id = id;
		this.timestamp = date;
		this.club = club;
		this.court = court;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return timestamp;
	}

	public void setDate(Timestamp date) {
		this.timestamp = date;
	}
	
	public void setDate(java.sql.Timestamp timestamp) {
		this.timestamp = new Timestamp(timestamp);
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.sql.Timestamp getTimestamp() {
		return timestamp.getTimestamp();
	}
	
	public String getTimestampAsString() {
		return timestamp.getTimestampAsString();
	}

	@Override
	public String toString() {
		return court + ", " + name;
	}

}
