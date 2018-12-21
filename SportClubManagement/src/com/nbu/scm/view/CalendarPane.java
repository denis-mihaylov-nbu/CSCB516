package com.nbu.scm.view;

import com.nbu.scm.bean.Club;
import com.nbu.scm.view.layout.Calendar;
import com.nbu.scm.view.layout.Cell;
import com.nbu.scm.view.layout.Row;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class CalendarPane extends GridPane {
	
	private Club club;
	
	public CalendarPane(Club club) {
		super();
		this.club = club;
		init(0);
	}

	public void init(int page){

		Calendar calendar = new Calendar(club, page);

		Button prev = new Button("<<");
		Button next = new Button(">>");

		int rowId = 0;
		add(prev, 1, rowId);
		add(next, 2, rowId);
		
		for (Row row : calendar.getRows()) {
			int cellId = 0;
			for (Cell cell : row.getCells()) {
				add(cell, cellId++, rowId);
			}
			rowId++;
		}
		
	}
	

}
