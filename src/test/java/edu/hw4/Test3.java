package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static edu.hw4.Task3.typeCounter;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test3 {
    static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of(List.of(
                new Animal("Bob", Animal.Type.SPIDER, Animal.Sex.M, 18, 12, 21, true),
                new Animal("Freddy", Animal.Type.FISH, Animal.Sex.M, 100, 20, 20, false),
                new Animal("Freddy", Animal.Type.FISH, Animal.Sex.M, 100, 20, 20, false),
                new Animal("Freddy", Animal.Type.FISH, Animal.Sex.M, 100, 20, 20, false),
                new Animal("Vasyan", Animal.Type.CAT, Animal.Sex.M, 12, 56, 5, true),
                new Animal("Vasyan", Animal.Type.CAT, Animal.Sex.M, 12, 56, 5, true),
                new Animal("Patricia", Animal.Type.DOG, Animal.Sex.F, 10, 78, 12, true)
            ), Map.ofEntries(
                Map.entry(Animal.Type.CAT, 2),
                Map.entry(Animal.Type.FISH, 3),
                Map.entry(Animal.Type.DOG, 1),
                Map.entry(Animal.Type.SPIDER, 1),
                Map.entry(Animal.Type.BIRD, 0)
            )),
            Arguments.of(null, null),
            Arguments.of(List.of(), new HashMap<>(Map.ofEntries(
                Map.entry(Animal.Type.DOG, 0),
                Map.entry(Animal.Type.BIRD, 0),
                Map.entry(Animal.Type.FISH, 0),
                Map.entry(Animal.Type.CAT, 0),
                Map.entry(Animal.Type.SPIDER, 0)
            )))
        );
    }

    @ParameterizedTest
    @DisplayName("Sample test")
    @MethodSource("provideData")
    void test(List<Animal> input, Map<Animal.Type, Integer> answer) {
        Map<Animal.Type, Integer> result = typeCounter(input);
        assertEquals(answer, result);
    }
}
