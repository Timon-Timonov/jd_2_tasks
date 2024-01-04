package it_academy.$07_12_23Homework;

import java.util.Comparator;

public class ArrayComparator implements Comparator<String[]> {
	@Override
	public int compare(String[] o1, String[] o2) {
		for (int i = 0; i < (Math.min(o1.length, o2.length)); i++) {
			int comp = o1[i].compareTo(o2[i]);
			if (comp != 0) {
				return comp;
			}
		}
		if (o1.length != o2.length) {
			return o1.length - o2.length;
		}
		return 0;
	}
}
