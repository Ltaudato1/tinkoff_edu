package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import static edu.hw3.Task2.clusterize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Test2 {
    private record Data(String input, ArrayList<String> answer) {}

    static Stream<Arguments> provideData() {
        ArrayList<String> list1 = new ArrayList<>(Arrays.asList("()", "()", "()"));
        ArrayList<String> list2 = new ArrayList<>(Arrays.asList("((()))"));
        ArrayList<String> list3 = new ArrayList<>(Arrays.asList("((()))", "(())", "()", "()", "(()())"));
        ArrayList<String> list4 = new ArrayList<>(Arrays.asList("((())())", "(()(()()))"));
        return Stream.of(
            Arguments.of("()()()", list1),
            Arguments.of("((()))", list2),
            Arguments.of("((()))(())()()(()())", list3),
            Arguments.of("((())())(()(()()))", list4)
        );
    }

    @ParameterizedTest
    @DisplayName("Sample tests")
    @MethodSource("provideData")
    void sampleTest(String input, ArrayList<String> answer) throws Exception {
        ArrayList<String> result = clusterize(input);
        assertEquals(result, answer);
    }

    static Stream<Arguments> provideInvalidData() {
        ArrayList<String> list5 = new ArrayList<>(Arrays.asList(""));
        ArrayList<String> list6 = new ArrayList<>(Arrays.asList((String) null));
        ArrayList<String> list7 = new ArrayList<>(Arrays.asList((String) null));
        return Stream.of(
            Arguments.of("", list5),
            Arguments.of(null, list6),
            Arguments.of("()))()", list7)
        );
    }

    @ParameterizedTest
    @DisplayName("Tests with invalid input")
    @MethodSource("provideInvalidData")
    void testInvalidInput(String input, ArrayList<String> answer) {
        try {
            ArrayList<String> result = clusterize(input);
            fail();
        } catch (Exception e) {
            return;
        }
    }
}
