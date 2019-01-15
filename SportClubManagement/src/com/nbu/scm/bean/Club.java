package com.nbu.scm.bean;

import java.util.Set;
import java.util.TreeSet;

public class Club implements Comparable<Club> {

	private int id;
	private String name;
	private String address;
	private ClubType type;
	private Set<Court> courts;

	public Club() {
		super();
		this.courts = new TreeSet<Court>();
	}

	public Club(String name, String adress) {
		this.name = name;
		this.address = adress;
//		this.type = type;
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

	public Set<Court> getCourts() {
		return courts;
	}

	public void addCourt(Court court) {
		if (courts == null) {
			courts = new TreeSet<Court>();
		}
		courts.add(court);
	}

	@Override
	public String toString() {
		return name + ", " + address + ", " + type;
	}

	public void setCourts(Set<Court> courts) {
		this.courts = courts;
	}

	@Override
	public int compareTo(Club o) {
		return this.toString().compareTo(o.toString());
	}

}
