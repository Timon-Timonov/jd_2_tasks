package it_academy.$18_01_24Homework.dbConnection.mySQLlocalDB;

import java.util.ResourceBundle;

public class Recourse {

	public static final String DATABASE_RESOURCE = "dataBase";
	private static final String URL_KEY = "URL";
	private static final String USER_KEY = "USER";
	private static final String PASSWORD_KEY = "PASSWORD";

	private static final ResourceBundle bundle = ResourceBundle.getBundle(DATABASE_RESOURCE);

	private static final String URL = getValue(URL_KEY);
	private static final String USER = getValue(USER_KEY);
	private static final String PASSWORD = getValue(PASSWORD_KEY);

	private static String getValue(String key) {

		return bundle.getString(key);
	}

	public static String getUrl() {

		return URL;
	}

	public static String getUser() {

		return USER;
	}

	public static String getPassword() {

		return PASSWORD;
	}
}
