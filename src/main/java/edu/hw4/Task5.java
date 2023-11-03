package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task5 {
    private Task5() {

    }

    public static Animal.Sex mostPopularSex(List<Animal> input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        return input.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElseThrow();
    }
}
