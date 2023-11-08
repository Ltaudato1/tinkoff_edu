package edu.hw4;

import java.util.List;

public class Task9 {
    private Task9() {

    }

    public static Integer pawSum(List<Animal> input) {
        if (input == null) {
            return null;
        }
        return input.stream()
            .mapToInt(Animal::paws)
            .sum();
    }
}
