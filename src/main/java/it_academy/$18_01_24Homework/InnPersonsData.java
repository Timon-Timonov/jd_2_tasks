package it_academy.$18_01_24Homework;

import java.sql.Date;
import java.sql.Time;

public class InnPersonsData {

	public static final int[] AGE = {12, 75, 18, 22, 19, 31};

	public static final double[] SALARY= {0.1, 3345.8, 500.5, 875.9, 600, 1800.45};

	public static final String[] PASSPORT = {null, "MC98756895", "MP78541268", "DD85219201", "MK55998741", "DP54100120"};

	public static final String[] ADDRESS = {"Jodino, Stroitelei str. 4-15", "Minsk, Nezavisimosti ave. 44-155', '1948-10-13",
			"San-Francisco, Liberti str. 15', '2005-09-19", "London, Allenclusive distr., 334-2', '2003-02-08",
			"Minsk, Surganova str., 56-79", "Brest, Druzjbi ave., 67-A-55"};

	public static final Date[] DATE_OF_BIRTH = {Date.valueOf("2011-05-15"), Date.valueOf("1948-10-13"),
			Date.valueOf("2005-09-19"), Date.valueOf("2003-02-08"),
			Date.valueOf("2004-07-29"), Date.valueOf("1992-11-01")};

	public static final Time[] TIME_TO_LUNCH = {Time.valueOf("12:00:00"), Time.valueOf("13:30:00"),
			Time.valueOf("14:20:00"), Time.valueOf("13:40:00"),
			Time.valueOf("12:45:00"), Time.valueOf("13:00:00")};

	public static final String[] LETTER = {"It is a littel chaild", "It is an old man",
			"It is a student", "It is a yong girl",
			"It is a teenager", "It is a strong man"};
}
