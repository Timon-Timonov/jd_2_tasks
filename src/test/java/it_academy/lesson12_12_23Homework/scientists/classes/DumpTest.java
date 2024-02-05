package it_academy.lesson12_12_23Homework.scientists.classes;

import it_academy.lesson12_12_23Homework.scientists.ConstantContainer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class DumpTest {

	public static final int SLEEP_TIME = 500;
	public static final int COUNT_OF_CHECK = 100;
	private Dump dump = Dump.getDump();


	@Test
	public void testGetDump() {

		assertNotNull(dump);

		for (int i = 0; i < COUNT_OF_CHECK; i++) {
			assertEquals(dump, Dump.getDump());
		}
	}

	@Test
	public void testgetAnyPartFromDump() throws InterruptedException {
		PartsOfRobots part = dump.getAnyPartFromDump();

		assertNull(part);

		Thread thread = new Thread(dump);
		thread.start();
		Thread.sleep(SLEEP_TIME);
		part = dump.getAnyPartFromDump();

		assertNotNull(part);

		PartsOfRobots roboPart = part;

		assertEquals(1, roboPart.getCount());

		int to = (int) (ConstantContainer.START_COUNT_OF_DUMP_PARTS
				+ ConstantContainer.MIN_FACTORY_PARTS_PER_NIGHT
				* SLEEP_TIME / ConstantContainer.NIGHT_LONG);
		for (int i = 0; i < to; i++) {

			assertTrue(Arrays.asList(ConstantContainer.PARTS_OF_ROBOTS)
					.contains(dump.getAnyPartFromDump().getName()));
		}

		thread.stop();
	}
}