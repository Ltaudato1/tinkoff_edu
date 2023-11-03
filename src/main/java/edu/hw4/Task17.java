package edu.hw4;

import java.util.List;

public class Task17 {
    private Task17() {

    }

    public static Boolean spidersDangerThanDogs(List<Animal> input) {
        if (input == null) {
            return null;
        }

        final int MINIMUM_TO_COMPARE = 3;

        Integer spiders = input.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER)
            .toList()
            .size();
        if (spiders < MINIMUM_TO_COMPARE) {
            return false;
        }
        Integer spidersThatBites = input.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites())
            .toList()
            .size();

        Integer dogs = input.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG)
            .toList()
            .size();
        if (dogs < MINIMUM_TO_COMPARE) {
            return false;
        }
        Integer dogsThatBites = input.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG && animal.bites())
            .toList()
            .size();

        return (spidersThatBites / spiders) > (dogsThatBites / dogs);
    }
}
