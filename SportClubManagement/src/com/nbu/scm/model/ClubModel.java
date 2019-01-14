package com.nbu.scm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nbu.scm.bean.Club;
import com.nbu.scm.bean.ClubType;
import com.nbu.scm.bean.Court;
import com.nbu.scm.bean.CourtType;

public class ClubModel extends Base {

	private static final String COLUMN_ID = "CLUB.ID";
	private static final String COLUMN_NAME = "CLUB.NAME";
	private static final String COLUMN_ADDRESS = "CLUB.ADDRESS";
	private static final String COLUMN_PRICE = "CLUB.PRICE";
	
	private static final String GET_USER_BY_USERNAME_AND_PASSWORD = 
			"SELECT * FROM CLUB LEFT JOIN CLUB_TYPE ON CLUB.TYPE = CLUB_TYPE.ID WHERE CLUB.ID=?";
	
	public static Club fill(Club club, ResultSet rs) throws SQLException {
		if (club == null) {
			club = new Club();
		}
		club.setId(rs.getInt(COLUMN_ID));
		club.setName(rs.getString(COLUMN_NAME));
		club.setAddress(rs.getString(COLUMN_ADDRESS));
		club.setType(ClubTypeModel.fill(new ClubType(), rs));
		club.addCourt(new Court(rs.getInt(CourtModel.COLUMN_ID), rs.getInt(CourtModel.COLUMN_NUMBER),
				CourtTypeModel.fill(new CourtType(), rs)));
		return club;
	}

	public static Club getClub(int id) throws SQLException {
		Club club = null;
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = getConnection();
			preparedStatement = con.prepareStatement(GET_USER_BY_USERNAME_AND_PASSWORD);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				club = fill(club, rs);
			}

		} finally {
			close(con);
			close(preparedStatement);
		}
		return club;
	}
}
