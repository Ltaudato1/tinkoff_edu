package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private Task2() {

    }

    private static final int FRIDAY_THIRTEENTH = 13;

    public static List<LocalDate> getAllFriday13(LocalDate startDate) {
        if (startDate == null) {
            return null;
        }

        LocalDate endDate = LocalDate.of(startDate.getYear() + 1, 1, 1);

        List<LocalDate> result = new ArrayList<>();
        for (LocalDate currentDate = startDate;
             currentDate.isBefore(endDate);
             currentDate = currentDate.plusDays(1)) {
            if (currentDate.getDayOfWeek() == DayOfWeek.FRIDAY && currentDate.getDayOfMonth() == FRIDAY_THIRTEENTH) {
                result.add(currentDate);
            }
        }
        return result;
    }

    public static class NextFriday13 implements TemporalAdjuster {
        @Override
        public Temporal adjustInto(Temporal temporal) {
            if (temporal == null) {
                return null;
            }

            LocalDate date = LocalDate.from(temporal);
            LocalDate nextFriday13th = LocalDate.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth())
                .with(TemporalAdjusters.next(DayOfWeek.FRIDAY));

            while (nextFriday13th.getDayOfMonth() != FRIDAY_THIRTEENTH) {
                nextFriday13th = LocalDate.of(nextFriday13th.getYear(),
                        nextFriday13th.getMonthValue(),
                        nextFriday13th.getDayOfMonth())
                    .with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
            }

            return nextFriday13th;
        }

    }
}
