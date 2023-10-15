package edu.hw1;

public final class Task1 {

    private Task1() {

    }

    public static final long MINUTES_TO_SECONDS_TRANSFER = 60;

    public static long minutesToSeconds(String input) {
        String[] splitTime = input.split(":"); // splitTime[0] - minutes, splitTime[1] - seconds
        if (splitTime.length != 2) {
            return -1;
        }
        long minutes;
        long seconds;
        try {
            minutes = Long.parseLong(splitTime[0]);
            seconds = Long.parseLong(splitTime[1]);
        } catch (NumberFormatException e) { // выдаст ошибку если в строке есть символы
            return -1;
        }
        if (minutes < 0 || seconds < 0 || seconds >= MINUTES_TO_SECONDS_TRANSFER) {
            return -1; // проверка на отрицательность и seconds >= 60
        }
        return minutes * MINUTES_TO_SECONDS_TRANSFER + seconds;
    }
}
