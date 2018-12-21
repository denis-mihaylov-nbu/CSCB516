package com.nbu.scm.view.layout;

import java.util.ArrayList;
import java.util.List;

public class Row {

	List<Cell> cells;

	public Row() {
		super();
		this.cells = new ArrayList<Cell>();
	}

	public boolean add(Cell cell) {
		return cells.add(cell);
	}

	public Cell get(int index) {
		return cells.get(index);
	}

	public List<Cell> getCells() {
		return cells;
	}

}
