package edu.hw4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task3 {
    private Task3() {

    }

    public static Map<Animal.Type, Integer> typeCounter(List<Animal> input) {
        if (input == null) {
            return null;
        }

        return input.stream()
            .collect(Collectors.toMap(
                Animal::type,
                e -> 1,
                Integer::sum,
                () -> new HashMap<>(Map.of(
                    Animal.Type.DOG, 0,
                    Animal.Type.BIRD, 0,
                    Animal.Type.FISH, 0,
                    Animal.Type.CAT, 0,
                    Animal.Type.SPIDER, 0
                ))
            ));
    }

}
