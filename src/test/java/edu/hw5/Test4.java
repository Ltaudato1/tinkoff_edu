package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static edu.hw5.Task4.containsAtLeastOne;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test4 {
    static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of("Dota2LOL1", false),
            Arguments.of("%appdata%", true),
            Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @DisplayName("Sample test")
    @MethodSource("provideData")
    void test(String input, Boolean answer) {
        Boolean result = containsAtLeastOne(input);
        assertEquals(answer, result);
    }
}
