package it_academy.lesson25_01_24Homework.HibernateUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtils {
	private static EntityManagerFactory emFactory;

	static {
		emFactory = Persistence.createEntityManagerFactory("person");
	}

	private HibernateUtils() {
	}

	public static EntityManager getEntityManager() {
		return emFactory.createEntityManager();
	}

	public static void closeFactory() {
		emFactory.close();
	}

}
