package edu.hw4;

import edu.hw4.Task19.ValidationError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static edu.hw4.Task20.Apply.getErrorNames;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test20 {
    static Stream<Arguments> provideData() {
        String testString = "Name must start with upper case\n";
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("Bob Marley", Animal.Type.SPIDER, Animal.Sex.M, 8, 12, 20, true),
                    new Animal("Arkadiy", Animal.Type.SPIDER, Animal.Sex.M, 8, 12, 20, true),
                    new Animal("Bolzhedor", Animal.Type.SPIDER, Animal.Sex.M, 8, 12, 20, true),
                    new Animal("Freddy", Animal.Type.FISH, Animal.Sex.M, 19, 20, 20, false),
                    new Animal("Vasyan-Tolyan-Kalyan", Animal.Type.CAT, Animal.Sex.M, 23, 56, 5, true),
                    new Animal("Mystery", Animal.Type.DOG, Animal.Sex.F, 10, 78, 120, true),
                    new Animal("BarkBark", Animal.Type.DOG, Animal.Sex.M, 21, 78, 120, true),
                    new Animal("barkbark", Animal.Type.DOG, Animal.Sex.M, 21, 78, 120, false)
                ),
                Map.ofEntries(
                    Map.entry("barkbark", testString)
                )
            ),
            Arguments.of(null, null),
            Arguments.of(List.of(), Map.of())
        );
    }

    @ParameterizedTest
    @DisplayName("Sample test")
    @MethodSource("provideData")
    void test(List<Animal> input, Map<String, String> answer) {
        Map<String, String> result = getErrorNames(input);
        assertEquals(answer, result);
    }
}
