package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

// Форматы "tomorrow", "yesterday", "today"
public class FormatterDay extends Formatter {

    @Override
    public Optional<LocalDate> parse(String input) {
        return switch (input.toLowerCase()) {
            case "today" -> Optional.of(LocalDate.now());
            case "tomorrow" -> Optional.of(LocalDate.now().plusDays(1));
            case "yesterday" -> Optional.of(LocalDate.now().minusDays(1));
            default -> (nextFormatter != null) ? nextFormatter.parse(input) : Optional.empty();
        };
    }
}
