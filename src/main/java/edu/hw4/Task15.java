package edu.hw4;

import edu.hw4.Animal.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task15 {
    private Task15() {

    }

    public static Map<Type, Integer> fromKToIWeight(List<Animal> input, Integer k, Integer i) {
        if (input == null || k == null || i == null) {
            return null;
        }

        List<Animal> filteredInput = input.stream()
            .filter(animal -> animal.age() > Math.min(k, i) && animal.age() < Math.max(k, i))
            .toList();
        Map<Type, Integer> result = new HashMap<>(Map.ofEntries(
            Map.entry(Animal.Type.SPIDER, 0),
            Map.entry(Animal.Type.BIRD, 0),
            Map.entry(Animal.Type.DOG, 0),
            Map.entry(Animal.Type.FISH, 0),
            Map.entry(Animal.Type.CAT, 0)
        ));
        for (Animal animal : filteredInput) {
            result.merge(animal.type(), animal.weight(), Integer::sum);
        }
        return result;
    }
}
