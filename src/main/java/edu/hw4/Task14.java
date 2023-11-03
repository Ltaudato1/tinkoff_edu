package edu.hw4;

import java.util.List;

public class Task14 {
    private Task14() {

    }

    public static Boolean dogHigherThanK(List<Animal> input, Integer k) {
        if (input == null) {
            return false;
        }
        return input.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }
}
