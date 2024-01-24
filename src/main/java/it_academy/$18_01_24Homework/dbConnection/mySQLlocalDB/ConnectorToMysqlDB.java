package it_academy.$18_01_24Homework.dbConnection.mySQLlocalDB;


import it_academy.$18_01_24Homework.dbConnection.ConnectorToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorToMysqlDB implements ConnectorToDB {

	private static ConnectorToMysqlDB connector = null;
	private static Connection connection = null;

	private ConnectorToMysqlDB() {
	}

	public static synchronized ConnectorToMysqlDB getInstance() {
		if (connector == null) {
			connector = new ConnectorToMysqlDB();
		}
		return connector;
	}

	public synchronized Connection getConnection() throws SQLException {

		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(
					Recourse.getUrl(),
					Recourse.getUser(),
					Recourse.getPassword());
		}
		return connection;
	}
}
