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
import com.nbu.scm.bean.filter.ReservationFilter;

public class ReservationModel extends Base {

	private static final String COLUMN_ID = "RESERVATION.ID";
	private static final String COLUMN_CLUB_ID = "RESERVATION.CLUB_ID";
	private static final String COLUMN_NAME = "RESERVATION.NAME";
	private static final String COLUMN_TIMESTAMP = "RESERVATION.TIMESTAMP";
	private static final String COLUMN_PAID = "RESERVATION.PAID";

	public static Reservation fill(Reservation reservation, ResultSet rs) throws SQLException {
		reservation = new Reservation();
		reservation.setId(rs.getInt(COLUMN_ID));
		reservation.setName(rs.getString(COLUMN_NAME));
		reservation.setDate(rs.getTimestamp(COLUMN_TIMESTAMP));
		reservation.setCourt(CourtModel.fill(new Court(), rs));
		return reservation;
	}

	private static final String CREATE_RESERVATION = "INSERT INTO "
			+ "RESERVATION (TIMESTAMP, CLUB_ID, COURT_ID, NAME) " + "VALUES (?, ?, ?, ?)";

	private static final String GET_RESERVATIONS = "SELECT * FROM RESERVATION "
			+ "LEFT JOIN COURT ON RESERVATION.COURT_ID = COURT.ID "
			+ "LEFT JOIN COURT_TYPE ON COURT.COURT_TYPE_ID = COURT_TYPE.ID ";

	private static final String WHERE_CLUB_ID = COLUMN_CLUB_ID + " =? ";

	private static final String WHERE_NAME = COLUMN_NAME + " LIKE ? ";

	private static final String WHERE_TIMESTAMP_GQ = COLUMN_TIMESTAMP + " >= ? ";

	private static final String WHERE_TIMESTAMP_LQ = COLUMN_TIMESTAMP + " <= ? ";

	private static final String WHERE_PAID = COLUMN_PAID + " = ? ";

	private static final String GET_RESERVATIONS_BY_TIMESTAMP = "SELECT * FROM RESERVATION "
			+ "LEFT JOIN COURT ON RESERVATION.COURT_ID = COURT.ID "
			+ "LEFT JOIN COURT_TYPE ON COURT.COURT_TYPE_ID = COURT_TYPE.ID " + "WHERE RESERVATION.TIMESTAMP = ?";

	private static final String UPDATE_RESERVATION_PAID = "UPDATE RESERVATION " + "SET PAID = ? " + "WHERE ID = ? ";

	public static List<Reservation> get(ReservationFilter filter) throws SQLException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String whereClause = getWhere(filter);
			ps = con.prepareStatement(GET_RESERVATIONS + whereClause);
			fillPreparedStatement(ps, filter);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Reservation reservation = new Reservation();
				reservation = fill(reservation, rs);
				reservations.add(reservation);
			}

		} finally {
			close(con);
			close(ps);
		}
		return reservations;
	}

	private static String getWhere(ReservationFilter filter) {
		StringBuilder sb = new StringBuilder();
		if (filter.getClubId() > 0) {
			if (sb.length() == 0) {
				sb.append(" WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(WHERE_CLUB_ID);
		}
		if (filter.getName() != null && !filter.getName().isEmpty()) {
			if (sb.length() == 0) {
				sb.append(" WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(WHERE_NAME);
		}
		if (filter.getFromDate() != null) {
			if (sb.length() == 0) {
				sb.append(" WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(WHERE_TIMESTAMP_GQ);
		}
		if (filter.getToDate() != null) {
			if (sb.length() == 0) {
				sb.append(" WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(WHERE_TIMESTAMP_LQ);
		}
		if (sb.length() == 0) {
			sb.append(" WHERE ");
		} else {
			sb.append(" AND ");
		}
		sb.append(WHERE_PAID);
		return sb.toString();
	}

	private static void fillPreparedStatement(PreparedStatement ps, ReservationFilter filter) throws SQLException {
		int index = 1;
		if (filter.getClubId() > 0) {
			ps.setInt(index++, filter.getClubId());
		}
		if (filter.getName() != null && !filter.getName().isEmpty()) {
			ps.setString(index++, filter.getName());
		}
		if (filter.getFromDate() != null) {
			ps.setTimestamp(index++, filter.getFromDate());
		}
		if (filter.getToDate() != null) {
			ps.setTimestamp(index++, filter.getToDate());
		}
		ps.setBoolean(index++, filter.getPaid());
	}

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

//where do we use updatePaid method
	public static Reservation updatePaid(Connection con, Reservation reservation, boolean paid) throws SQLException {
		PreparedStatement ps = null;
		System.out.println(paid);
		System.exit(0);
		try {
			ps = con.prepareStatement(UPDATE_RESERVATION_PAID);
			int i = 1;
			ps.setBoolean(i++, paid);
			ps.setInt(i++, reservation.getId());
			ps.executeUpdate();
		} finally {
			close(ps);
		}
		return reservation;
	}

}
