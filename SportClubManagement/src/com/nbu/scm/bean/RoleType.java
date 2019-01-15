package com.nbu.scm.bean;

public enum RoleType {

	RECEPTIONIST(1, "Receptionist"), ADMINISTRATOR(2, "Administrator");
	
	private int id;
	private String name;

	RoleType(int id, String name) {
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

	public static RoleType getById(int id) {
		RoleType roleType = null;
		for (RoleType value  : values()) {
			if (value.getId() == id) {
				roleType = value;
				break;
			}
		}
		return roleType;
	}
}
