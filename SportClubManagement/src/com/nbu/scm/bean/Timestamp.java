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
		int year = cal.get(java.util.Calendar.YEAR);
		int month = cal.get(java.util.Calendar.MONTH) + 1;
		int date = cal.get(java.util.Calendar.DATE);
		int hour = cal.get(java.util.Calendar.HOUR_OF_DAY);
		StringBuilder sb = new StringBuilder();
		sb.append(year);
		sb.append("-");
		sb.append(month >= 10 ? month : "0" + month);
		sb.append("-");
		sb.append(date >= 10 ? date : "0" + date);
		sb.append(" ");
		sb.append(hour >= 10 ? hour : "0" + hour);
		sb.append(":00");
		return sb.toString();
	}

	@Override
	public String toString() {
		int year = cal.get(java.util.Calendar.YEAR);
		int month = cal.get(java.util.Calendar.MONTH) + 1;
		int date = cal.get(java.util.Calendar.DATE);
		int hour = cal.get(java.util.Calendar.HOUR_OF_DAY);
		int minutes = cal.get(java.util.Calendar.MINUTE);
		int seconds = cal.get(java.util.Calendar.SECOND);
		StringBuilder sb = new StringBuilder();
		sb.append(year);
		sb.append("-");
		sb.append(month >= 10 ? month : "0" + month);
		sb.append("-");
		sb.append(date >= 10 ? date : "0" + date);
		sb.append(" ");
		sb.append(hour >= 10 ? hour : "0" + hour);
		sb.append(":");
		sb.append(minutes >= 10 ? minutes : "0" + minutes);
		sb.append(":");
		sb.append(seconds >= 10 ? seconds : "0" + seconds);
		return sb.toString();

	}

}
