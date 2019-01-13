package com.nbu.scm.bean;

import java.util.ArrayList;
import java.util.List;

public class Receipt {

	private int id;
	private double value;
	private Club club;
	private Timestamp timestamp;
	private List<Object> items = new ArrayList<Object>();

	public Receipt() {
		
	}
	
	public Receipt(Timestamp timestamp, double value, Club club, List<Object> items) {
		this(-1, timestamp, value, club, items);
	}

	public Receipt(int id, Timestamp timestamp, double value, Club club, List<Object> items) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.value = value;
		this.club = club;
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.sql.Timestamp getTimestamp() {
		return timestamp.getTimestamp();
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public List<Object> getItems() {
		return items;
	}

	public void setItems(List<Object> items) {
		this.items = items;
	}

}
