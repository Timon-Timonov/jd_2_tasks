package it_academy.lesson01_02_24Homework.dao;

import it_academy.lesson01_02_24Homework.dao.peopleDAO.PeopleDaoImpl;
import it_academy.lesson01_02_24Homework.dto.People;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DaoImplTest {

	@Test
	public void testGet() {
		PeopleDaoImpl dao = new PeopleDaoImpl();
		People pep = People.builder()
				.age(25)
				.name("UniqueName1")
				.surname("UniqueSurName2")
				.build();
		dao.createRow(pep);
		assertEquals(pep, dao.get(pep.getId()));
	}

	@Test
	void testDelete() {
		PeopleDaoImpl dao = new PeopleDaoImpl();
		People pep = People.builder()
				.age(35)
				.name("UniqueName3")
				.surname("UniqueSurName3")
				.build();
		dao.createRow(pep);
		List<People> list = dao.getAll();
		int count = list.size();
		dao.delete(pep.getId());
		List<People> list2 = dao.getAll();

		assertEquals(count - 1, list2.size());
		IntStream.range(0, Math.min(list.size(), list2.size()))
				.forEach(i -> {
					assertEquals(list.get(i), list2.get(i));
				});
	}

	@Test
	void testCreateRow() {
		PeopleDaoImpl dao = new PeopleDaoImpl();
		People pep = People.builder()
				.age(25)
				.name("UniqueName")
				.surname("UniqueSurName")
				.build();
		int count = dao.getAll().size();
		People pep1 = (People) dao.createRow(pep);
		assertEquals(count + 1, dao.getAll().size());
		assertEquals(pep, pep1);
	}

	@Test
	void testUpdateRow() {
		PeopleDaoImpl dao = new PeopleDaoImpl();
		People pep = People.builder()
				.age(18)
				.name("UniqueName18")
				.surname("UniqueSurName18")
				.build();
		dao.createRow(pep);
		int count = dao.getAll().size();
		pep.setAge(20);
		pep.setName("NewName");
		pep.setSurname("NewSurName");
		dao.updateRow(pep);
		assertEquals(count, dao.getAll().size());
		assertEquals(pep.getAge(), dao.get(pep.getId()).getAge());
	}
}