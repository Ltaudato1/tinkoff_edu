package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Task1 {
    private Task1() {

    }

    private static final int MINUTES_TO_HOURS_TRANSFER = 60;
    private static final int INDEX_OF_START_SESSION = 0;
    private static final int INDEX_OF_END_SESSION = 1;

    public static String averageDuration(List<String> input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        long sumOfTime = 0L;
        for (String string : input) {
            String[] dates = string.split(" - ");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
            LocalDateTime timeIn;
            LocalDateTime timeOut;
            try {
                timeIn = LocalDateTime.parse(dates[INDEX_OF_START_SESSION], formatter);
                timeOut = LocalDateTime.parse(dates[INDEX_OF_END_SESSION], formatter);
            } catch (DateTimeParseException e) {
                return "Error!";
            }

            Duration duration = Duration.between(timeIn, timeOut);
            sumOfTime += duration.toMinutes();
        }
        int average = Math.round((float) sumOfTime / input.size());
        return (Integer.toString(average / MINUTES_TO_HOURS_TRANSFER)
            + "h "
            + Integer.toString(average % MINUTES_TO_HOURS_TRANSFER)
            + "m");
    }
}
