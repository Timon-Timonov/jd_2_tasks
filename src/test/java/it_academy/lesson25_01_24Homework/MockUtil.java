package it_academy.lesson25_01_24Homework;

import it_academy.lesson25_01_24Homework.dto.Person;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MockUtil {

	private MockUtil() {
	}

	public static List<Person> createPersons(int count) {
		return IntStream.range(0, count)
				.mapToObj(i -> Person.builder()
						.age(MockConstants.AGE[i])
						.salary(MockConstants.SALARY[i])
						.passport(MockConstants.PASSPORT[i])
						.address(MockConstants.ADDRESS[i])
						.dateOfBirth(MockConstants.DATE_OF_BIRTH[i])
						.timeToLunch(MockConstants.TIME_TO_LUNCH[i])
						.letter(MockConstants.LETTER[i])
						.build()).collect(Collectors.toList());
	}
}
