package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class Test1 {
    @Test
    @DisplayName("Тест с нормальными значениями для task1.minutesToSeconds")
    public void testNormal() {
        long result = Task1.minutesToSeconds("12454534:43");
        assertEquals(result, 747272083);
    }

    @ParameterizedTest(name="Число: {0}, Верный ответ - {1}")
    @DisplayName("Тесты с неккоректными значениями для task1.minutesToSeconds")
    @CsvSource({
        "123:456:789, -1",
        "123232132, -1",
        "-1234:55, -1",
        "123123:61, -1",
        "123a:22, -1"
    })
    public void testWithInvalidArguments(String input, long answer) {
        long result = Task1.minutesToSeconds(input);
        assertEquals(result, answer);
    }
}
