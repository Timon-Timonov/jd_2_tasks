package it_academy.$07_12_23Homework;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class App {
	public static void main(String[] args) {

		List<String[]> inList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(ConstantContainer.PATH_TO_IN_FILE))) {

			String line = br.readLine();
			while (line != null) {
				inList.add(line.split(ConstantContainer.REGEX_DILIMETR_BETWEEN_VALUES_IN_LINE_FOR_IN_FILE));
				line = br.readLine();
			}

		} catch (IOException e) {
			System.out.println(ConstantContainer.IN_FILE_EXCEPTION_MESSAGE);
		}

		inList.sort(new ArrayComparator());

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ConstantContainer.PATH_TO_OUT_FILE))) {

			bw.write(confirmTextToFile(inList));

		} catch (IOException e) {
			System.out.println(ConstantContainer.OUT_FILE_EXCEPTION_MESSAGE);
		}

		//printAllValues(inList);
	}

	private static String confirmTextToFile(List<String[]> outList) {
		StringBuilder text = new StringBuilder();
		for (String[] arr : outList) {

			boolean isFirst = true;
			for (String val : arr) {

				if (isFirst) {
					text.append(val);
					isFirst = false;
				} else {
					text.append(ConstantContainer.DILIMETR_BETWEEN_VALUES_FOR_OUT_FILE).append(val);
				}
			}
			text.append(ConstantContainer.DILIMETR_BETWEEN_LINES_FOR_OUT_FILE);
		}
		return text.toString();
	}

	private static void printAllValues(List<String[]> inList) {
		for (String[] str : inList) {
			for (String val : str) {
				System.out.print(val + ConstantContainer.DILIMETR_BETWEEN_VALUES_FOR_OUT_FILE);
			}
			System.out.print(ConstantContainer.DILIMETR_BETWEEN_LINES_FOR_OUT_FILE);
			System.out.println(str.length);
		}
	}
}
