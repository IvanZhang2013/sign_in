package com.howbuy.database.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class SqlLiteJDBCUtils {

	@Value("${dataBaseUrl}")
	private String SQLLITE_URI;

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConn() throws SQLException, ClassNotFoundException {

		Connection conn = DriverManager.getConnection(SQLLITE_URI);
		return conn;
	}

	public void closeConn(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException {

		if (rs != null) {
			rs.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (conn != null) {
			conn.close();
		}

	}

	public void closeConn(Connection conn, PreparedStatement ps) throws SQLException {

		if (ps != null) {
			ps.close();
		}
		if (conn != null) {
			conn.close();
		}

	}

}
