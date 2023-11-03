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
            .sorted(new HeightComparator())
            .toList();
    }

    private static class HeightComparator implements Comparator<Animal> {

        @Override
        public int compare(Animal o1, Animal o2) {
            return Integer.compare(o1.height(), o2.height());
        }
    }
}
