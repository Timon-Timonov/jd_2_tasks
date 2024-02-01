package it_academy.lesson12_12_23Homework.scientists;

public interface ConstantContainer {
    String[] PARTS_OF_ROBOTS = {"Head", "Body", "CPU", "HDD", "RAM", "LeftLeg", "RightLeg", "LeftHand", "RightHand"};
    int COUNT_OF_SCIENTISTS = 2;
    int COUNT_OF_NIGHTS = 100;
    long NIGHT_LONG = 100;
    int MAX_FACTORY_PARTS_PER_NIGHT = 4;
    int MIN_FACTORY_PARTS_PER_NIGHT = 1;
    int START_COUNT_OF_DUMP_PARTS = 20;
    int MAX_SERVANT_PARTS_PER_NIGHT = 4;
    int MIN_SERVANT_PARTS_PER_NIGHT = 1;
    String DUMP = "Dump_______";
    String Scientist = "Scientist_";
    Object SUNCHR =new Object();// Object for synchronized blocks of code
}
