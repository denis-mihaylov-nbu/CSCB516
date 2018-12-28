package com.nbu.scm.view;

import com.nbu.scm.bean.Club;
import com.nbu.scm.view.layout.CalendarView;
import com.nbu.scm.view.layout.Cell;
import com.nbu.scm.view.layout.Row;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class CalendarPane extends GridPane {
	
	private Club club;
	private int page = 0;
	
	CalendarView calendarView;
	ReservationPanel reservationPanel = new ReservationPanel(this);
	
	public CalendarPane(Club club) throws Exception {
		super();
		this.club = club;
		init();
	}

	public void init() throws Exception{

		calendarView = new CalendarView(club, page, this);

		Button prev = new Button("<<");
		Button next = new Button(">>");

		int rowId = 0;
		add(prev, 1, rowId);
		add(next, 2, rowId);
		
		for (Row row : calendarView.getRows()) {
			int cellId = 0;
			for (Cell cell : row.getCells()) {
				add(cell, cellId++, rowId);
			}
			rowId++;
		}
		
	}

	public ReservationPanel getReservationPanel() {
		return reservationPanel;
	}
}
