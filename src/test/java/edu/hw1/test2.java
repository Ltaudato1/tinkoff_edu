package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class test2 {
    @ParameterizedTest(name = "Число {0}, Верный ответ - {1}")
    @CsvSource({
        "4666, 4",
        "544, 3",
        "232123, 6",
        "-32213123, 8",
        "-1, 1",
        "0, 1"
    }
    )
    public void testsForTask2(int num, int answer) {
        int result = task2.countDigits(num);
        assertThat(result).isEqualTo(answer);
    }
}
