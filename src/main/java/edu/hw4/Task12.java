package edu.hw4;

import java.util.List;

public class Task12 {
    private Task12() {

    }

    public static Integer weightMoreThanAge(List<Animal> input) {
        if (input == null) {
            return null;
        }
        return Math.toIntExact(input.stream()
            .filter(animal -> animal.weight() > animal.age())
            .count());
    }
}
