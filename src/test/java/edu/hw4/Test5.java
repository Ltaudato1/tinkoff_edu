package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw4.Animal.Sex.F;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Task5.mostPopularSex;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test5 {
    static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of(List.of(
                new Animal("Bob", Animal.Type.SPIDER, M, 18, 12, 21, true),
                new Animal("Freddy", Animal.Type.FISH, M, 100, 20, 20, false),
                new Animal("Freddy", Animal.Type.FISH, M, 100, 20, 20, false),
                new Animal("Freddy", Animal.Type.FISH, M, 100, 20, 20, false),
                new Animal("Vasyan", Animal.Type.CAT, M, 12, 56, 5, true),
                new Animal("Vasyan", Animal.Type.CAT, M, 12, 56, 5, true),
                new Animal("Patricia", Animal.Type.DOG, F, 10, 78, 12, true)
            ), M),
            Arguments.of(null, null),
            Arguments.of(List.of(), null)
        );
    }

    @ParameterizedTest
    @DisplayName("Sample test")
    @MethodSource("provideData")
    void test(List<Animal> input, Animal.Sex answer) {
        Animal.Sex result = mostPopularSex(input);
        assertEquals(answer, result);
    }
}
