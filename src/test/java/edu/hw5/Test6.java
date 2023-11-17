package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static edu.hw5.Task6.isSubstr;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test6 {
    static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of("achfdbaabgcaabg", "abc", true),
            Arguments.of("Р670ММ123В777ОР77", "АА123ИГ67", false),
            Arguments.of("abacaba", "abc", true),
            Arguments.of(null, null, null)
        );
    }

    @ParameterizedTest
    @DisplayName("Sample test")
    @MethodSource("provideData")
    void test(String inputString, String inputSubstring, Boolean answer) {
        Boolean result = isSubstr(inputString, inputSubstring);
        assertEquals(answer, result);
    }
}
