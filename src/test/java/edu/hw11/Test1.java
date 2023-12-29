package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test1 {
    @Test
    void sampleTest() throws InstantiationException, IllegalAccessException {
        Class<?> helloBuddy;
        helloBuddy = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(Test1.class.getClassLoader())
            .getLoaded();

        Object instance = helloBuddy.newInstance();

        String result = instance.toString();

        assertEquals("Hello, ByteBuddy!", result);
    }
}
