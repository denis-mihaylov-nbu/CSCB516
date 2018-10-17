package com.nbu.scm.bean;

import java.util.Calendar;

public class Timestamp {

	private Calendar cal;
	private java.sql.Timestamp timestamp;

	public Timestamp(Calendar cal) {
		super();
		this.cal = cal;
		this.timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
	}

	public Timestamp(java.sql.Timestamp timestamp) {
		super();
		this.timestamp = timestamp;
		this.cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp.getTime());
	}

	public java.sql.Timestamp getTimestamp() {
		return timestamp;
	}

	public String getTimestampAsString() {
		StringBuilder sb = new StringBuilder();
		sb.append(cal.get(Calendar.DATE));
		sb.append("/");
		sb.append(cal.get(Calendar.MONTH) + 1);
		sb.append("/");
		sb.append(cal.get(Calendar.YEAR));
		sb.append(" ");
		sb.append(cal.get(Calendar.HOUR_OF_DAY));
		sb.append(":00");
		return sb.toString();
	}

}
