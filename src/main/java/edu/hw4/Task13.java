package edu.hw4;

import java.util.List;

public class Task13 {
    private Task13() {

    }

    public static List<Animal> namesMoreThanTwo(List<Animal> input) {
        if (input == null) {
            return null;
        }
        return input.stream()
            .filter(animal -> animal.name().split(" |-").length > 2)
            .toList();
    }
}
