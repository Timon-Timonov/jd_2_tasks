package it_academy.$07_12_23Homework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayComparatorTest {

	@Test
	void compare() {

		String[] s1 = {"-11"};
		String[] s2 = {"-9"};
		String[] s3 = {"-0.112"};
		String[] s4 = {"-0.111"};

		String[] s5 = {"0"};

		String[] s6 = {"0.111"};
		String[] s7 = {"0.112"};
		String[] s8 = {"9.111"};
		String[] s9 = {"11.11"};
		String[] s10 = {"12"};
		String[] s11 = {"13"};

		String[] s12 = {""};
		String[] s13 = {" "};
		String[] s13_1 = {"."};

		String[] s14 = {"A"};
		String[] s15 = {"Z"};
		String[] s16 = {"a"};
		String[] s17 = {"z"};

		String[] s18 = {"z", "1"};
		String[] s19 = {"z", "1", ""};
		String[] s20 = {"z", "1", ""};

		ArrayComparator cmp = new ArrayComparator();

		assertTrue(cmp.compare(s2,s1)>0);
		assertTrue(cmp.compare(s3,s2)>0);
		assertTrue(cmp.compare(s4,s3)>0);
		assertTrue(cmp.compare(s5,s4)>0);
		assertTrue(cmp.compare(s6,s5)>0);
		assertTrue(cmp.compare(s7,s6)>0);
		assertTrue(cmp.compare(s8,s7)>0);
		assertTrue(cmp.compare(s9,s8)>0);
		assertTrue(cmp.compare(s10,s9)>0);
		assertTrue(cmp.compare(s11,s10)>0);
		assertTrue(cmp.compare(s12,s11)>0);
		assertTrue(cmp.compare(s13,s12)>0);

		assertTrue(cmp.compare(s13_1,s13)>0);
		assertTrue(cmp.compare(s14,s13_1)>0);

		assertTrue(cmp.compare(s15,s14)>0);
		assertTrue(cmp.compare(s16,s15)>0);
		assertTrue(cmp.compare(s17,s16)>0);
		assertTrue(cmp.compare(s18,s17)>0);
		assertTrue(cmp.compare(s19,s18)>0);

		assertTrue(cmp.compare(s20,s1)>0);

		assertEquals(0, cmp.compare(s20, s19));
		assertEquals(0, cmp.compare(s20, s20));


	}
}