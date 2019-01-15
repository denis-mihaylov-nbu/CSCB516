package com.nbu.scm.model;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;
import java.util.Set;

import com.nbu.scm.bean.RoleType;
import com.nbu.scm.bean.User;
import com.nbu.scm.security.Cryptography;

public class UserModel extends Base {

	private static final String COLUMN_ID = "USER.ID";
	private static final String COLUMN_USERNAME = "USER.USERNAME";
	private static final String COLUMN_FIRST_NAME = "USER.FIRST_NAME";
	private static final String COLUMN_LAST_NAME = "USER.LAST_NAME";
	private static final String COLUMN_TYPE = "USER.TYPE";
	private static final String COLUMN_CLUB_ID = "USER.CLUBID";

	private static final String GET_USER_BY_USERNAME_AND_PASSWORD = "SELECT * FROM USER"
			+ " LEFT JOIN CLUB ON USER.CLUBID = CLUB.ID"
			+ " LEFT JOIN CLUB_TYPE ON CLUB.TYPE = CLUB_TYPE.ID"
			+ " LEFT JOIN COURT ON CLUB.ID = COURT.CLUB_TYPE_ID"
			+ " LEFT JOIN COURT_TYPE ON COURT.COURT_TYPE_ID = COURT_TYPE.ID"
			+ " WHERE USERNAME=? AND PASSWORD=?";

	private static final String GET_USERS = "SELECT * FROM USER";
	
	private static final String GET_USER_BY_CLUB_ID = GET_USERS
			+ " WHERE CLUBID=?";	

	private static final String INSERT_USER = "INSERT INTO "
			+ "USER (USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, CLUBID, TYPE) VALUES (?, ?, ?, ?, ?, ?)";

	private static final String UPDATE_USER = "UPDATE USER "
			+ "SET PASSWORD = ?, USERNAME = ?, FIRST_NAME = ?, LAST_NAME = ?, CLUBID = ?, TYPE = ? " 
			+ "WHERE ID = ? ";
	
	private static final String UPDATE_USER_NO_PASSWORD = "UPDATE USER "
			+ "SET USERNAME = ?, FIRST_NAME = ?, LAST_NAME = ?, CLUBID = ?, TYPE = ? " 
			+ "WHERE ID = ? ";

	public static User fill(User user, ResultSet rs) throws SQLException {
		if (user == null) {
			user = new User();
		}
		user.setId(rs.getInt(COLUMN_ID));
		user.setUsername(rs.getString(COLUMN_USERNAME));
		user.setFirstName(rs.getString(COLUMN_FIRST_NAME));
		user.setLastName(rs.getString(COLUMN_LAST_NAME));
		user.setClub(ClubModel.getClub(rs.getInt(COLUMN_CLUB_ID)));
		user.setType(RoleType.getById(rs.getInt(COLUMN_TYPE)));
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

	public static Set<User> getUsersByClubId(int clubId) throws SQLException {
		Set<User> users = new TreeSet<User>();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = getConnection();
			if (clubId > 0) {				
				preparedStatement = con.prepareStatement(GET_USER_BY_CLUB_ID);
				preparedStatement.setInt(1, clubId);
			} else {
				preparedStatement = con.prepareStatement(GET_USERS);
			}
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				users.add(fill(new User(), rs));
			}
		} finally {
			close(con);
			close(preparedStatement);
		}
		return users;
	}

	public static User update(User user) throws SQLException, NoSuchAlgorithmException {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = getConnection();
			int i = 1;
			if (!user.getPassword().equals(Cryptography.cryptSHA256(""))) {
				preparedStatement = con.prepareStatement(UPDATE_USER);
				preparedStatement.setString(i++, user.getPassword());
			} else {
				preparedStatement = con.prepareStatement(UPDATE_USER_NO_PASSWORD);
			}
			preparedStatement.setString(i++, user.getUsername());
			preparedStatement.setString(i++, user.getFirstName());
			preparedStatement.setString(i++, user.getLastName());
			preparedStatement.setInt(i++, user.getClub().getId());
			preparedStatement.setInt(i++, user.getType().getId());
			preparedStatement.setInt(i++, user.getId());
			preparedStatement.executeUpdate();
		} finally {
			close(con);
			close(preparedStatement);
		}
		return user;
	}

	public static User insert(User user) throws SQLException {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = getConnection();
			preparedStatement = con.prepareStatement(INSERT_USER);
			int i = 1;
			preparedStatement.setString(i++, user.getUsername());
			preparedStatement.setString(i++, user.getPassword());
			preparedStatement.setString(i++, user.getFirstName());
			preparedStatement.setString(i++, user.getLastName());
			preparedStatement.setInt(i++, user.getClub().getId());
			preparedStatement.setInt(i++, user.getType().getId());
			preparedStatement.executeUpdate();
		} finally {
			close(con);
			close(preparedStatement);
		}
		return user;
	}

}
