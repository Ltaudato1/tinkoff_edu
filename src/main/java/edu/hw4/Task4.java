package edu.hw4;

import java.util.List;

public class Task4 {
    private Task4() {

    }

    public static Animal longestAnimalName(List<Animal> input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        return input.stream()
            .max((animal1, animal2) -> animal1.name().length() - animal2.name().length())
            .get();
    }
}
