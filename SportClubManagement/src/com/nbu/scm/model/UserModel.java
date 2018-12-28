package com.nbu.scm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nbu.scm.bean.User;

public class UserModel extends Base {

	private static final String COLUMN_ID = "USER.ID";
	private static final String COLUMN_USERNAME = "USER.USERNAME";
	private static final String COLUMN_FIRST_NAME = "USER.FIRST_NAME";
	private static final String COLUMN_LAST_NAME = "USER.LAST_NAME";
	private static final String COLUMN_TYPE = "USER.TYPE";
	
	private static final String GET_USER_BY_USERNAME_AND_PASSWORD = "SELECT * FROM USER"
			+ " LEFT JOIN CLUB ON USER.CLUBID = CLUB.ID"
			+ " LEFT JOIN CLUB_TYPE ON CLUB.TYPE = CLUB_TYPE.ID"
			+ " LEFT JOIN COURT ON CLUB.ID = COURT.CLUB_TYPE_ID"
			+ " LEFT JOIN COURT_TYPE ON COURT.COURT_TYPE_ID = COURT_TYPE.ID"
			+ " WHERE USERNAME=? AND PASSWORD=?";

	public static User fill(User user, ResultSet rs) throws SQLException {
		if (user == null) {
			user = new User();
		}
		user.setId(rs.getInt(COLUMN_ID));
		user.setUsername(rs.getString(COLUMN_USERNAME));
		user.setFirstName(rs.getString(COLUMN_FIRST_NAME));
		user.setLastName(rs.getString(COLUMN_LAST_NAME));
		user.setClub(ClubModel.fill(user.getClub(), rs));
		user.setType(rs.getInt(COLUMN_TYPE));
		return user;
	}

	public static User getUserByUsernameAndPassword(String username, String password) throws SQLException {
		User user = null;
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = getConnection();
			preparedStatement = con.prepareStatement(GET_USER_BY_USERNAME_AND_PASSWORD);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				user = fill(user, rs);
			}
		} finally {
			close(con);
			close(preparedStatement);
		}
		return user;
	}

}
