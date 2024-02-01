package it_academy.lesson25_01_24Homework;

import it_academy.lesson18_01_24Homework.InnPersonsData;
import it_academy.lesson25_01_24Homework.dao.PersonDao;
import it_academy.lesson25_01_24Homework.dao.PersonDaoImpl;
import it_academy.lesson25_01_24Homework.dto.Person;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

	public static final int COUNT_OF_PERSONS = 5;
	public static final String AGE_COLUMN_NAME = "age";
	public static final int AGE_TO_SELECT_FROM_DB = 21;
	public static final String COLUMN_NAME_DATE_TIME_CREATE = "dateTimeCreate";

	public static void main(String[] args) {
		List<Person> createdList = createPersons(COUNT_OF_PERSONS);
		printList(createdList, "List of crated persons:");
		PersonDao dao = new PersonDaoImpl();

		List<Person> savedList =
				createdList.stream()
						.map(dao::save)
						.collect(Collectors.toList());
		printList(savedList, "List of persons saved in database:");

		List<Person> findList = dao.findByColumnValueGreaterThenNumberInOrderByOtherColumn(
				AGE_COLUMN_NAME,
				AGE_TO_SELECT_FROM_DB,
				COLUMN_NAME_DATE_TIME_CREATE);
		String message = "List of persons from database with " + AGE_COLUMN_NAME + " greater then " + AGE_TO_SELECT_FROM_DB + " :";
		printList(findList, message);
	}

	private static List<Person> createPersons(int count) {
		return IntStream.range(0, count)
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


	private static void printList(List<Person> list, String message) {
		System.out.println();
		System.out.println(message);
		list.forEach(System.out::println);
		System.out.println();
	}
}
