package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class Task6 {
    private Task6() {

    }

    public static Map<Animal.Type, Animal> heaviestAnimal(List<Animal> input) {
        if (input == null) {
            return null;
        }

        return input.stream()
            .collect(Collectors.toMap(
                Animal::type,
                a -> a,
                BinaryOperator.maxBy(Comparator.comparingInt(Animal::weight))));
    }

}
