package com.nbu.scm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.nbu.scm.bean.Court;
import com.nbu.scm.bean.CourtType;

public class CourtModel extends Base {

	public static final String COLUMN_ID = "COURT.ID";
	public static final String COLUMN_NUMBER = "COURT.NUMBER";

	private static final String GET_AVAILABLE_COURTS = "SELECT * FROM COURT "+
	  "LEFT JOIN COURT_TYPE ON COURT_TYPE.ID = COURT.COURT_TYPE_ID "+
		"WHERE COURT.CLUB_TYPE_ID = ? AND COURT.ID NOT IN " +
	    "(SELECT COURT_ID FROM RESERVATION WHERE RESERVATION.CLUB_ID = ? AND RESERVATION.TIMESTAMP = ?)";
	
	public static Court fill(Court court, ResultSet rs) throws SQLException {
		court = new Court();
		court.setId(rs.getInt(COLUMN_ID));
		court.setNumber(rs.getInt(COLUMN_NUMBER));
		court.setType(CourtTypeModel.fill(new CourtType(), rs));
		return court;
	}
	
	public static List<Court> getAvailableCourts(int clubId, Timestamp timestamp) throws SQLException {
		List<Court> courts = new ArrayList<Court>();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = getConnection();
			preparedStatement = con.prepareStatement(GET_AVAILABLE_COURTS);
			preparedStatement.setInt(1, clubId);
			preparedStatement.setInt(2, clubId);
			preparedStatement.setTimestamp(3, timestamp);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Court court = new Court();
				court= fill(court, rs);
				courts.add(court);
			}

		} finally {
			close(con);
			close(preparedStatement);
		}
		return courts;
	}
	
}