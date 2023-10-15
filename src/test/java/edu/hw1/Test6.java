package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test6 {
    @ParameterizedTest(name="Число: {0}, Верный ответ - {1}")
    @DisplayName("Тесты с нормальными значениями")
    @CsvSource({
        "3524, 3",
        "6621, 5",
        "6554, 4",
        "1234, 3"
    })
    void testNormal(long input, int answer) {
        int result = Task6.kaprekar(input);
        assertThat(result).isEqualTo(answer);
    }

    @ParameterizedTest(name="Число: {0} - неккоректный ввод")
    @DisplayName("Тесты с неккоректным вводом")
    @CsvSource({
        "1324123123, -1",
        "123, -1",
        "0, -1"
    })
    void testInvalid(long input, int answer) {
        int result = Task6.kaprekar(input);
        assertThat(result).isEqualTo(answer);
    }
}
