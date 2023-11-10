package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private Task2() {

    }

    private static final int FRIDAY_THIRTEENTH = 13;

    public static List<LocalDateTime> getAllFriday13(LocalDateTime startDate) {
        if (startDate == null) {
            return null;
        }

        LocalDateTime endDate = LocalDateTime.of(startDate.getYear() + 1, 1, 1, 0, 0);

        List<LocalDateTime> result = new ArrayList<>();
        for (LocalDateTime currentDate = startDate;
             currentDate.isBefore(endDate);
             currentDate = currentDate.plusDays(1)) {
            if (currentDate.getDayOfWeek() == DayOfWeek.FRIDAY && currentDate.getDayOfMonth() == FRIDAY_THIRTEENTH) {
                result.add(currentDate);
            }
        }
        return result;
    }
}
