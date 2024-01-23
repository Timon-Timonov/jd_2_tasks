package it_academy.$18_01_24Homework.dao.personDaoImpl;

public interface Queries {

	String ADD_QUERY = "INSERT INTO people.Person (age, salary, passport, address, dateOfBirthday, timeToLunch, letter)\n" +
			" VALUE (?, ?, ?, ?, ?, ?, ?);";

	String GET_ALL_QUERY = "SELECT * FROM people.Person;";

	String GET_BY_ID_QUERY = "SELECT * FROM people.Person p WHERE p.id=?;";

	String DELETE_BY_ID_QUERY = "DELETE FROM People.person p WHERE p.id=?;";

	String UPDATE_QUERY = "UPDATE People.person p set\n" +
			"                           p.age=?,\n" +
			"                           p.salary=?,\n" +
			"                           p.passport=?,\n" +
			"                           p.address=?,\n" +
			"                           p.dateOfBirthday=?,\n" +
			"                           p.timeToLunch=?,\n" +
			"                           p.letter=?\n" +
			"WHERE p.id=?;";
}
