package it_academy.lesson12_12_23Homework.scientists.classes;

import it_academy.lesson12_12_23Homework.scientists.ConstantContainer;
import it_academy.lesson12_12_23Homework.scientists.exceptions.InvalidCountOfParts;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PartsOfRobotsTest {

	public static final int INCR_VALUE = 10;
	public static final int DEFAULT_VALUE = 1;
	public static final int DECR_VALUE = 1;
	public static final int NAME_INDEX = 0;
	public static final int DECREMENT_VALUE_EXCEEDED = 2;
	private PartsOfRobots partsOfRobots1;
	private PartsOfRobots partsOfRobots2;


	@Test
	public void testGetCount() {
		partsOfRobots1 = new PartsOfRobots(ConstantContainer.PARTS_OF_ROBOTS[NAME_INDEX]);
		assertEquals(DEFAULT_VALUE, partsOfRobots1.getCount());
	}

	@Test
	public void testIncrPartCount() {
		partsOfRobots1 = new PartsOfRobots(ConstantContainer.PARTS_OF_ROBOTS[NAME_INDEX]);
		partsOfRobots1.incrPartCount(INCR_VALUE);
		assertEquals(DEFAULT_VALUE + INCR_VALUE, partsOfRobots1.getCount());
	}

	@Test
	public void testDecrPartCount() {
		partsOfRobots1 = new PartsOfRobots(ConstantContainer.PARTS_OF_ROBOTS[NAME_INDEX]);
		partsOfRobots2 = new PartsOfRobots(ConstantContainer.PARTS_OF_ROBOTS[NAME_INDEX]);
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
	public void testGetName() {
		partsOfRobots1 = new PartsOfRobots(ConstantContainer.PARTS_OF_ROBOTS[NAME_INDEX]);
		assertEquals(ConstantContainer.PARTS_OF_ROBOTS[NAME_INDEX], partsOfRobots1.getName());
	}

	@Test
	public void testEquals() {
		partsOfRobots1 = new PartsOfRobots(ConstantContainer.PARTS_OF_ROBOTS[NAME_INDEX]);
		partsOfRobots2 = new PartsOfRobots(ConstantContainer.PARTS_OF_ROBOTS[NAME_INDEX]);
		partsOfRobots2.incrPartCount(INCR_VALUE);
		assertNotEquals(partsOfRobots1.getCount(), partsOfRobots2.getCount());
		assertEquals(partsOfRobots1.getName(), partsOfRobots2.getName());
		assertEquals(partsOfRobots1, partsOfRobots2);
	}

	@Test
	public void testHashCode() {
		partsOfRobots1 = new PartsOfRobots(ConstantContainer.PARTS_OF_ROBOTS[NAME_INDEX]);
		partsOfRobots2 = new PartsOfRobots(ConstantContainer.PARTS_OF_ROBOTS[NAME_INDEX]);
		partsOfRobots2.incrPartCount(INCR_VALUE);
		assertTrue(partsOfRobots1.getCount() != partsOfRobots2.getCount());
		assertEquals(partsOfRobots1.getName(), partsOfRobots2.getName());
		assertEquals(partsOfRobots1.hashCode(), partsOfRobots2.hashCode());
	}

	@Test
	public void testToString() {
		partsOfRobots1 = new PartsOfRobots(ConstantContainer.PARTS_OF_ROBOTS[NAME_INDEX]);
		assertEquals(partsOfRobots1.getName() + " - " + partsOfRobots1.getCount(), partsOfRobots1.toString());
	}
}