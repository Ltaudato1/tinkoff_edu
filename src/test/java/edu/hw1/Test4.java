package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test4 {
    @ParameterizedTest(name="Строка: {0}, Верный ответ - {1}")
    @CsvSource({
        "оПомигети псаривьтс ртко!и, Помогите исправить строки!",
        "123456, 214365",
        "hTsii  s aimex dpus rtni.g, This is a mixed up string.",
        "badce, abcde",
    })
    @DisplayName("Тест task4.fixString с нормальными значениями")
    void testFixString(String input, String answer) {
        String result = Task4.fixString(input);
        assertThat(result).isEqualTo(answer);
    }

    @ParameterizedTest(name="Строка: {0}, верный ответ - пустая строка")
    @DisplayName("Тест task4.fixString c пустыми строками")
    @NullAndEmptySource
    void testWithEmptyString(String input) {
        String result = Task4.fixString(input);
        assertThat(result).isBlank();
    }
}
