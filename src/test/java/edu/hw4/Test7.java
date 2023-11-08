package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw4.Task7.kOldestAnimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test7 {
    static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("Bob", Animal.Type.SPIDER, Animal.Sex.M, 18, 12, 20, true),
                    new Animal("Freddy", Animal.Type.FISH, Animal.Sex.M, 100, 20, 20, false),
                    new Animal("Vasyan", Animal.Type.CAT, Animal.Sex.M, 12, 56, 5, true),
                    new Animal("Mystery", Animal.Type.DOG, Animal.Sex.F, 10, 78, 12, true)
                ),
                2,
                new Animal("Bob", Animal.Type.SPIDER, Animal.Sex.M, 18, 12, 20, true)
            ),
            Arguments.of(
                List.of(
                    new Animal("Bob", Animal.Type.SPIDER, Animal.Sex.M, 18, 12, 20, true),
                    new Animal("Freddy", Animal.Type.FISH, Animal.Sex.M, 100, 20, 20, false),
                    new Animal("Vasyan", Animal.Type.CAT, Animal.Sex.M, 12, 56, 5, true),
                    new Animal("Mystery", Animal.Type.DOG, Animal.Sex.F, 10, 78, 12, true)
                ),
                5,
                new Animal("Mystery", Animal.Type.DOG, Animal.Sex.F, 10, 78, 12, true)
            ),
            Arguments.of(null, 10000, null),
            Arguments.of(List.of(), 19, null)
        );
    }

    @ParameterizedTest
    @DisplayName("Sample test")
    @MethodSource("provideData")
    void test(List<Animal> input, Integer k, Animal answer) {
        Animal result = kOldestAnimal(input, k);
        assertEquals(answer, result);
    }
}
