package com.nbu.scm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nbu.scm.bean.Club;
import com.nbu.scm.bean.ClubType;

public class ClubModel extends Base {

	private static final String COLUMN_ID = "CLUB.ID";
	private static final String COLUMN_NAME = "CLUB.NAME";
	private static final String COLUMN_ADDRESS = "CLUB.ADDRESS";
	
	private static final String GET_USER_BY_USERNAME_AND_PASSWORD = "SELECT * FROM CLUB LEFT JOIN CLUB_TYPE ON CLUB.TYPE = CLUB_TYPE.ID WHERE ID=?";

	public static Club fill(Club club, ResultSet rs) throws SQLException {
		club = new Club();
		club.setId(rs.getInt(COLUMN_ID));
		club.setName(rs.getString(COLUMN_NAME));
		club.setAddress(rs.getString(COLUMN_ADDRESS));
		club.setType(ClubTypeModel.fill(new ClubType(), rs));
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
