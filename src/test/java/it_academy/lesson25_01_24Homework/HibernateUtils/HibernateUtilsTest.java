package it_academy.lesson25_01_24Homework.HibernateUtils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class HibernateUtilsTest {

	@Test
	void testGetEntityManager() {
		assertNotNull(HibernateUtils.getEntityManager());
	}
}