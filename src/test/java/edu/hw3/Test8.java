package edu.hw3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Test8 {

    @Test
    public void testBackwardIterator() {
        Integer[] intArray = {1, 2, 3, 4, 5};
        Task8.BackwardIterator<Integer> iterator = new Task8().new BackwardIterator<>(intArray);

        assertTrue(iterator.hasNext());
        assertEquals(5, iterator.next());
        assertEquals(4, iterator.next());
        assertEquals(3, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(1, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testEmptyArray() {
        Integer[] emptyArray = {};
        Task8.BackwardIterator<Integer> iterator = new Task8().new BackwardIterator<>(emptyArray);

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testStringArray() {
        String[] strArray = {"hello", "world", "java"};
        Task8.BackwardIterator<String> iterator = new Task8().new BackwardIterator<>(strArray);

        assertTrue(iterator.hasNext());
        assertEquals("java", iterator.next());
        assertEquals("world", iterator.next());
        assertEquals("hello", iterator.next());
        assertFalse(iterator.hasNext());
    }
}

