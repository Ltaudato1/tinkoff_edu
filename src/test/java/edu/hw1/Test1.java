package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Test1 {
    @Test
    @DisplayName("Тест с нормальными значениями для task1.minutesToSeconds")
    public void testNormal() {
        long result = Task1.minutesToSeconds("12454534:43");
        assertEquals(result, 747272083);
    }

    @Test
    @DisplayName("Тест с неккоректным вводом(без двоеточий) для task1.minutesToSeconds")
    public void testWithoutDoubleDot() {
        long result = Task1.minutesToSeconds("1323131");
        assertEquals(result, -1);
    }

    @Test
    @DisplayName("Тест с неккоректным вводом(больше одного двоеточия) для task1.minutesToSeconds")
    public void testWithManyDoubleDot() {
        long result = Task1.minutesToSeconds("123:456:789");
        assertEquals(result, -1);
    }

    @Test
    @DisplayName("Тест с неккоректным вводом(присутствуют символы) для task1.minutesToSeconds")
    public void testWithChars() {
        long result = Task1.minutesToSeconds("123a:22");
        assertEquals(result, -1);
    }

    @Test
    @DisplayName("Тест с отрицательными значениями для task1.minutesToSeconds")
    public void testWithNegative() {
        long result = Task1.minutesToSeconds("-1234:55");
        assertEquals(result, -1);
    }

    @Test
    @DisplayName("Тест с seconds >= 60 для task1.minutesToSeconds")
    public void testWithLargeSeconds() {
        long result = Task1.minutesToSeconds("123123:61");
        assertEquals(result, -1);
    }
}
