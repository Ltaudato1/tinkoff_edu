package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw4.Task18.heaviestFish;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test18 {
    static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of(
                List.of(
                    List.of(
                        new Animal("Bob Marley", Animal.Type.SPIDER, Animal.Sex.M, 8, 12, 20, true),
                        new Animal("Arkadiy", Animal.Type.SPIDER, Animal.Sex.M, 8, 12, 20, true),
                        new Animal("Bolzhedor", Animal.Type.SPIDER, Animal.Sex.M, 8, 12, 20, true),
                        new Animal("Freddy", Animal.Type.FISH, Animal.Sex.M, 19, 20, 20, false),
                        new Animal("Vasyan-Tolyan-Kalyan", Animal.Type.CAT, Animal.Sex.M, 23, 56, 5, true),
                        new Animal("Mystery", Animal.Type.DOG, Animal.Sex.F, 10, 78, 120, true),
                        new Animal("BarkBark", Animal.Type.DOG, Animal.Sex.M, 21, 78, 120, true),
                        new Animal("BarkBark", Animal.Type.DOG, Animal.Sex.M, 21, 78, 120, false)
                    ),
                    List.of(
                        new Animal("Bob Marley", Animal.Type.SPIDER, Animal.Sex.M, 8, 12, 20, true),
                        new Animal("Arkadiy", Animal.Type.FISH, Animal.Sex.M, 8, 12, 20, true),
                        new Animal("Bolzhedor", Animal.Type.SPIDER, Animal.Sex.M, 8, 12, 20, true),
                        new Animal("Freddy", Animal.Type.FISH, Animal.Sex.M, 19, 20, 20, false),
                        new Animal("Vasyan-Tolyan-Kalyan", Animal.Type.CAT, Animal.Sex.M, 23, 56, 5, true),
                        new Animal("Mystery", Animal.Type.FISH, Animal.Sex.F, 10, 78, 120, true),
                        new Animal("BarkBark", Animal.Type.DOG, Animal.Sex.M, 21, 78, 120, true),
                        new Animal("BarkBark", Animal.Type.DOG, Animal.Sex.M, 21, 78, 120, false)
                    )
                ),
                new Animal("Mystery", Animal.Type.FISH, Animal.Sex.F, 10, 78, 120, true)
            ),
            Arguments.of(null, null),
            Arguments.of(List.of(), null)
        );
    }


    @ParameterizedTest
    @DisplayName("Sample test")
    @MethodSource("provideData")
    void test(List<List<Animal>> input, Animal answer) {
        Animal result = heaviestFish(input);
        assertEquals(answer, result);
    }
}
