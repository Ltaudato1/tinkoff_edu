package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw5.Task1.averageDuration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test1 {
    static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of(
                new ArrayList<String>(List.of(
                    "2022-03-12, 20:20 - 2022-03-12, 23:50",
                    "2022-04-01, 21:30 - 2022-04-02, 01:20"
                )),
                "3h 40m"
            ),
            Arguments.of(
                new ArrayList<String>(List.of(
                    "2023-03-03, 14:88 - 2118-05-12, 04:20"
                )),
                "Error!"
            ),
            Arguments.of(
                null,
                null
            ),
            Arguments.of(
                List.of(),
                null
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Sample test")
    @MethodSource("provideData")
    void test(List<String> input, String answer) {
        String result = averageDuration(input);
        assertEquals(answer, result);
    }
}
