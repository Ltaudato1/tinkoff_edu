package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Последний формат в задании
@SuppressWarnings("MultipleStringLiterals")
public class FormatterDaysAgo extends Formatter {
    private static final int INDEX_OF_NUMBER = 1;
    private static final int INDEX_OF_MEASUREMENT = 2;
    private static final int INDEX_OF_DIRECTION = 3;

    @Override
    public Optional<LocalDate> parse(String input) {
        Pattern pattern = Pattern.compile("(\\d+)\\s+(\\w+)\\s+(\\w+)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            int n = Integer.parseInt(matcher.group(INDEX_OF_NUMBER));
            String unit = matcher.group(INDEX_OF_MEASUREMENT).toLowerCase();

            return switch (matcher.group(INDEX_OF_DIRECTION).toLowerCase()) {
                case "ago" -> getPastDate(unit, n);
                case "later" -> getFutureDate(unit, n);
                default -> Optional.empty();
            };
        } else {
            return (nextFormatter != null) ? nextFormatter.parse(input) : Optional.empty();
        }
    }

    private Optional<LocalDate> getPastDate(String unit, int n) {
        return switch (unit) {
            case "day", "days" -> Optional.of(LocalDate.now().minusDays(n));
            case "week", "weeks" -> Optional.of(LocalDate.now().minusWeeks(n));
            case "month", "months" -> Optional.of(LocalDate.now().minusMonths(n));
            case "year", "years" -> Optional.of(LocalDate.now().minusYears(n));
            default -> Optional.empty();
        };
    }

    private Optional<LocalDate> getFutureDate(String unit, int n) {
        return switch (unit) {
            case "day", "days" -> Optional.of(LocalDate.now().plusDays(n));
            case "week", "weeks" -> Optional.of(LocalDate.now().plusWeeks(n));
            case "month", "months" -> Optional.of(LocalDate.now().plusMonths(n));
            case "year", "years" -> Optional.of(LocalDate.now().plusYears(n));
            default -> Optional.empty();
        };
    }
}
