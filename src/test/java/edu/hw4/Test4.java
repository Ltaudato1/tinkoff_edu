package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw4.Task4.longestAnimalName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test4 {
    static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of(List.of(
                new Animal("Bob", Animal.Type.SPIDER, Animal.Sex.M, 18, 12, 20, true),
                new Animal("Freddy", Animal.Type.FISH, Animal.Sex.M, 100, 20, 20, false),
                new Animal("Vasyan", Animal.Type.CAT, Animal.Sex.M, 12, 56, 5, true),
                new Animal("Mystery", Animal.Type.DOG, Animal.Sex.F, 10, 78, 12, true)
            ),
                new Animal("Mystery", Animal.Type.DOG, Animal.Sex.F, 10, 78, 12, true)
            ),
            Arguments.of(null, null),
            Arguments.of(List.of(), null)
        );
    }

    @ParameterizedTest
    @DisplayName("Sample test")
    @MethodSource("provideData")
    void test(List<Animal> input, Animal answer) {
        Animal result = longestAnimalName(input);
        assertEquals(answer, result);
    }
}
