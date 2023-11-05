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

        Integer spiders = Math.toIntExact(input.stream()
            .filter(animal -> animal.type().equals(Animal.Type.SPIDER))
            .count());
        if (spiders < MINIMUM_TO_COMPARE) {
            return false;
        }
        Integer spidersThatBites = Math.toIntExact(input.stream()
            .filter(animal -> animal.type().equals(Animal.Type.SPIDER) && animal.bites())
            .count());

        Integer dogs = Math.toIntExact(input.stream()
            .filter(animal -> animal.type().equals(Animal.Type.DOG))
            .count());
        if (dogs < MINIMUM_TO_COMPARE) {
            return false;
        }
        Integer dogsThatBites = Math.toIntExact(input.stream()
            .filter(animal -> animal.type().equals(Animal.Type.DOG) && animal.bites())
            .count());

        return (spidersThatBites / spiders) > (dogsThatBites / dogs);
    }
}
