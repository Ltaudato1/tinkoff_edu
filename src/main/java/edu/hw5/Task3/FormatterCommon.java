package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.util.Optional;

// Рассматриваем нормальное задание даты (первые 4 формата из задания)
public class FormatterCommon extends Formatter {
    public Optional<LocalDate> parse(String input) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendOptional(DateTimeFormatter.ofPattern("y-M-d"))
            .appendOptional(DateTimeFormatter.ofPattern("d/M/y"))
            .toFormatter(Locale.ENGLISH);
        try {
            return Optional.of(LocalDate.parse(input, formatter));
        } catch (Exception e) {
            if (nextFormatter != null) {
                return nextFormatter.parse(input);
            } else {
                return Optional.empty();
            }
        }
    }

    @Override
    public void setNextFormatter(Formatter nextFormatter) {
        super.setNextFormatter(nextFormatter);
    }
}
