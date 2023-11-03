package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import static edu.hw4.Task9.pawSum;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test9 {
    static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("Bob", Animal.Type.SPIDER, Animal.Sex.M, 18, 12, 20, true),
                    new Animal("Freddy", Animal.Type.FISH, Animal.Sex.M, 100, 20, 20, false),
                    new Animal("Vasyan", Animal.Type.CAT, Animal.Sex.M, 12, 56, 5, true),
                    new Animal("Mystery", Animal.Type.DOG, Animal.Sex.F, 10, 78, 120, true)
                ),
                16
            ),
            Arguments.of(null, null),
            Arguments.of(List.of(), 0)
        );
    }

    @ParameterizedTest
    @DisplayName("Sample test")
    @MethodSource("provideData")
    void test(List<Animal> input, Integer answer) {
        Integer result = pawSum(input);
        assertEquals(answer, result);
    }
}
