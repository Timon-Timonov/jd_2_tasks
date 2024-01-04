package it_academy.$12_12_23Homework.scientists.classes;



import it_academy.$12_12_23Homework.scientists.ConstantContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dump implements Runnable {

    private static Dump dump;
    private int night = 1;
    private List<PartsOfRobots> dumpList = new ArrayList<>();
    Random random = new Random();

    // private int allCount = 0;// temp!

    private Dump() {
    }

    public static Dump getDump() {
        if (dump == null) {
            dump = new Dump();
        }
        return dump;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " start.");

        // allCount += ConstantContainer.START_COUNT_OF_DUMP_PARTS;// temp!

        synchronized (Integer.class) {
            startFullingOfDump();
            printCurrentState();
        }

        waitNextNight();

        for (int i = 1; i < ConstantContainer.COUNT_OF_NIGHTS; i++) {
            doEveryNightWork();
            waitNextNight();
        }

        System.out.println(Thread.currentThread().getName() + " end state " + dumpList.toString());

        // System.out.println(Thread.currentThread().getName() + " finished." + (allCount + countInDumpList()));// temp!
        System.out.println(Thread.currentThread().getName() + " finished.");
    }

    private void startFullingOfDump() {
        for (int i = 0; i < ConstantContainer.START_COUNT_OF_DUMP_PARTS; i++) {
            putPartToDump(getRandomPartsOfRobots());
        }
    }

    private PartsOfRobots getRandomPartsOfRobots() {
        int randomIndex = random.nextInt(ConstantContainer.PARTS_OF_ROBOTS.length);
        return new PartsOfRobots(ConstantContainer.PARTS_OF_ROBOTS[randomIndex]);
    }

    private void putPartToDump(PartsOfRobots randomPart) {
        int listInd = dumpList.indexOf(randomPart);
        if (listInd != (-1)) {
            dumpList.get(listInd).incrPartCount(1);
        } else {
            dumpList.add(randomPart);
        }
    }

    private void printCurrentState() {
        //System.out.println(Thread.currentThread().getName() + " night_" + night + " " + dumpList.toString() + countInDumpList());//temp!
        System.out.println(Thread.currentThread().getName() + " night_" + night + " " + dumpList.toString());
    }

    /*private int countInDumpList() {
        int count = 0;
        for (PartsOfRobots part : dumpList) {
            count += part.getCount();
        }
        return count;
    }//temp!*/


    private void waitNextNight() {
        try {
            Thread.sleep(ConstantContainer.NIGHT_LONG);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doEveryNightWork() {
        synchronized (Integer.class) {
            night++;
            int toNightCountOfParts = random.nextInt(
                    ConstantContainer.MAX_FACTORY_PARTS_PER_NIGHT + 1
                            - ConstantContainer.MIN_FACTORY_PARTS_PER_NIGHT)
                    + ConstantContainer.MIN_FACTORY_PARTS_PER_NIGHT;

            System.out.println(Thread.currentThread().getName() + " night_" + night + " produced " + toNightCountOfParts + " parts.");

            for (int j = 0; j < toNightCountOfParts; j++) {
                putPartToDump(getRandomPartsOfRobots());
            }
            printCurrentState();

            // allCount += toNightCountOfParts;// temp!
        }
    }


    public PartsOfRobots getAnyPartFromDump() {
        synchronized (Integer.class) {
            List<PartsOfRobots> availibleParts = new ArrayList<>();
            for (PartsOfRobots p : dumpList) {
                if (p.getCount() > 0) {
                    availibleParts.add(p);
                }
            }
            if (availibleParts.isEmpty()) {
                return null;
            }
            int rand = random.nextInt(availibleParts.size());
            availibleParts.get(rand).decrPartCount(1);//! the same objects in different lists(dumplist)
            String name = availibleParts.get(rand).getName();
            return new PartsOfRobots(name);//return NEW object with same name
        }
    }
}
