package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test3 {
    @Test
    @DisplayName("[1, 2, 3, 4], [0, 6] --> true")
    void testOnTrue() {
        long[] internal = {1, 2, 3, 4};
        long[] external = {0, 6};
        boolean result = Task3.isNestable(internal, external);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("[9, 9, 8], [8, 9] --> false")
    void testOnFalse() {
        long[] internal = {9, 9, 8};
        long[] external = {8, 9};
        boolean result = Task3.isNestable(internal, external);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("[3, 1], [4, 0] --> true")
    void testOnTrue2() {
        long[] internal = {3, 1};
        long[] external = {4, 0};
        boolean result = Task3.isNestable(internal, external);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("[1, 2, 3, 4], [2, 3] --> false")
    void testOnFalse2() {
        long[] internal = {1, 2, 3, 4};
        long[] external = {2, 3};
        boolean result = Task3.isNestable(internal, external);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Тест для task3.isNestable с пустыми массивами --> false")
    void testWithEmpty(){
        long[] internal = null;
        long[] external = {};
        boolean result = Task3.isNestable(internal, external);
        assertThat(result).isFalse();
    }
}
