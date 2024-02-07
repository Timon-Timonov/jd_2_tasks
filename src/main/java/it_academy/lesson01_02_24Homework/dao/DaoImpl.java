package it_academy.lesson01_02_24Homework.dao;

import it_academy.lesson01_02_24Homework.Utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class DaoImpl<T> {

	public static final String THERE_IS_NO_ROW_WITH_SUCH_ID = "There is no row with such ID in table %s.";

	public T get(Class<?> clazz, int id) {

		T t = null;
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			t = (T) em.find(clazz, id);
			em.getTransaction().commit();
		} catch (EntityNotFoundException e) {
			System.out.println(
					String.format(THERE_IS_NO_ROW_WITH_SUCH_ID,
							(clazz.isAnnotationPresent(Table.class) ?
									clazz.getAnnotation(Table.class).name()
									: clazz.getName())));
		} finally {
			em.close();
		}
		return t;
	}


	public void delete(Class<?> clazz, int id) {

		EntityManager em = HibernateUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Object rootEntity = em.getReference(clazz, id);
			em.remove(rootEntity);
			em.getTransaction().commit();
		} catch (EntityNotFoundException e) {
			System.out.println(
					String.format(THERE_IS_NO_ROW_WITH_SUCH_ID,
							(clazz.isAnnotationPresent(Table.class) ?
									clazz.getAnnotation(Table.class).name()
									: clazz.getName())));
		} finally {
			em.close();
		}
	}


	public T createRow(T t) {

		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
		return t;
	}


	public void updateRow(T t) {

		EntityManager em = HibernateUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(t);
			em.getTransaction().commit();
		} catch (EntityNotFoundException e) {
			System.out.println(
					String.format(THERE_IS_NO_ROW_WITH_SUCH_ID,
							(t.getClass().isAnnotationPresent(Table.class) ?
									t.getClass().getAnnotation(Table.class).name()
									: t.getClass().getName())));
		} finally {
			em.close();
		}
	}

	public List<?> getAll(Class<?> clazz) {

		List<T> list;
		EntityManager em = HibernateUtil.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = (CriteriaQuery<T>) cb.createQuery(clazz);
		Root<T> rootEntry = (Root<T>) cq.from(clazz);
		CriteriaQuery<T> all = cq.select(rootEntry);
		TypedQuery<T> allQuery = em.createQuery(all);
		list = allQuery.getResultList();
		em.close();
		return list;
	}
}
