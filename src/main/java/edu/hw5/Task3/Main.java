package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class Main {
    private Main() {

    }

    public static Optional<LocalDate> parseDate(String input) {
        if (input == null) {
            return Optional.empty();
        }

        FormatterCommon formatterCommon = new FormatterCommon();
        FormatterDay formatterDay = new FormatterDay();
        FormatterDaysAgo formatterDaysAgo = new FormatterDaysAgo();

        formatterCommon.setNextFormatter(formatterDay);
        formatterDay.setNextFormatter(formatterDaysAgo);

        return formatterCommon.parse(input);
    }
}
