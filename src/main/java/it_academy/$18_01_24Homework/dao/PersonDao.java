package it_academy.$18_01_24Homework.dao;

import it_academy.$18_01_24Homework.dto.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonDao extends DAO<Person> {

	List<Person> getAll() throws SQLException;

	List<Person> getAllWithAgeGreaterThen(int age) throws SQLException;

	List<Person> getAllPersonsWithCustomQuery(String query) throws SQLException;
}
