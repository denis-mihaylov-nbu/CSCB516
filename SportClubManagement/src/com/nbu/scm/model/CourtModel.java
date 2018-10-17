package com.nbu.scm.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.nbu.scm.bean.Court;
import com.nbu.scm.bean.CourtType;

public class CourtModel {

	private static final String COLUMN_ID = "COURT.ID";
	private static final String COLUMN_NUMBER = "COURT.NUMBER";

	public static Court fill(Court court, ResultSet rs) throws SQLException {
		court = new Court();
		court.setId(rs.getInt(COLUMN_ID));
		court.setNumber(rs.getInt(COLUMN_NUMBER));
		court.setType(CourtTypeModel.fill(new CourtType(), rs));
		return court;
	}

}
