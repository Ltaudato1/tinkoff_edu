package edu.hw1;

public final class task2 {
    public static int countDigits(long number){
        long num = number;
        final int DIV_TO_REMOVE_NUMBER = 10; // При делении нацело на 10 убирается последняя цифра
        int counter = 1;
        while (num >= DIV_TO_REMOVE_NUMBER || num <= -DIV_TO_REMOVE_NUMBER){
            counter++;
            num /= DIV_TO_REMOVE_NUMBER;
        }
        return counter;
    }
}
