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

        long sumOfTime = input.stream()
            .map(string -> string.split(" - "))
            .map(dates -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
                try {
                    LocalDateTime timeIn = LocalDateTime.parse(dates[INDEX_OF_START_SESSION], formatter);
                    LocalDateTime timeOut = LocalDateTime.parse(dates[INDEX_OF_END_SESSION], formatter);
                    return Duration.between(timeIn, timeOut).toMinutes();
                } catch (DateTimeParseException e) {
                    return 0L;
                }
            })
            .reduce(Long::sum)
            .orElse(0L);

        Duration averageDuration = Duration.ofMinutes(sumOfTime).dividedBy(input.size());
        if (averageDuration.isZero()) {
            return "Error!";
        }

        return String.format("%dh %dm", averageDuration.toHours(), averageDuration.toMinutesPart());
    }
}

