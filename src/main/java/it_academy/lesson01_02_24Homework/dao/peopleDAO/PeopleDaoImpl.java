package it_academy.lesson01_02_24Homework.dao.peopleDAO;

import it_academy.lesson01_02_24Homework.dao.DaoImpl;
import it_academy.lesson01_02_24Homework.dto.People;

import java.util.List;

public class PeopleDaoImpl extends DaoImpl implements PeopleDAO {

	@Override
	public List<People> getAll() {

		return super.getAll(People.class);
	}

	@Override
	public People get(int id) {

		return (People) super.get(People.class, id);
	}

	@Override
	public void delete(int id) {

		super.delete(People.class, id);
	}

	@Override
	public People save(People people) {

		return (People) super.createRow(people);
	}

	@Override
	public void update(People people) {

		super.updateRow(people);
	}
}
