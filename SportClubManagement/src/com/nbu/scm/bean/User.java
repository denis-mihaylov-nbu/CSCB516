package com.nbu.scm.bean;

import java.security.NoSuchAlgorithmException;

import com.nbu.scm.security.Cryptography;

public class User {
	
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private Club club;
	private RoleType type;

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String text) {
		try {
			this.password = Cryptography.cryptSHA256(text);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public RoleType getType() {
		return type;
	}

	public void setType(RoleType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

}
