package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static edu.hw5.Task5.isRussianSign;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test5 {
    static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of("Р670ММ123", true),
            Arguments.of("АА123ИГ67", false),
            Arguments.of("р670мм123", false),
            Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @DisplayName("Sample test")
    @MethodSource("provideData")
    void test(String input, Boolean answer) {
        Boolean result = isRussianSign(input);
        assertEquals(answer, result);
    }
}
