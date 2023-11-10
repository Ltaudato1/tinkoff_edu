package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import static edu.hw5.Task3.Main.parseDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test3 {
    static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of("2020-02-10", Optional.of(LocalDate.of(2020, 2, 10))),
            Arguments.of("2020-12-2", Optional.of(LocalDate.of(2020, 12, 2))),
            Arguments.of("1/3/1976", Optional.of(LocalDate.of(1976, 3, 1))),
            Arguments.of("1/3/20", Optional.of(LocalDate.of(20, 3, 1))),
            Arguments.of("tomorrow", Optional.of(LocalDate.now().plusDays(1))),
            Arguments.of("today", Optional.of(LocalDate.now())),
            Arguments.of("yesterday", Optional.of(LocalDate.now().minusDays(1))),
            Arguments.of("228 days ago", Optional.of(LocalDate.now().minusDays(228))),
            Arguments.of("420 weeks ago", Optional.of(LocalDate.now().minusWeeks(420))),
            Arguments.of("1337 years ago", Optional.of(LocalDate.now().minusYears(1337))),
            Arguments.of("1488 days later", Optional.of(LocalDate.now().plusDays(1488))),
            Arguments.of("1 week later", Optional.of(LocalDate.now().plusWeeks(1))),
            Arguments.of("1 year later", Optional.of(LocalDate.now().plusYears(1))),
            Arguments.of("nuuu kogda eto nu ti ponyal", Optional.empty()),
            Arguments.of(null, Optional.empty())
        );
    }

    @ParameterizedTest
    @DisplayName("Sample test")
    @MethodSource("provideData")
    void test(String input, Optional<LocalDate> answer) {
        Optional<LocalDate> result = parseDate(input);
        assertEquals(answer, result);
    }
}
