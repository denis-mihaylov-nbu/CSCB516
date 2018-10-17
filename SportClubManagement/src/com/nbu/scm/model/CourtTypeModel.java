package com.nbu.scm.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.nbu.scm.bean.CourtType;

public class CourtTypeModel {

	private static final String COLUMN_ID = "COURT_TYPE.ID";
	private static final String COLUMN_TYPE = "COURT_TYPE.TYPE";	

	public static CourtType fill(CourtType courtType, ResultSet rs) throws SQLException {
		courtType = new CourtType();
		courtType.setId(rs.getInt(COLUMN_ID));
		courtType.setType(rs.getString(COLUMN_TYPE));
		return courtType;
	}
}
