package by.gsu.client.db.postgresql;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Aaa {
	public static void main(String[] args) throws Exception {
		Class.forName("org.postgresql.Driver");
		Connection connection = getConnection();

		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
		stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
		stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
		ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
		while (rs.next()) {
			System.out.println("Read from DB: " + rs.getTimestamp("tick"));
		}
	}

	private static Connection getConnection() throws URISyntaxException, SQLException {
		URI dbUri = new URI(
				"postgres://trggsxonkiqjxu:d77d37f09ee891feba5f69cd633255e7c37501cb51da12094b070f967af64cc1@ec2-107-21-95-70.compute-1.amazonaws.com:5432/d8qrlb02afc3bv");

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

		return DriverManager.getConnection(dbUrl, username, password);
	}
}
