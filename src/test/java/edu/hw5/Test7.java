package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static edu.hw5.Task7.atLeast3EndsWithZero;
import static edu.hw5.Task7.lengthOneToThree;
import static edu.hw5.Task7.startsAndEndsWithOneSym;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test7 {
    static Stream<Arguments> provideData1() {
        return Stream.of(
            Arguments.of("1100", true),
            Arguments.of("11", false),
            Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @DisplayName("Test for the first pattern")
    @MethodSource("provideData1")
    void test1(String input, Boolean answer) {
        Boolean result = atLeast3EndsWithZero(input);
        assertEquals(answer, result);
    }

    static Stream<Arguments> provideData2() {
        return Stream.of(
            Arguments.of("10000001", true),
            Arguments.of("110010", false),
            Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @DisplayName("Test for the second pattern")
    @MethodSource("provideData2")
    void test2(String input, Boolean answer) {
        Boolean result = startsAndEndsWithOneSym(input);
        assertEquals(answer, result);
    }

    static Stream<Arguments> provideData3() {
        return Stream.of(
            Arguments.of("100", true),
            Arguments.of("11111", false),
            Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @DisplayName("Test for the third pattern")
    @MethodSource("provideData3")
    void test3(String input, Boolean answer) {
        Boolean result = lengthOneToThree(input);
        assertEquals(answer, result);
    }
}
