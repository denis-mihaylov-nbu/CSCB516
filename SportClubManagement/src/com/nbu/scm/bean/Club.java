package com.nbu.scm.bean;

import java.util.ArrayList;
import java.util.List;

public class Club {

	private int id;
	private String name;
	private String address;
	private ClubType type;
	private List<Court> courts;

	public Club() {
		super();
	}

	public Club(int id, String name, String address, ClubType type, List<Court> courts) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.type = type;
		this.courts = courts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ClubType getType() {
		return type;
	}

	public void setType(ClubType type) {
		this.type = type;
	}

	public List<Court> getCourts() {
		return courts;
	}

	public void addCourt(Court court) {
		if (courts == null) {
			courts = new ArrayList<Court>();
		}
		courts.add(court);
	}

}
