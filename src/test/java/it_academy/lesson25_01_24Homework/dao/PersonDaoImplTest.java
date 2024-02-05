package it_academy.lesson25_01_24Homework.dao;

import it_academy.lesson25_01_24Homework.HibernateUtils.HibernateUtils;
import it_academy.lesson25_01_24Homework.MockConstants;
import it_academy.lesson25_01_24Homework.MockUtil;
import it_academy.lesson25_01_24Homework.dto.Person;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDaoImplTest {

	@Test
	public void testSave() {
		PersonDao dao = new PersonDaoImpl();
		List<Person> createdList = MockUtil.createPersons(MockConstants.TOTAL_COUNT);
		List<Person> savedList = new ArrayList<>();
		List<Person> findList = new ArrayList<>();
		EntityManager em = HibernateUtils.getEntityManager();

		createdList.stream()
				.map(dao::save)
				.peek(savedList::add)
				.map(person -> em.find(Person.class, person.getId()))
				.forEach(findList::add);
		em.close();

		IntStream.range(0,MockConstants.MAX_INDEX)
				.forEach(i->{
					Person created=createdList.get(i);
					Person saved=savedList.get(i);
					Person fined=findList.get(i);
					assertNotNull(saved);
					assertNotNull(fined);
					assertEquals(saved, fined);
					assertEquals(fined, created);
				});
	}

	@Test
	public void testFind() {
		PersonDao dao = new PersonDaoImpl();
		List<Person> list = MockUtil.createPersons(MockConstants.TOTAL_COUNT);
		EntityManager em = HibernateUtils.getEntityManager();
		em.getTransaction().begin();
		list.forEach(em::persist);
		em.getTransaction().commit();
		em.close();

		List<Person> persons =
				dao.findByColumnValueGreaterThenNumberInOrderByOtherColumn(
						MockConstants.AGE_COLUMN_NAME,
						MockConstants.AGE[MockConstants.MAX_INDEX],
						MockConstants.DATE_TIME_CREATE_COLUMN_NAME);

		assertNotNull(persons);
		assertEquals(MockConstants.SIZE_1, persons.size());

		assertNotNull(persons.get(MockConstants.INDEX_0));
		assertEquals(MockConstants.AGE[MockConstants.INDEX_1], persons.get(MockConstants.INDEX_0).getAge());

		List<Person> persons1 =
				dao.findByColumnValueGreaterThenNumberInOrderByOtherColumn(
						MockConstants.SALARY_COLUMN_NAME,
						MockConstants.SALARY[MockConstants.INDEX_4],
						MockConstants.AGE_COLUMN_NAME);

		assertNotNull(persons1);
		assertEquals(MockConstants.SIZE_1, persons1.size());

		assertNotNull(persons1.get(MockConstants.INDEX_0));
		assertEquals(MockConstants.SALARY[MockConstants.MAX_INDEX], persons1.get(MockConstants.INDEX_0).getSalary());

		List<Person> persons2 =
				dao.findByColumnValueGreaterThenNumberInOrderByOtherColumn(
						MockConstants.AGE_COLUMN_NAME,
						MockConstants.AGE[MockConstants.INDEX_0],
						MockConstants.SALARY_COLUMN_NAME);

		assertNotNull(persons2);
		assertEquals(MockConstants.TOTAL_COUNT - 1, persons2.size());
		assertFalse(persons2.contains(null));
		IntStream.range(0, persons2.size())
				.forEach(i -> assertEquals(MockConstants.SALARY[i + 1], persons2.get(i).getSalary()));
		assertEquals(MockConstants.AGE[MockConstants.INDEX_1], persons2.get(MockConstants.INDEX_0).getAge());
	}
}