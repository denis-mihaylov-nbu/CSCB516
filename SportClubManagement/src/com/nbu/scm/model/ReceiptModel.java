package com.nbu.scm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nbu.scm.bean.Receipt;
import com.nbu.scm.bean.Reservation;

public class ReceiptModel extends Base {

	public static final String COLUMN_ID = "COURT.ID";
	public static final String COLUMN_NUMBER = "COURT.NUMBER";
	
	private static final String CREATE_RECEIPT = "INSERT INTO "
			+ "RECEIPT (CLUB_ID, VALUE, TIMESTAMP) " + "VALUES (?, ?, ?)";

	public static Receipt fill(Receipt receipt, ResultSet rs) throws SQLException {
		receipt = new Receipt();
//		receipt.setId(rs.getInt(COLUMN_ID));
//		receipt.setNumber(rs.getInt(COLUMN_NUMBER));
//		receipt.setType(CourtTypeModel.fill(new CourtType(), rs));
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

}