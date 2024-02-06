package it_academy.lesson01_02_24Homework.dao.peopleDAO;

import it_academy.lesson01_02_24Homework.dao.DAO;
import it_academy.lesson01_02_24Homework.dto.People;

import java.util.List;

public interface PeopleDAO extends DAO<People> {

	List<People> getAll();
}
