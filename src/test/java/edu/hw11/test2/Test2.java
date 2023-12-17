package edu.hw11.test2;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test2 {

    @Test
    void sampleTest() throws InstantiationException, IllegalAccessException {
        ArithmeticUtils arithmeticUtils = new ArithmeticUtils();

        ArithmeticUtils modifiedInstance = new ByteBuddy()
            .subclass(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum"))
            .intercept(Advice.to(ModifyMethodAdvice.class))
            .make()
            .load(Test2.class.getClassLoader())
            .getLoaded()
            .newInstance();

        assertEquals(12*5, modifiedInstance.sum(12, 5));
    }
}
