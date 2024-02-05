package it_academy.lesson18_01_24Homework.dbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectorToDB {
	Connection getConnection() throws SQLException;
}
