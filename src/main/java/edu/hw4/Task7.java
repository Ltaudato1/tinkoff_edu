package edu.hw4;

import java.util.Comparator;
import java.util.List;
import static java.lang.Math.min;

public class Task7 {
    private Task7() {

    }

    public static Animal kOldestAnimal(List<Animal> input, Integer k) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        return input.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .toList()
            .get(min(k - 1, input.size() - 1));
    }
}
