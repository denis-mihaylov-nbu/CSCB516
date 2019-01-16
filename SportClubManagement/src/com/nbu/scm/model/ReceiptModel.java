package com.nbu.scm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import com.nbu.scm.bean.Club;
import com.nbu.scm.bean.Receipt;
import com.nbu.scm.bean.Reservation;

public class ReceiptModel extends Base {

	private static final String COLUMN_ID = "RECEIPT.ID";
	private static final String COLUMN_VALUE = "RECEIPT.VALUE";
	private static final String COLUMN_TIMESTAMP = "RECEIPT.TIMESTAMP";

	private static final String GET_RECEIPTS_BY_CLUB_AND_DATE = "SELECT * FROM RECEIPT WHERE CLUB_ID = ? AND TIMESTAMP > ? AND TIMESTAMP < ?";

	private static final String CREATE_RECEIPT = "INSERT INTO "
			+ "RECEIPT (CLUB_ID, VALUE, TIMESTAMP) VALUES (?, ?, ?)";

	public static Receipt fill(ResultSet rs) throws SQLException {
		return fill(new Receipt(), rs);
	}

	public static Receipt fill(Receipt receipt, ResultSet rs) throws SQLException {
		receipt = new Receipt();
		receipt.setId(rs.getInt(COLUMN_ID));
		receipt.setValue(rs.getDouble(COLUMN_VALUE));
		receipt.setTimestamp(rs.getTimestamp(COLUMN_TIMESTAMP));
		return receipt;
	}

	public static Receipt create(Receipt receipt) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(CREATE_RECEIPT);
			int i = 1;
			ps.setInt(i++, receipt.getClub().getId());
			ps.setDouble(i++, receipt.getValue());
			ps.setTimestamp(i++, receipt.getTimestamp());
			ps.executeUpdate();
			for (Object res : receipt.getItems()) {
				if (res instanceof Reservation) {
					ReservationModel.updatePaid(con, (Reservation) res, true);
				}
			}
			con.commit();
		} catch (SQLException e) {
			if (con != null) {
				con.rollback();
			}
			throw e;
		} finally {
			close(con);
			close(ps);
		}
		return receipt;
	}

	public static Set<Receipt> read(Club club, LocalDate date) throws SQLException {
		Set<Receipt> receipts = new TreeSet<Receipt>();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = getConnection();
			preparedStatement = con.prepareStatement(GET_RECEIPTS_BY_CLUB_AND_DATE);
			preparedStatement.setInt(1, club.getId());
			preparedStatement.setTimestamp(2, Timestamp.valueOf(date.atStartOfDay()));
			preparedStatement.setTimestamp(3, Timestamp.valueOf(date.plusDays(1).atStartOfDay()));
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Receipt receipt = fill(rs);
				receipts.add(receipt);
			}

		} finally {
			close(con);
			close(preparedStatement);
		}
		return receipts;
	}

}