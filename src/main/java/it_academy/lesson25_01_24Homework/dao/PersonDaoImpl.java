package it_academy.lesson25_01_24Homework.dao;

import it_academy.lesson25_01_24Homework.HibernateUtils.HibernateUtils;
import it_academy.lesson25_01_24Homework.dto.Person;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.List;

public class PersonDaoImpl implements PersonDao {

	@Override
	public Person save(Person person) {
		Person person1;
		EntityManager em = HibernateUtils.getEntityManager();
		em.getTransaction().begin();
		em.persist(person);
		em.getTransaction().commit();
		person1 = em.find(Person.class, person.getId());
		em.close();
		return person1;
	}

	@Override
	public List<Person> findByColumnValueGreaterThenNumberInOrderByOtherColumn(String columnNameFind, Number greaterThenExclude, String columnNameOrderAsc) {
		if (columnNameFind == null || greaterThenExclude == null || columnNameOrderAsc == null) {
			return null;
		}
		List<Person> personList;
		EntityManager em = HibernateUtils.getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
		Root<Person> root = criteriaQuery.from(Person.class);
		Order order = criteriaBuilder.asc(root.get(columnNameOrderAsc));
		try {
			criteriaQuery
					.select(root)
					.where(criteriaBuilder.gt(root.get(columnNameFind), greaterThenExclude))
					.orderBy(order);
			Query query = em.createQuery(criteriaQuery);
			personList = query.getResultList();
		} catch (IllegalArgumentException e) {
			em.close();
			return null;
		}
		em.close();
		return personList;
	}
}
