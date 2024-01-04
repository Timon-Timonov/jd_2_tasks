package it_academy.$12_12_23Homework.scientists;


import it_academy.$12_12_23Homework.scientists.classes.Dump;
import it_academy.$12_12_23Homework.scientists.classes.InvalidCountOfDifferentParts;
import it_academy.$12_12_23Homework.scientists.classes.PartsOfRobots;
import it_academy.$12_12_23Homework.scientists.classes.Scientist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Runner {
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + " start.");

		Dump dump = Dump.getDump();

		List<List<PartsOfRobots>> listOfLists = new ArrayList<>();
		List<Thread> threadsList = new ArrayList<>();

		Thread threadDump = new Thread(dump);
		threadDump.setName(ConstantContainer.DUMP);
		threadsList.add(threadDump);

		for (int i = 0; i < ConstantContainer.COUNT_OF_SCIENTISTS; i++) {
			List<PartsOfRobots> partsOfRobotsList = new ArrayList<>();
			listOfLists.add(partsOfRobotsList);
			String name = ConstantContainer.Scientist + (i + 1);

			Thread thread = new Thread(new Scientist(dump, partsOfRobotsList));
			thread.setName(name);
			threadsList.add(thread);
		}//create Threads

		for (Thread thr : threadsList) {
			thr.start();
		}//start Threads

		for (Thread thr : threadsList) {
			try {
				thr.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//join all threads

		chooseWinner(makeRobots(listOfLists));

		System.out.println(Thread.currentThread().getName() + " finished.");
	}

	private static int[] makeRobots(List<List<PartsOfRobots>> lists) throws InvalidCountOfDifferentParts {
		int[] countOfRobots = new int[lists.size()];
		Arrays.fill(countOfRobots, 0);


		for (int i = 0; i < lists.size(); i++) {
			List<PartsOfRobots> roboParts = lists.get(i);

			int robotCount = 0;
			if (roboParts.size() == ConstantContainer.PARTS_OF_ROBOTS.length) {

				robotCount = roboParts.get(0).getCount();
				for (PartsOfRobots part : roboParts) {
					int count = part.getCount();
					robotCount = Math.min(robotCount, count);
				}//choosing of min count

				for (PartsOfRobots part : roboParts) {
					part.decrPartCount(robotCount);
				}//take parts from the list

			} else if (roboParts.size() > ConstantContainer.PARTS_OF_ROBOTS.length) {
				throw new InvalidCountOfDifferentParts();
			}
			countOfRobots[i] = robotCount;
			System.out.println((i + 1) + "st scientist build " + robotCount + " robot(s).");
			System.out.println("His rest of roboParts: " + roboParts.toString());

		}
		return countOfRobots;
	}

	private static void chooseWinner(int[] countOfRObots) {

		int maxCountOfRobots = 0;
		for (int count : countOfRObots) {
			maxCountOfRobots = Math.max(maxCountOfRobots, count);
		}//choosing of max count

		if (maxCountOfRobots > 0) {
			int countOfWinners = 0;
			int lastWinnerIndex = 0;
			for (int i = 0; i < countOfRObots.length; i++) {
				if (countOfRObots[i] == maxCountOfRobots) {
					countOfWinners++;
					lastWinnerIndex = i;
				}
			}

			if (countOfWinners == 1) {
				System.out.println(((lastWinnerIndex + 1) + "st scientist is winner. He build "
						+ maxCountOfRobots + " robots."));
			} else {
				System.out.print("We have " + countOfWinners + " winners. They built "
						+ maxCountOfRobots + " robots each. Their numbers: ");

				boolean isFirst = true;
				for (int i = 0; i < countOfRObots.length; i++) {
					if (countOfRObots[i] == maxCountOfRobots) {
						if (isFirst) {
							System.out.print((i + 1));
							isFirst = false;
						} else {
							System.out.print(", " + (i + 1));
						}
					}
				}
				System.out.println(". ");
			}
		} else {
			System.out.println("Nobody has build any robots.");
		}
	}
}
