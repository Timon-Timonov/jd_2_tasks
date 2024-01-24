package it_academy.$18_01_24Homework.dbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectorToDB {
	Connection getConnection() throws SQLException;
}
