package com.nbu.scm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.List;
import java.util.Set;

import com.nbu.scm.bean.Court;

public class CourtModel extends Base {

	public static final String COLUMN_ID = "COURT.ID";
	public static final String COLUMN_NUMBER = "COURT.NUMBER";
	public static final String COLUMN_COURT_TYPE_ID = "COURT.COURT_TYPE_ID";
	public static final String COLUMN_PRICE = "COURT.PRICE";

	private static final String GET_AVAILABLE_COURTS = "SELECT * FROM COURT "+
	  "LEFT JOIN COURT_TYPE ON COURT_TYPE.ID = COURT.COURT_TYPE_ID "+
		"WHERE COURT.CLUB_TYPE_ID = ? AND COURT.ID NOT IN " +
	    "(SELECT COURT_ID FROM RESERVATION WHERE RESERVATION.CLUB_ID = ? AND RESERVATION.TIMESTAMP = ?)";
	
	private static final String GET_COURTS_BY_CLUB_ID = "SELECT * FROM COURT "+
			" WHERE CLUB_TYPE_ID = ?";
	
	private static final String INSERT_COURT = "INSERT INTO "
			+ "COURT (NUMBER, COURT_TYPE_ID, PRICE, CLUB_TYPE_ID) VALUES (?, ?, ?, ?)";

	private static final String UPDATE_COURT = "UPDATE COURT "
			+ "SET NUMBER = ?, COURT_TYPE_ID = ?, PRICE = ?, CLUB_TYPE_ID = ? " 
			+ "WHERE ID = ? ";
	
	public static Court fill(Court court, ResultSet rs) throws SQLException {
		court = new Court();
		court.setId(rs.getInt(COLUMN_ID));
		court.setNumber(rs.getInt(COLUMN_NUMBER));
		court.setType(CourtTypeModel.getCourtTypeById(rs.getInt(COLUMN_COURT_TYPE_ID)));
		court.setPrice(rs.getDouble(COLUMN_PRICE));
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

	public static Set<Court> getCourtsById(int clubId) throws SQLException {
		Set<Court> courts = new TreeSet<Court>();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = getConnection();
			preparedStatement = con.prepareStatement(GET_COURTS_BY_CLUB_ID);
			preparedStatement.setInt(1, clubId);
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

	public static Court update(Court court) throws SQLException {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = getConnection();
			int i = 1;
			preparedStatement = con.prepareStatement(UPDATE_COURT);
			preparedStatement.setInt(i++, court.getNumber());
			preparedStatement.setInt(i++, court.getType().getId());
			preparedStatement.setDouble(i++, court.getPrice());
			preparedStatement.setInt(i++, court.getClubId());
			preparedStatement.setInt(i++, court.getId());
			preparedStatement.executeUpdate();
		} finally {
			close(con);
			close(preparedStatement);
		}
		return court;
	}

	public static Court insert(Court court) throws SQLException {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = getConnection();
			preparedStatement = con.prepareStatement(INSERT_COURT);
			int i = 1;
			preparedStatement.setInt(i++, court.getNumber());
			preparedStatement.setInt(i++, court.getType().getId());
			preparedStatement.setDouble(i++, court.getPrice());
			preparedStatement.setInt(i++, court.getClubId());
			preparedStatement.executeUpdate();
		} finally {
			close(con);
			close(preparedStatement);
		}
		return court;
	}
	
}