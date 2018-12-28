package com.nbu.scm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.nbu.scm.bean.Court;
import com.nbu.scm.bean.Reservation;

public class ReservationModel extends Base {
	
	private static final String COLUMN_ID = "RESERVATION.ID";
	private static final String COLUMN_NAME = "RESERVATION.NAME";
	private static final String COLUMN_TIMESTAMP = "RESERVATION.TIMESTAMP";
	
	public static Reservation fill(Reservation reservation, ResultSet rs) throws SQLException {
		reservation = new Reservation();
		reservation.setId(rs.getInt(COLUMN_ID));
		reservation.setName(rs.getString(COLUMN_NAME));
		reservation.setDate(rs.getTimestamp(COLUMN_TIMESTAMP));
		reservation.setCourt(CourtModel.fill(new Court(), rs));
		return reservation;
	}

	private static final String CREATE_RESERVATION = "INSERT INTO "
			+ "RESERVATION (TIMESTAMP, CLUB_ID, COURT_ID, NAME) "
			+ "VALUES (?, ?, ?, ?)";
	
	private static final String GET_RESERVATIONS_BY_TIMESTAMP = "SELECT * FROM RESERVATION "
			+ "LEFT JOIN COURT ON RESERVATION.COURT_ID = COURT.ID "
			+ "LEFT JOIN COURT_TYPE ON COURT.COURT_TYPE_ID = COURT_TYPE.ID "
			+ "WHERE RESERVATION.TIMESTAMP = ?";
	
	public static Reservation create(Reservation reservation) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(CREATE_RESERVATION);
			int i = 1;
			ps.setTimestamp(i++, reservation.getTimestamp());
			ps.setInt(i++, reservation.getClub().getId());
			ps.setInt(i++, reservation.getCourt().getNumber());
			ps.setString(i++, reservation.getName());
			ps.executeUpdate();
		} finally {
			close(con);
			close(ps);
		}
		return reservation;
	}

	public static List<Reservation> getByTimestamp(Timestamp timestamp) throws SQLException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = getConnection();
			preparedStatement = con.prepareStatement(GET_RESERVATIONS_BY_TIMESTAMP);
			preparedStatement.setTimestamp(1, timestamp);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Reservation reservation = new Reservation();
				reservation = fill(reservation, rs);
				reservations.add(reservation);
			}

		} finally {
			close(con);
			close(preparedStatement);
		}
		return reservations;
	}

}
