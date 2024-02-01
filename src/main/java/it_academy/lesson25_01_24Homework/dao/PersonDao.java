package it_academy.lesson25_01_24Homework.dao;

import it_academy.lesson25_01_24Homework.dto.Person;

import java.util.List;

public interface PersonDao {
	Person save(Person person);

	List<Person> findByColumnValueGreaterThenNumberInOrderByOtherColumn(String columnNameFind, Number paramExcl, String columnNameOrderAsc);
}
