package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class test7 {
    @ParameterizedTest(name="Число: {0} сдвигается на {1} влево, Верный ответ - {2}")
    @DisplayName("Тесты для task7.rotateLeft")
    @CsvSource({
        "17, 2, 6",
        "16, 1, 1",
        "17, 12, 6"
    })
    void testForLeft(int n, int shift, int answer){
        int result = task7.rotateLeft(n, shift);
        assertThat(result).isEqualTo(answer);
    }

    @ParameterizedTest(name="Число: {0} сдвигается на {1} вправо, Верный ответ - {2}")
    @DisplayName("Тесты для task7.rotateRight")
    @CsvSource({
        "8, 1, 4",
        "0, 100, 0",
        "-6, 2, -1",
        "33, 3, 12",
        "36, 4, 18",
        "10, -1, -1"
    })
    void testForRight(int n, int shift, int answer){
        int result = task7.rotateRight(n, shift);
        assertThat(result).isEqualTo(answer);
    }

    @Test
    @DisplayName("Тест с отрицательным n")
    void testInvalidN(){
        int result = task7.rotateLeft(-124, 3);
        assertThat(result).isEqualTo(-1);
    }
}
