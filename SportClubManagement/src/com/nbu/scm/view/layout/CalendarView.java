package com.nbu.scm.view.layout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.nbu.scm.bean.Club;
import com.nbu.scm.bean.Court;
import com.nbu.scm.bean.Reservation;
import com.nbu.scm.bean.Timestamp;
import com.nbu.scm.controller.CourtController;
import com.nbu.scm.view.CalendarPane;
import com.nbu.scm.view.ReservationPanel;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CalendarView {

	Club club;
	int page;
	List<Row> rows;

	CalendarPane calendarPane;
		
	CourtController courtController = new CourtController();

	private static final int START_HOUR = 8;
	private static final int OPEN_FOR = 14;

	public CalendarView(Club club, int page, CalendarPane calendarPane) throws Exception {
		super();
		this.club = club;
		this.page = page;
		this.calendarPane = calendarPane;
		init();
	}

	public void init() throws Exception {
		rows = new ArrayList<Row>();
		
		Row header = fillHeader();
		rows.add(header);
		
		for (int i = 0; i < OPEN_FOR; i++) {
			Row hour = fillRow(i);
			rows.add(hour);
		}
	}
	
	private Calendar getCalendar(int hoursOffset) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, START_HOUR);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(java.util.Calendar.DATE, page * 7);
		cal.add(Calendar.HOUR_OF_DAY, hoursOffset);
		return cal;
	}
	
	private Row fillHeader() {
		Row header = new Row();
		header.add(new Cell("", Color.LIGHTGREY, Color.BLACK));
		
		for (int i = 0; i < 7; i++) {
			Calendar cal = getCalendar(i * 24);
			header.add(new Cell(getHeaderText(cal), isWeekend(cal) ? Color.DARKGRAY : Color.LIGHTGREY, Color.BLACK));
		}
		return header;
	}
	
	private Row fillRow(int row) throws Exception {
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		    	Cell source = (Cell) mouseEvent.getSource();
		    	Court court =  new Court();
		    	try {
		    		Timestamp t = new Timestamp(source.getCal());
		    		new ReservationPanel(calendarPane).start(new Reservation(-1, t, club, court, ""));
				} catch (Exception e) {
					Alert alert = new Alert(AlertType.ERROR, e.getMessage());
					alert.showAndWait();
					e.printStackTrace();
				}
		    }
		};
		
		Row hour = new Row();
		hour.add(new Cell(getHourText(row + START_HOUR), Color.LIGHTGREY, Color.BLACK));
		for (int j = 0; j < 7; j++) {
			Calendar cal = getCalendar(j * 24 + row);
			int availableCourts = courtController.getAvailableCourts(club.getId(), new Timestamp(cal).getTimestamp()).size();
			hour.add(new Cell(availableCourts, availableCourts > 0 ? Color.YELLOWGREEN : Color.RED, Color.BLACK, cal, eventHandler));
		}
		return hour;
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
