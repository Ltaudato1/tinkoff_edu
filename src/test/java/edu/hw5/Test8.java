package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static edu.hw5.Task7.atLeast3EndsWithZero;
import static edu.hw5.Task7.lengthOneToThree;
import static edu.hw5.Task7.startsAndEndsWithOneSym;
import static edu.hw5.Task8.anyExcept11And111;
import static edu.hw5.Task8.noConsistent1;
import static edu.hw5.Task8.notDefine;
import static edu.hw5.Task8.notDefineSymIs1;
import static edu.hw5.Task8.starts0DefineOrStarts1NotDefine;
import static edu.hw5.Task8.zeroesMultipleOf3;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test8 {
    static Stream<Arguments> provideData1() {
        return Stream.of(
            Arguments.of("11010", true),
            Arguments.of("11", false),
            Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @DisplayName("Test for the first pattern")
    @MethodSource("provideData1")
    void test1(String input, Boolean answer) {
        Boolean result = notDefine(input);
        assertEquals(answer, result);
    }

    static Stream<Arguments> provideData2() {
        return Stream.of(
            Arguments.of("0110101", true),
            Arguments.of("110101", true),
            Arguments.of("11000", false),
            Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @DisplayName("Test for the second pattern")
    @MethodSource("provideData2")
    void test2(String input, Boolean answer) {
        Boolean result = starts0DefineOrStarts1NotDefine(input);
        assertEquals(answer, result);
    }

    static Stream<Arguments> provideData3() {
        return Stream.of(
            Arguments.of("110010011010", true),
            Arguments.of("1111111", true),
            Arguments.of("111101", false),
            Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @DisplayName("Test for the third pattern")
    @MethodSource("provideData3")
    void test3(String input, Boolean answer) {
        Boolean result = zeroesMultipleOf3(input);
        assertEquals(answer, result);
    }

    static Stream<Arguments> provideData4() {
        return Stream.of(
            Arguments.of("1100", true),
            Arguments.of("11", false),
            Arguments.of("111", false),
            Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @DisplayName("Test for the fourth pattern")
    @MethodSource("provideData4")
    void test4(String input, Boolean answer) {
        Boolean result = anyExcept11And111(input);
        assertEquals(answer, result);
    }

    static Stream<Arguments> provideData5() {
        return Stream.of(
            Arguments.of("101110101110", true),
            Arguments.of("110010", false),
            Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @DisplayName("Test for the fifth pattern")
    @MethodSource("provideData5")
    void test5(String input, Boolean answer) {
        Boolean result = notDefineSymIs1(input);
        assertEquals(answer, result);
    }

    static Stream<Arguments> provideData7() {
        return Stream.of(
            Arguments.of("10010100", true),
            Arguments.of("11111", false),
            Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @DisplayName("Test for the seventh pattern")
    @MethodSource("provideData7")
    void test7(String input, Boolean answer) {
        Boolean result = noConsistent1(input);
        assertEquals(answer, result);
    }
}
