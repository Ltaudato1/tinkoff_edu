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

    private static final int FROM_FIRST_TO_THIRTEENTH_DAY_OF_MONTH = 12;
    private static final int THIRTEENTH_DAY_OF_MONTH = 13;

    public static List<LocalDate> getAllFriday13(LocalDate startDate) {
        if (startDate == null) {
            return null;
        }


        LocalDate endDate = LocalDate.of(startDate.getYear() + 1, 1, 1);

        List<LocalDate> result = new ArrayList<>();
        for (LocalDate currentDate = LocalDate.of(startDate.getYear(), 1, THIRTEENTH_DAY_OF_MONTH);
             currentDate.isBefore(endDate);
             currentDate = currentDate.plusMonths(1)) {
            if (currentDate.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
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
            LocalDate nextFriday13th = LocalDate.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
            if (nextFriday13th.getDayOfMonth() < THIRTEENTH_DAY_OF_MONTH) {
                nextFriday13th = nextFriday13th.minusMonths(1);
            }
            nextFriday13th = nextFriday13th
                .with(TemporalAdjusters.firstDayOfNextMonth())
                .plusDays(FROM_FIRST_TO_THIRTEENTH_DAY_OF_MONTH);

            while (nextFriday13th.getDayOfWeek() != DayOfWeek.FRIDAY) {
                nextFriday13th = nextFriday13th
                    .with(TemporalAdjusters.firstDayOfNextMonth())
                    .plusDays(FROM_FIRST_TO_THIRTEENTH_DAY_OF_MONTH);
            }
            return nextFriday13th;
        }

    }
}
