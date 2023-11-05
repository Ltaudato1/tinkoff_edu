package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static edu.hw4.Animal.Sex.F;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Task6.heaviestAnimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test6 {
    static Stream<Arguments> provideData() {
        Animal nullObject = new Animal(null, null, null, 0, 0, 0, false);
        return Stream.of(
            Arguments.of(List.of(
                new Animal("Bob", Animal.Type.SPIDER, M, 18, 12, 21, true),
                new Animal("Breddy", Animal.Type.FISH, M, 100, 20, 20, false),
                new Animal("Freddy", Animal.Type.FISH, M, 100, 20, 25, false),
                new Animal("Bobby", Animal.Type.FISH, M, 100, 20, 22, false),
                new Animal("Vasyan", Animal.Type.CAT, M, 12, 56, 5, true),
                new Animal("Tolyan", Animal.Type.CAT, M, 12, 56, 11, true),
                new Animal("Patricia", Animal.Type.DOG, F, 10, 78, 12, true)
            ), new HashMap<>(Map.ofEntries(
                Map.entry(Animal.Type.DOG, new Animal("Patricia", Animal.Type.DOG, F, 10, 78, 12, true)),
                Map.entry(Animal.Type.FISH, new Animal("Freddy", Animal.Type.FISH, M, 100, 20, 25, false)),
                Map.entry(Animal.Type.CAT, new Animal("Tolyan", Animal.Type.CAT, M, 12, 56, 11, true)),
                Map.entry(Animal.Type.SPIDER, new Animal("Bob", Animal.Type.SPIDER, M, 18, 12, 21, true))
            ))),
            Arguments.of(null, null),
            Arguments.of(List.of(), new HashMap<>())
        );
    }



    @ParameterizedTest
    @DisplayName("Sample test")
    @MethodSource("provideData")
    void test(List<Animal> input, Map<Animal.Type, Animal> answer) {
        Map<Animal.Type, Animal> result = heaviestAnimal(input);
        assertEquals(answer, result);
    }

}
