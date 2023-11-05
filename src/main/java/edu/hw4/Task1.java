package edu.hw4;

import java.util.Comparator;
import java.util.List;

public class Task1 {
    private Task1() {

    }

    public static List<Animal> sortAnimalsByHeight(List<Animal> input) {
        if (input == null) {
            return null;
        }
        return input.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }
}
