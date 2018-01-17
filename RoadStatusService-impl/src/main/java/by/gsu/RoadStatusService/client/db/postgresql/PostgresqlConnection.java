package by.gsu.RoadStatusService.client.db.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresqlConnection {
	private static String host = "ec2-107-21-95-70.compute-1.amazonaws.com";
	private static String port = "5432";
	private static String dbName = "d8qrlb02afc3bv";
	private static String user = "trggsxonkiqjxu";
	private static String password = "d77d37f09ee891feba5f69cd633255e7c37501cb51da12094b070f967af64cc1";

	private static Connection conn = null;
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection newConnection() throws SQLException {
		if (conn == null) {
			Properties props = new Properties();
			props.setProperty("user", user);
			props.setProperty("password", password);
			props.setProperty("ssl", "true");
			props.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
			String connectionString = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
			conn = DriverManager.getConnection(connectionString, props);
		}
		System.out.println("newConnection");
		return conn;
	}

	public static void main(String[] args) {

		try {
			PostgresqlConnection.newConnection();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}
