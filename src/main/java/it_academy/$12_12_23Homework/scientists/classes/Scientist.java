package it_academy.$12_12_23Homework.scientists.classes;



import it_academy.$12_12_23Homework.scientists.ConstantContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Scientist implements Runnable {

    private Dump dump;
    private List<PartsOfRobots> scientistsList;
    private int night = 1;
    private Random random = new Random();

    // private int allCount = 0;// temp!


    public Scientist(Dump dump, List<PartsOfRobots> scientistsList) {
        this.dump = dump;
        this.scientistsList = scientistsList;
    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " start.");

        for (int i = 0; i < ConstantContainer.COUNT_OF_NIGHTS; i++) {
            doEveryNightWork();
            waitNextNight();
        }

        System.out.println(Thread.currentThread().getName() + " has: " + scientistsList.toString());

        // System.out.println(Thread.currentThread().getName() + " finished." + allCount);// temp!
        System.out.println(Thread.currentThread().getName() + " finished.");
    }


    private void doEveryNightWork() {
        List<PartsOfRobots> todayList = getPartsToday();

        printServantState(todayList);

        night++;
        putPartsToScientistList(todayList);
    }


    private List<PartsOfRobots> getPartsToday() {

        int wantToTakeToday = random.nextInt(ConstantContainer.MAX_SERVANT_PARTS_PER_NIGHT + 1
                - ConstantContainer.MIN_SERVANT_PARTS_PER_NIGHT) + ConstantContainer.MIN_SERVANT_PARTS_PER_NIGHT;

        List<PartsOfRobots> todayList = new ArrayList<>();

        for (int j = 0; j < wantToTakeToday; j++) {
            PartsOfRobots anyPart = dump.getAnyPartFromDump();
            if (anyPart != null) {
                todayList.add(anyPart);
            }
        }

        // allCount += todayList.size();// temp!

        return todayList;
    }


    private void printServantState(List<PartsOfRobots> todayList) {
        System.out.println(Thread.currentThread().getName() + " night_" + night + " servant take: " + todayList.toString());
    }


    private void putPartsToScientistList(List<PartsOfRobots> todayList) {
        for (PartsOfRobots p : todayList) {
            int ind = scientistsList.indexOf(p);
            if (ind != (-1)) {
                scientistsList.get(ind).incrPartCount(1);
            } else {
                scientistsList.add(p);
            }
        }
    }


    private void waitNextNight() {
        try {
            Thread.sleep(ConstantContainer.NIGHT_LONG);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
