package edu.hw4;

import java.util.Comparator;
import java.util.List;

public class Task18 {
    private Task18() {

    }

    public static Animal heaviestFish(List<List<Animal>> input) {
        if (input == null || input.isEmpty()) {
            return null;
        }

        return input.stream()
            .flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .get();
    }
}
