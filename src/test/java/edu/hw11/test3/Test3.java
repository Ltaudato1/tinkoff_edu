package edu.hw11.test3;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static edu.hw11.Task3.generateFibFunction;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test3 {
    @Test
    void sampleTest()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        byte[] bytecode = generateFibFunction();

        ByteClassLoader classLoader = new ByteClassLoader();
        Class<?> fibClass = classLoader.defineClass("Fibonacci", bytecode);

        assert fibClass.getName().equals("Fibonacci");

        Method fibMethod = fibClass.getDeclaredMethod("fib", int.class);

        long result = (long) fibMethod.invoke(null, 5);

        assertEquals(8, result);
    }
}
