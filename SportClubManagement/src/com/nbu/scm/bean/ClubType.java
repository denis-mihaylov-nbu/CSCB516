package com.nbu.scm.bean;

public class ClubType implements Comparable<ClubType> {

	private int id;
	private String name;

	public ClubType() {
	}
	
	public ClubType(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(ClubType o) {
		return this.toString().compareTo(o.toString());
	}
	
	

}
