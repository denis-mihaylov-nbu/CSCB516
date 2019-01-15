package com.nbu.scm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;
import java.util.Set;

import com.nbu.scm.bean.CourtType;

public class CourtTypeModel extends Base {

	private static final String COLUMN_ID = "ID";
	private static final String COLUMN_TYPE = "TYPE";
	
	private static final String GET_COURT_TYPES = "SELECT * FROM COURT_TYPE";
	
	private static final String GET_COURT_TYPE_BY_ID = GET_COURT_TYPES + 
			" WHERE ID = ?";


	public static CourtType fill(ResultSet rs) throws SQLException {
		return fill(new CourtType(), rs);
	}
	
	public static CourtType fill(CourtType courtType, ResultSet rs) throws SQLException {
		courtType = new CourtType();
		courtType.setId(rs.getInt(COLUMN_ID));
		courtType.setType(rs.getString(COLUMN_TYPE));
		return courtType;
	}

	public static Set<CourtType> getCourtTypes() throws SQLException {
		Set<CourtType> courtTypes = new TreeSet<CourtType>();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = getConnection();
			preparedStatement = con.prepareStatement(GET_COURT_TYPES);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				courtTypes.add(fill(rs));
			}

		} finally {
			close(con);
			close(preparedStatement);
		}
		return courtTypes;
	}

	public static CourtType getCourtTypeById(int id) throws SQLException {
		CourtType courtType = null;
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = getConnection();
			preparedStatement = con.prepareStatement(GET_COURT_TYPE_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				courtType = fill(rs);
			}

		} finally {
			close(con);
			close(preparedStatement);
		}
		return courtType;
	}
}
