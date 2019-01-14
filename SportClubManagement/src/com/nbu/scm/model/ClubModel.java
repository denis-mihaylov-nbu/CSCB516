package com.nbu.scm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.nbu.scm.bean.Club;
import com.nbu.scm.bean.ClubType;
import com.nbu.scm.bean.Reservation;

public class ClubModel extends Base {

	private static final String COLUMN_ID = "CLUB.ID";
	private static final String COLUMN_NAME = "CLUB.NAME";
	private static final String COLUMN_ADDRESS = "CLUB.ADDRESS";
	private static final String COLUMN_PRICE = "CLUB.PRICE";

	private static final String GET_CLUBS = 
			"SELECT * FROM CLUB LEFT JOIN CLUB_TYPE ON CLUB.TYPE = CLUB_TYPE.ID";
	
	private static final String GET_CLUB_BY_ID = 
			"SELECT * FROM CLUB LEFT JOIN CLUB_TYPE ON CLUB.TYPE = CLUB_TYPE.ID WHERE CLUB.ID=?";

	private static final String INSERT_CLUB = "INSERT INTO "
			+ "CLUB (NAME, ADDRESS, TYPE) " + "VALUES (?, ?, ?)";
	
	private static final String UPDATE_CLUB = "UPDATE CLUB "
			+ "SET NAME = ?, ADDRESS = ?, TYPE = ? " 
			+ "WHERE ID = ? ";
	
	public static Club fill(ResultSet rs) throws SQLException {
		return fill(null, rs);
	}
	
	public static Club fill(Club club, ResultSet rs) throws SQLException {
		if (club == null) {
			club = new Club();
		}
		club.setId(rs.getInt(COLUMN_ID));
		club.setName(rs.getString(COLUMN_NAME));
		club.setAddress(rs.getString(COLUMN_ADDRESS));
		club.setType(ClubTypeModel.fill(new ClubType(), rs));
		return club;
	}

	public static Set<Club> getClubs() throws SQLException {
		Set<Club> clubs = new HashSet<Club>();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = getConnection();
			preparedStatement = con.prepareStatement(GET_CLUBS);
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
	
	public static Club getClub(int id) throws SQLException {
		Club club = null;
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = getConnection();
			preparedStatement = con.prepareStatement(GET_CLUB_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				club = fill(club, rs);
			}

		} finally {
			close(con);
			close(preparedStatement);
		}
		return club;
	}

	public static Club update(Club club) throws SQLException {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = getConnection();
			preparedStatement = con.prepareStatement(UPDATE_CLUB);
			int i = 1;
			preparedStatement.setString(i++, club.getName());
			preparedStatement.setString(i++, club.getAddress());
			preparedStatement.setInt(i++, club.getType().getId());
			preparedStatement.setInt(i++, club.getId());
			preparedStatement.executeUpdate();
		} finally {
			close(con);
			close(preparedStatement);
		}
		return club;
	}

	public static Club insert(Club club) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(INSERT_CLUB);
			int i = 1;
			ps.setString(i++, club.getName());
			ps.setString(i++, club.getAddress());
			ps.setInt(i++, club.getType().getId());
			ps.executeUpdate();
		} finally {
			close(con);
			close(ps);
		}
		return club;
	}
	
}
