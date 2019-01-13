package com.nbu.scm.bean;

public class Court {

	private int id;
	private int number;
	private CourtType type;
	private double price;

	public Court() {
	}

	public Court(int id, int number, CourtType type) {
		super();
		this.id = id;
		this.number = number;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public CourtType getType() {
		return type;
	}

	public void setType(CourtType type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return +number + ", " + type;
	}

}
