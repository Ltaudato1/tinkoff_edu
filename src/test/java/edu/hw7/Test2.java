package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.math.BigInteger;
import java.util.stream.Stream;
import static edu.hw7.Task2.factorial;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test2 {
    static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of(1, new BigInteger("1")),
            Arguments.of(0, new BigInteger("1")),
            Arguments.of(14, new BigInteger("87178291200"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideData")
    @DisplayName("sample test")
    void test(int n, BigInteger answer) {
        BigInteger result = factorial(n);
        assertEquals(answer, result);
    }
}
