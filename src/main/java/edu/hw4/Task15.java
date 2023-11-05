package edu.hw4;

import edu.hw4.Animal.Type;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task15 {
    private Task15() {

    }

    public static Map<Type, Integer> fromKToIWeight(List<Animal> input, Integer k, Integer i) {
        if (input == null || k == null || i == null) {
            return null;
        }

        return input.stream()
            .filter(animal -> animal.age() > Math.min(k, i) && animal.age() < Math.max(k, i))
            .collect(Collectors.toMap(
                Animal::type,
                Animal::weight,
                Integer::sum,
                () -> new EnumMap<>(Animal.Type.class)
            ));
    }
}
