package com.nbu.scm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;
import java.util.Set;

import com.nbu.scm.bean.ClubType;

public class ClubTypeModel extends Base {

	private static final String COLUMN_ID = "CLUB_TYPE.ID";
	private static final String COLUMN_NAME = "CLUB_TYPE.NAME";

	private static final String GET_CLUB_TYPES = "SELECT * FROM CLUB_TYPE";

	public static ClubType fill(ResultSet rs) throws SQLException {
		return fill(new ClubType(), rs);
	}
	public static ClubType fill(ClubType clubType, ResultSet rs) throws SQLException {
		clubType.setId(rs.getInt(COLUMN_ID));
		clubType.setName(rs.getString(COLUMN_NAME));
		return clubType;
	}

	public static Set<ClubType> getClubTypes() throws SQLException {
		Set<ClubType> clubs = new TreeSet<ClubType>();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = getConnection();
			preparedStatement = con.prepareStatement(GET_CLUB_TYPES);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				clubs.add(fill(rs));
			}

		} finally {
			close(con);
			close(preparedStatement);
		}
		return clubs;
	}
}
