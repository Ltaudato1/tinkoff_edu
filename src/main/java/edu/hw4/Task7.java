package edu.hw4;

import java.util.List;

public class Task7 {
    private Task7() {

    }

    public static Animal kOldestAnimal(List<Animal> input, Integer k) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        List<Animal> result = input.stream()
            .sorted((animal1, animal2) -> animal2.age() - animal1.age())
            .toList();
        return (k <= result.size()) ? result.get(k - 1) : result.get(result.size() - 1);
    }
}
