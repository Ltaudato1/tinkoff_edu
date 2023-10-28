package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw2.Task4.callingInfo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test4 {
    @Test
    @DisplayName("Sample test")
    void sampleTest() {
        Task4.CallingInfo info = callingInfo();
        assertEquals(info.className(), Test4.class.getName());
        assertEquals(info.methodName(), "sampleTest");
    }

    public static class SampleClass { // Класс для тестов
        public SampleClass() {

        }

        public static Task4.CallingInfo callCallingInfo() {
            return callingInfo();
        }
    }

    @Test
    @DisplayName("Test with another class")
    void testAnotherClass() {
        Task4.CallingInfo info = SampleClass.callCallingInfo();
        assertEquals(info.className(), SampleClass.class.getName());
        assertEquals(info.methodName(), "callCallingInfo");
    }

    public static Task4.CallingInfo recursiveFunction(int n) { // Функция для тестов
        if (n == 0) {
            return callingInfo();
        }
        return recursiveFunction(n - 1);
    }

    @Test
    @DisplayName("Test with recursion")
    void testRecursion() {
        Task4.CallingInfo info = recursiveFunction(5); // какое конкретно число - не имеет значения
        assertEquals(info.className(), Test4.class.getName());
        assertEquals(info.methodName(), "recursiveFunction");
    }
}
