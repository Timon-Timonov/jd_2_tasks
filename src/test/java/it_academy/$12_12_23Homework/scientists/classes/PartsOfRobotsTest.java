package it_academy.$12_12_23Homework.scientists.classes;

import it_academy.$12_12_23Homework.scientists.ConstantContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartsOfRobotsTest {

	public static final int INCR_VALUE = 10;
	public static final int DEFAULT_VALUE = 1;
	public static final int DECR_VALUE = 1;
	public static final int NAME_INDEX = 0;
	public static final int DECREMENT_VALUE_EXCEEDED = 2;

	PartsOfRobots partsOfRobots1 = new PartsOfRobots(ConstantContainer.PARTS_OF_ROBOTS[NAME_INDEX]);
	PartsOfRobots partsOfRobots2 = new PartsOfRobots(ConstantContainer.PARTS_OF_ROBOTS[NAME_INDEX]);


	@Test
	void getCount() {
		assertEquals(DEFAULT_VALUE, partsOfRobots1.getCount());
	}

	@Test
	void incrPartCount() {
		partsOfRobots1.incrPartCount(INCR_VALUE);
		assertEquals(DEFAULT_VALUE + INCR_VALUE, partsOfRobots1.getCount());

	}

	@Test
	void decrPartCount() {
		partsOfRobots2.decrPartCount(DECR_VALUE);
		assertEquals((DEFAULT_VALUE - DECR_VALUE), partsOfRobots2.getCount());
		try {
			int crushDecr = partsOfRobots2.getCount() * DECREMENT_VALUE_EXCEEDED;
			partsOfRobots1.decrPartCount(crushDecr);
		} catch (Throwable e) {
			assertTrue(e instanceof InvalidCountOfParts);
		}
	}

	@Test
	void getName() {
		assertEquals(ConstantContainer.PARTS_OF_ROBOTS[NAME_INDEX], partsOfRobots1.getName());
	}

	@Test
	void testEquals() {
		partsOfRobots2.incrPartCount(INCR_VALUE);
		assertNotEquals(partsOfRobots1.getCount(), partsOfRobots2.getCount());
		assertEquals(partsOfRobots1.getName(), partsOfRobots2.getName());
		assertEquals(partsOfRobots1, partsOfRobots2);
	}

	@Test
	void testHashCode() {
		partsOfRobots2.incrPartCount(INCR_VALUE);
		assertTrue(partsOfRobots1.getCount() != partsOfRobots2.getCount());
		assertEquals(partsOfRobots1.getName(), partsOfRobots2.getName());
		assertEquals(partsOfRobots1.hashCode(), partsOfRobots2.hashCode());
	}

	@Test
	void testToString() {
		assertEquals(partsOfRobots1.getName() + " - " + partsOfRobots1.getCount(), partsOfRobots1.toString());
	}
}