package it_academy.$18_01_24Homework;

import it_academy.$18_01_24Homework.dao.PersonDao;
import it_academy.$18_01_24Homework.dao.personDaoImpl.PersonDaoImpl;
import it_academy.$18_01_24Homework.dto.Person;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

	public static final int MAX_AGE_EXCLUDE = 21;
	public static final int COUNT_OF_PERSONS_TO_CREATE = 6;

	//The table Person in DB People already exist.
	public static void main(String[] args) throws SQLException {

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

		System.out.println();
		System.out.println("Persons after filter in correct order:");
		dao.getAll().stream()
				.filter(person -> person.getAge() > MAX_AGE_EXCLUDE)
				.sorted(Comparator.comparing(Person::getDateTimeCreate))
				.forEach(System.out::println);
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