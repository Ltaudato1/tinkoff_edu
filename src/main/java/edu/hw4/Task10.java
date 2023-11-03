package edu.hw4;

import java.util.List;

public class Task10 {
    private Task10() {

    }

    public static List<Animal> pawsNotAge(List<Animal> input) {
        if (input == null) {
            return null;
        }
        return input.stream()
            .filter(animal -> animal.paws() != animal.age())
            .toList();
    }
}
