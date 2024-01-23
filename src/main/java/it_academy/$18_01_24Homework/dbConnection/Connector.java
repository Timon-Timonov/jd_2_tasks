package it_academy.$18_01_24Homework.dbConnection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {


	public static Connection getConnection() throws SQLException {

		return DriverManager.getConnection(
				Recourse.getUrl(),
				Recourse.getUser(),
				Recourse.getPassword());
	}
}
