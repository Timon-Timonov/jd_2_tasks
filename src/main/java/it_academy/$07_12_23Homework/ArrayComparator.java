package it_academy.$07_12_23Homework;

import java.util.Comparator;

public class ArrayComparator implements Comparator<String[]> {
	@Override
	public int compare(String[] o1, String[] o2) {
		for (int i = 0; i < (Math.min(o1.length, o2.length)); i++) {

			int comp = o1[i].compareTo(o2[i]);
			if (comp != 0) {
				Double d1 = null;
				Double d2 = null;

				try {
					d1 = Double.parseDouble(o1[i].trim());
				} catch (NumberFormatException e) {
				}

				try {
					d2 = Double.parseDouble(o2[i].trim());
				} catch (NumberFormatException e) {
				}

				if (d1 != null) {
					if (d2 != null) {
						return (int) ((d1 - d2) * ConstantContainer.CALCULATION_ACCURACY);
					} else {
						return -1;
					}

				} else {
					if (d2 != null) {
						return 1;
					} else {
						return comp;
					}
				}
			}
		}
		if (o1.length != o2.length) {
			return o1.length - o2.length;
		}
		return 0;
	}
}
