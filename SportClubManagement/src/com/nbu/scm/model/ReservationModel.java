package com.nbu.scm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.nbu.scm.bean.Reservation;

public class ReservationModel extends Base {

	private static final String CREATE_RESERVATION = "INSERT INTO RESERVATION (TIMESTAMP, CLUB_ID, COURT_ID, NAME) VALUES (?, ?, ?, ?)";
	
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

}
