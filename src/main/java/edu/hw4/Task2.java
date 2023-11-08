package edu.hw4;

import java.util.Comparator;
import java.util.List;

public class Task2 {
    private Task2() {

    }

    public static List<Animal> sortAnimalsByWeight(List<Animal> input, Integer k) {
        if (input == null) {
            return null;
        }
        return input.stream()
            .sorted(new WeightComparator())
            .limit(k)
            .toList();
    }

    public static class WeightComparator implements Comparator<Animal> {
        @Override
        public int compare(Animal o1, Animal o2) {
            return Integer.compare(o2.weight(), o1.weight());
        }
    }
}
