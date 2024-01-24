package it_academy.$18_01_24Homework;

import it_academy.$18_01_24Homework.dao.PersonDao;
import it_academy.$18_01_24Homework.dao.personDaoImpl.PersonDaoImpl;
import it_academy.$18_01_24Homework.dao.personDaoImpl.Queries;
import it_academy.$18_01_24Homework.dao.personDaoImpl.QueryConstructor;
import it_academy.$18_01_24Homework.dbConnection.ConnectorToDB;
import it_academy.$18_01_24Homework.dbConnection.mySQLlocalDB.ConnectorToMysqlDB;
import it_academy.$18_01_24Homework.dto.Person;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

	public static final int MAX_AGE_EXCLUDE = 21;
	public static final int COUNT_OF_PERSONS_TO_CREATE = 6;

	//The table Person in DB People already exist.
	public static void main(String[] args) throws SQLException {

		ConnectorToDB connector = ConnectorToMysqlDB.getInstance();

		List<Person> listToAdd = createPersonsList();
		PersonDao dao = new PersonDaoImpl();

		listToAdd.forEach(person -> {
			try {
				Person aded = dao.save(person);
				if (aded != null) {
					System.out.println("Person " + aded + " added successfully.");
				}
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		});



		//With special method with query constructor (filtering and sorting in DB)
		System.out.println();
		System.out.println("Persons after select and filter in correct order:");
		String query = QueryConstructor.constructSelectQueryWithConditionsAndOrder(new int[]{2},
				new String[]{Queries.GREATER_EXCLUDE},
				new String[]{String.valueOf(MAX_AGE_EXCLUDE)},
				new int[]{7}, new boolean[]{false});

		dao.getAllPersonsWithCustomQuery(query)
				.forEach(System.out::println);





		//With special method (filtering in DB, sorting in java App)
		System.out.println();
		System.out.println("Persons after select and filter in correct order:");
		dao.getAllWithAgeGreaterThen(MAX_AGE_EXCLUDE).stream()
				.sorted(Comparator.comparing(Person::getDateTimeCreate))
				.forEach(System.out::println);





		//With getAll() method (filtering and sorting in java App)
		System.out.println();
		System.out.println("Persons after select and filter in correct order:");
		dao.getAll().stream()
				.filter(person -> person.getAge() > MAX_AGE_EXCLUDE)
				.sorted(Comparator.comparing(Person::getDateTimeCreate))
				.forEach(System.out::println);




		connector.getConnection().close();
	}

	private static List<Person> createPersonsList() {
		return IntStream.range(0, COUNT_OF_PERSONS_TO_CREATE)
				.mapToObj(i -> Person.builder()
						.age(InnPersonsData.AGE[i])
						.salary(InnPersonsData.SALARY[i])
						.passport(InnPersonsData.PASSPORT[i])
						.address(InnPersonsData.ADDRESS[i])
						.dateOfBirth(InnPersonsData.DATE_OF_BIRTH[i])
						.timeToLunch(InnPersonsData.TIME_TO_LUNCH[i])
						.letter(InnPersonsData.LETTER[i])
						.build()).collect(Collectors.toList());
	}
}