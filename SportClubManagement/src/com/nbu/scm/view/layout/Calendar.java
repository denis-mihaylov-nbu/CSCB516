package com.nbu.scm.view.layout;

import java.util.ArrayList;
import java.util.List;

import com.nbu.scm.bean.Club;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Calendar {

	Club club;
	int page;
	List<Row> rows;

	private static final int START_HOUR = 8;
	private static final int OPEN_FOR = 14;

	public Calendar(Club club, int page) {
		super();
		this.club = club;
		this.page = page;
		init();
	}

	private void init() {
		rows = new ArrayList<Row>();
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.add(java.util.Calendar.DATE, page * 7);

		Row header = new Row();
		header.add(new Cell("", Color.LIGHTGREY, Color.BLACK));
		
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent e) {
	        	Alert alert = new Alert(AlertType.INFORMATION);
	        	alert.setTitle("Information Dialog");
	        	alert.setHeaderText("Look, an Information Dialog");
	        	alert.setContentText("I have a great message for you!");

	        	alert.showAndWait();
	        }
	    };
		
		for (int i = 0; i < 7; i++) {
			header.add(new Cell(getHeaderText(cal), isWeekend(cal) ? Color.DARKGRAY : Color.LIGHTGREY, Color.BLACK));
			cal.add(java.util.Calendar.DATE, 1);
		}
		rows.add(header);

		for (int i = 0; i < OPEN_FOR; i++) {
			Row hour = new Row();
			hour.add(new Cell(getHourText(i + START_HOUR), Color.LIGHTGREY, Color.BLACK));
			for (int j = 0; j < 7; j++) {
				hour.add(new Cell("2", Color.YELLOWGREEN, Color.BLACK, eventHandler));
			}
			rows.add(hour);
		}
	}
	
	private boolean isWeekend(java.util.Calendar cal) {
		int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
		return dayOfWeek == 1 || dayOfWeek == 7;
	}
	
	private String getHourText(int hour) {
		StringBuilder sb = new StringBuilder();
		sb.append(hour >= 10 ? hour : "0" + hour);
		sb.append(":00 - ");
		sb.append((hour + 1) >= 10 ? (hour + 1) : "0" + (hour + 1));
		sb.append(":00");
		return sb.toString();
	}

	private String getHeaderText(java.util.Calendar cal) {
		int year = cal.get(java.util.Calendar.YEAR);
		int month = cal.get(java.util.Calendar.MONTH) + 1;
		int date = cal.get(java.util.Calendar.DATE);
		StringBuilder sb = new StringBuilder();
		sb.append(year);
		sb.append("-");
		sb.append(month >= 10 ? month : "0" + month);
		sb.append("-");
		sb.append(date >= 10 ? date : "0" + date);
		return sb.toString();
	}
	
	public List<Row> getRows() {
		return rows;
	}

}
