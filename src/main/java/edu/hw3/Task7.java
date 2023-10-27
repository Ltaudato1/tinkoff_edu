package edu.hw3;

import java.util.Comparator;

public class Task7<K> implements Comparator<K> {
    private final Comparator<? super K> comparator;

    public Task7(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int compare(K key1, K key2) {
        if (key1 == null && key2 == null) {
            return 0;
        } else if (key1 == null) {
            return -1;
        } else if (key2 == null) {
            return 1;
        } else {
            return comparator.compare(key1, key2);
        }
    }
}
