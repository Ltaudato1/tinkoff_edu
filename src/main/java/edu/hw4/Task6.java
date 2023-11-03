package edu.hw4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task6 {
    private Task6() {

    }

    public static Map<Animal.Type, Animal> heaviestAnimal(List<Animal> input) {
        if (input == null) {
            return null;
        }

        Animal nullObject = new Animal(null, null, null, 0, 0, 0, false);

        return input.stream()
            .collect(Collectors.toMap(
                Animal::type,
                Function.identity(),
                (animal1, animal2) -> animal1.weight() > animal2.weight() ? animal1 : animal2,
                () -> new HashMap<>(Map.ofEntries(
                    Map.entry(Animal.Type.DOG, nullObject),
                    Map.entry(Animal.Type.BIRD, nullObject),
                    Map.entry(Animal.Type.FISH, nullObject),
                    Map.entry(Animal.Type.CAT, nullObject),
                    Map.entry(Animal.Type.SPIDER, nullObject)
                ))
            ));
    }

}
