package edu.hw1;

public final class Task2 {
    private Task2() {

    }

    private static final int DIV_TO_REMOVE_NUMBER = 10;

    public static int countDigits(long number) {
        long num = number;
        int counter = 1;
        while (num >= DIV_TO_REMOVE_NUMBER || num <= -DIV_TO_REMOVE_NUMBER) {
            counter++;
            num /= DIV_TO_REMOVE_NUMBER;
        }
        return counter;
    }
}
