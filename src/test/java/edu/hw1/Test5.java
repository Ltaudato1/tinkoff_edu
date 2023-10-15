package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test5 {
    @ParameterizedTest(name="{0} --> true")
    @DisplayName("Тесты с нормальными значениями (true)")
    @ValueSource(longs = {11211230, 13001120, 23336014, 11, 123312})
    void normalTestsTrue(long input) {
        boolean result = Task5.isPalindromeDescendant(input);
        assertThat(result).isTrue();
    }

    @ParameterizedTest(name="{0} --> false")
    @DisplayName("Тесты с нормальными значениями (false)")
    @ValueSource(longs = {1121203, 130010, 21236014, 112, 1232})
    void normalTestsFalse(long input) {
        boolean result = Task5.isPalindromeDescendant(input);
        assertThat(result).isFalse();
    }
}
