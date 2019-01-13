package com.nbu.scm.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Base {

	public static Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scm?useSSL=false&serverTimezone=EET","root", "root");
//		Connection con = DriverManager.getConnection("jdbc:mysql://pine.rdb.superhosting.bg:3306/denismih_scm?useSSL=false&serverTimezone=EET","denismih_root", "Simona1010");
		return con;
	}

	public static void close(Connection con) throws SQLException {
		if (con != null){
			con.close();
		}
	}

	public static void close(Statement statement) throws SQLException {
		if (statement != null){
			statement.close();
		}
	}
	
	public static void close(PreparedStatement preparedStatement) throws SQLException {
		if (preparedStatement != null){
			preparedStatement.close();
		}
	}

}
