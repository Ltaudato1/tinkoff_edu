package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw4.Task14.dogHigherThanK;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test14 {
    static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("Bob Marley", Animal.Type.SPIDER, Animal.Sex.M, 8, 12, 20, true),
                    new Animal("Freddy", Animal.Type.FISH, Animal.Sex.M, 100, 20, 20, false),
                    new Animal("Vasyan-Tolyan-Kalyan", Animal.Type.CAT, Animal.Sex.M, 12, 56, 5, true),
                    new Animal("Mystery", Animal.Type.DOG, Animal.Sex.F, 10, 78, 120, true)
                ),
                77,
                true
            ),
            Arguments.of(null, 228, false),
            Arguments.of(List.of(), 1337, false)
        );
    }

    @ParameterizedTest
    @DisplayName("Sample test")
    @MethodSource("provideData")
    void test(List<Animal> input, Integer k, Boolean answer) {
        Boolean result = dogHigherThanK(input, k);
        assertEquals(answer, result);
    }
}
