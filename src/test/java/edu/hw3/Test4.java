package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static edu.hw3.Task4.convertToRoman;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test4 {
    static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of(2, "II"),
            Arguments.of(12, "XII"),
            Arguments.of(16, "XVI"),
            Arguments.of(58, "LVIII"),
            Arguments.of(58, "LVIII"),
            Arguments.of(1994, "MCMXCIV"),
            Arguments.of(3999, "MMMCMXCIX")
        );
    }

    @ParameterizedTest
    @MethodSource("provideData")
    void test(int input, String answer) {
        String result = convertToRoman(input);
        assertEquals(result, answer);
    }
}
