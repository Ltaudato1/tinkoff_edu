package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw5.Task2.getAllFriday13;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Test2 {
    static Stream<Arguments> provideData1() {
        return Stream.of(
            Arguments.of(
                // [1925-02-13, 1925-03-13, 1925-11-13]
                LocalDate.of(1925, 1, 1),
                List.of(
                    LocalDate.of(1925, 2, 13),
                    LocalDate.of(1925, 3, 13),
                    LocalDate.of(1925, 11, 13)
                )
            ),
            Arguments.of(
                // [2024-09-13, 2024-12-13]
                LocalDate.of(2024, 1, 1),
                List.of(
                    LocalDate.of(2024, 9, 13),
                    LocalDate.of(2024, 12, 13)
                )
            ),
            Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @DisplayName("Sample test for getAllFriday13")
    @MethodSource("provideData1")
    void test1(LocalDate input, List<LocalDate> answer) {
        List<LocalDate> result = getAllFriday13(input);
        assertEquals(answer, result);
    }

    static Stream<Arguments> provideData2() {
        return Stream.of(
            Arguments.of(
                LocalDate.of(1925, 1, 1),
                LocalDate.of(1925, 2, 13)
            ),
            Arguments.of(
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 9, 13)
            ),
            Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @DisplayName("Sample test for getNextFriday13")
    @MethodSource("provideData2")
    void test2(LocalDate input, LocalDate answer) {
        try {
            LocalDate result = input.with(new Task2.NextFriday13());
            assertEquals(answer, result);
        } catch (NullPointerException e) {
            assertNull(input);
        }
    }
}
