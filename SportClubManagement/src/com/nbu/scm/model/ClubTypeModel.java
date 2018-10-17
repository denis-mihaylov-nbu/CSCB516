package com.nbu.scm.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.nbu.scm.bean.ClubType;

public class ClubTypeModel extends Base {

	private static final String COLUMN_ID = "CLUB_TYPE.ID";
	private static final String COLUMN_NAME = "CLUB_TYPE.NAME";

	public static ClubType fill(ClubType clubType, ResultSet rs) throws SQLException {
		clubType = new ClubType();
		clubType.setId(rs.getInt(COLUMN_ID));
		clubType.setName(rs.getString(COLUMN_NAME));
		return clubType;
	}
}
