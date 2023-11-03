package edu.hw4;

import java.util.List;
import java.util.Optional;

public class Task8 {
    private Task8() {

    }

    public static Optional<Animal> heaviestAnimalInGroup(List<Animal> input, Integer k) {
        if (input == null || input.isEmpty()) {
            return Optional.empty();
        }
        Animal result = input.stream()
            .filter(animal -> animal.height() < k)
            .max((animal1, animal2) -> animal1.weight() - animal2.weight())
            .get();
        return Optional.of(result);
    }
}
