package it_academy.lesson25_01_24Homework.dao;

import it_academy.lesson25_01_24Homework.HibernateUtils.HibernateUtils;
import it_academy.lesson25_01_24Homework.MockConstants;
import it_academy.lesson25_01_24Homework.MockUtil;
import it_academy.lesson25_01_24Homework.dto.Person;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDaoImplTest {

	@Test
	public void testSave() {
		PersonDao dao = new PersonDaoImpl();
		List<Person> list = MockUtil.createPersons(MockConstants.TOTAL_COUNT);
		EntityManager em = HibernateUtils.getEntityManager();

		Person person1 = dao.save(list.get(MockConstants.INDEX_1));
		Person person2 = dao.save(list.get(MockConstants.INDEX_2));
		Person person1_1 = em.find(Person.class, person1.getId());
		Person person2_1 = em.find(Person.class, person2.getId());

		assertNotNull(person1);
		assertNotNull(person2);
		assertNotNull(person1_1);
		assertNotNull(person2_1);
		assertEquals(person1, person1_1);
		assertEquals(person2, person2_1);
		assertNotEquals(person1, person2);
		assertEquals(person1_1, list.get(MockConstants.INDEX_1));
		assertEquals(person2_1, list.get(MockConstants.INDEX_2));
		em.close();
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