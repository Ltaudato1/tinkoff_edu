package edu.hw4;

import java.util.List;

public class Task11 {
    private Task11() {

    }

    private static final int MIN_HEIGHT_FOR_ANIMAL = 101;

    public static List<Animal> canBiteAndTall(List<Animal> input) {
        if (input == null) {
            return null;
        }
        return input.stream()
            .filter(animal -> animal.bites() && animal.height() >= MIN_HEIGHT_FOR_ANIMAL)
            .toList();
    }
}
