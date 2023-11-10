package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw5.Task2.getAllFriday13;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test2 {
    static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of(
                // [1925-02-13, 1925-03-13, 1925-11-13]
                LocalDateTime.of(1925, 1, 1, 0, 0),
                List.of(
                    LocalDateTime.of(1925, 2, 13, 0, 0),
                    LocalDateTime.of(1925, 3, 13, 0, 0),
                    LocalDateTime.of(1925, 11, 13, 0, 0)
                )
            ),
            Arguments.of(
                // [2024-09-13, 2024-12-13]
                LocalDateTime.of(2024, 1, 1, 0, 0),
                List.of(
                    LocalDateTime.of(2024, 9, 13, 0, 0),
                    LocalDateTime.of(2024, 12, 13, 0, 0)
                )
            ),
            Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @DisplayName("Sample test")
    @MethodSource("provideData")
    void test(LocalDateTime input, List<LocalDateTime> answer) {
        List<LocalDateTime> result = getAllFriday13(input);
        assertEquals(answer, result);
    }
}
