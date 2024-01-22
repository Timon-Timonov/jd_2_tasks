package it_academy.$16_01_24Homework;

import java.sql.*;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = null;
		Statement statment = null;
		ResultSet res = null;

		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/people",
					"root",
					"1234");

			connection.setAutoCommit(false);
			statment = connection.createStatement();

			statment.addBatch("CREATE TABLE Person\n" +
					"(\n" +
					"    id             int NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
					"    age            int,\n" +
					"    salary         dec,\n" +
					"    passport       char(10),\n" +
					"    address        varchar(200),\n" +
					"    dateOfBirthday date,\n" +
					"    dateTimeCreate timestamp DEFAULT CURRENT_TIMESTAMP,\n" +
					"    timeToLunch    time,\n" +
					"    letter         text\n" +
					");");


			statment.addBatch("INSERT INTO Person (age, salary, passport, address, dateOfBirthday, timeToLunch, letter)\n" +
					"VALUE (12, 0.1, NULL, 'Jodino, Stroitelei str. 4-15', '2011-05-15', '12:00:00',\n" +
					"        'It is a littel chaild');");

			statment.addBatch("INSERT INTO Person (age, salary, passport, address, dateOfBirthday, timeToLunch, letter)\n" +
					"VALUE  (75, 3345.8, 'MC98756895', 'Minsk, Nezavisimosti ave. 44-155', '1948-10-13', '13:30:00',\n" +
					"        'It is an old man');");

			statment.addBatch("INSERT INTO Person (age, salary, passport, address, dateOfBirthday, timeToLunch, letter)\n" +
					"VALUE (18, 500.5, 'MP78541268', 'San-Francisco, Liberti str. 15', '2005-09-19', '14:20:00',\n" +
					"        'It is a student');");

			statment.addBatch("INSERT INTO Person (age, salary, passport, address, dateOfBirthday, timeToLunch, letter)\n" +
					"VALUE (22, 1175.9, 'DD85219201', 'London, Allenclusive distr., 334-2', '2003-02-08', '13:40:00',\n" +
					"        'It is a ypung girl');");

			statment.addBatch("INSERT INTO Person (age, salary, passport, address, dateOfBirthday, timeToLunch, letter)\n" +
					"VALUE (19, 600, 'MK55998741', 'Minsk, Surganova str., 56-79', '2004-07-29', '12:45:00',\n" +
					"        'It is a teenager');");

			statment.addBatch("INSERT INTO Person (age, salary, passport, address, dateOfBirthday, timeToLunch, letter)\n" +
					"VALUE (31, 1800.45, 'DP54100120', 'Brest, Druzjbi ave., 67-A-55', '1992-11-01', '13:00:00',\n" +
					"        'It is a strong man');");

			statment.executeBatch();
			connection.commit();
			connection.setAutoCommit(true);

			res = statment.executeQuery("SELECT *\n" +
					"FROM Person\n" +
					"WHERE age > 21\n" +
					"ORDER BY dateTimeCreate;");

			System.out.println("Selected persons:");
			while (res.next()) {
				System.out.print(res.getString(1) + "\t");
				System.out.print(res.getString(2) + "\t");
				System.out.print(res.getString(3) + "\t");
				System.out.print(res.getString(4) + "\t");
				System.out.print(res.getString(5) + "\t");
				System.out.print(res.getString(6) + "\t");
				System.out.print(res.getString(7) + "\t");
				System.out.print(res.getString(8) + "\t");
				System.out.println(res.getString(9));
			}
			System.out.println();


			DatabaseMetaData dbmd = connection.getMetaData();
			res = dbmd.getTables(null, null, "Person", null);
			System.out.println("MetaData about table Person:");
			String tableName = null;
			while (res.next()) {
				tableName = res.getString("TABLE_NAME");
			}
			System.out.println("TABLE_NAME: " + tableName);

			res = dbmd.getColumns(null, null, tableName, null);
			System.out.println("Columns: ");
			while (res.next()) {
				System.out.println(res.getString("COLUMN_NAME") + " "
						+ res.getString("TYPE_NAME") + " "
						+ res.getString("COLUMN_SIZE"));
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
			assert connection != null;
			connection.rollback();

		} finally {
			if (res != null) {
				res.close();
			}
			if (statment != null) {
				try {
					statment.close();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		}

	}
}
