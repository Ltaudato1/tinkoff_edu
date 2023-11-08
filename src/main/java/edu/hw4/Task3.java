package edu.hw4;

import java.util.EnumMap;
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
                () -> new EnumMap<>(Animal.Type.class)
            ));


    }

}
