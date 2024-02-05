package it_academy.lesson07_12_23Homework;

import java.util.Comparator;

public class ArrayComparator implements Comparator<String[]> {
	@Override
	public int compare(String[] o1, String[] o2) {
		for (int i = 0; i < (Math.min(o1.length, o2.length)); i++) {

			int comp = o1[i].compareTo(o2[i]);
			if (comp != 0) {

				Double d1 = getaDouble(o1[i]);
				Double d2 = getaDouble(o2[i]);

				if (d1 != null && d2 != null) {
					return d1.compareTo(d2);
				} else if (d1 == null && d2 == null) {
					return comp;
				} else {
					return d1 != null ? -1 : 1;
				}
			}
		}


		if (o1.length != o2.length) {
			return o1.length - o2.length;
		}
		return 0;
	}


	private Double getaDouble(String s) {
		double d;
		try {
			d = Double.parseDouble(s.trim());
		} catch (NumberFormatException e) {
			return null;
		}
		return d;
	}

}
