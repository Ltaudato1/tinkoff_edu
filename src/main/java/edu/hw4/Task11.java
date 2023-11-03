package edu.hw4;

import java.util.List;

public class Task11 {
    private Task11() {

    }

    private static final int CONDITION = 100;

    public static List<Animal> canBiteAndTall(List<Animal> input) {
        if (input == null) {
            return null;
        }
        return input.stream()
            .filter(animal -> animal.bites() && animal.height() > CONDITION)
            .toList();
    }
}
