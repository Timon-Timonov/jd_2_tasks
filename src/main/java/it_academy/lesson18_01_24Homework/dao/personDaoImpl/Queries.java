package it_academy.lesson18_01_24Homework.dao.personDaoImpl;

public interface Queries {

	String ADD_QUERY = "INSERT INTO people.Person (age, salary, passport, address, dateOfBirthday, timeToLunch, letter)\n" +
			" VALUE (?, ?, ?, ?, ?, ?, ?) ";

	String GET_ALL_QUERY = "SELECT * FROM people.Person p ";

	String GET_WITH_AGE_GREATER_THEN_QUERY = "SELECT * FROM people.Person p WHERE p.age>? ";

	String GET_BY_ID_QUERY = "SELECT * FROM people.Person p WHERE p.id=? ";

	String DELETE_BY_ID_QUERY = "DELETE FROM People.person p WHERE p.id=? ";

	String UPDATE_QUERY = "UPDATE People.person p set\n" +
			"                           p.age=?,\n" +
			"                           p.salary=?,\n" +
			"                           p.passport=?,\n" +
			"                           p.address=?,\n" +
			"                           p.dateOfBirthday=?,\n" +
			"                           p.timeToLunch=?,\n" +
			"                           p.letter=?\n" +
			"WHERE p.id=?;";

	String GET_ALL_WITH_WHERE_QUERY = "SELECT * FROM people.Person p WHERE  ";

	String[] columns = new String[]{
			" p.id ",
			" p.age ",
			" p.salary ",
			" p.passport ",
			" p.address ",
			" p.dateOfBirthday ",
			" p.dateTimeCreate ",
			" p.timeToLunch ",
			" p.letter "
	};

	String GREATER_EXCLUDE = " > ";
	String GREATER_INCLUDE = " >= ";
	String LESS_EXCLUDE = " < ";
	String LESS_INCLUDE = " <= ";
	String EQUALS = " = ";
	String NOT_EQUALS = " <> ";

	String ORDER_BY = " ORDER BY ";
	String INVERT_ORDER = " DESC ";
	String POINT = ", ";
	String SPACE = " ";


}
