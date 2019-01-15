package com.nbu.scm.bean;

public class CourtType implements Comparable<CourtType> {

	private int id;
	private String type;

	public CourtType() {
	}
	
	public CourtType(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}

	@Override
	public int compareTo(CourtType o) {
		return this.toString().compareTo(o.toString());
	}

}
