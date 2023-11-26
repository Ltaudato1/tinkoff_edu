package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static edu.hw7.Task1.parallelIncrement;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test1 {
    static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of(12, 12*4),
            Arguments.of(134217728, 134217728*4),
            Arguments.of(0, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideData")
    @DisplayName("sample test")
    void test(int input, int answer) throws InterruptedException {
        int result = parallelIncrement(input);
        assertEquals(answer, result);
    }
}
