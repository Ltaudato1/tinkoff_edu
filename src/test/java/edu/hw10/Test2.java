package edu.hw10;

import edu.hw10.task2.Cache;
import edu.hw10.task2.CacheProxy;
import edu.hw10.task2.FibCalculator;
import org.junit.jupiter.api.Test;
import static java.lang.System.currentTimeMillis;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test2 {
    @Test
    void sampleTest() {
        FibCalculator calculator = new FibCalculator() {
            @Override
            @Cache(persist = true)
            public long fib(int number) {
                if (number < 2) {
                    return 1;
                }
                return fib(number-1) + fib(number-2);
            }
        };

        FibCalculator proxy = CacheProxy.create(calculator, FibCalculator.class);

        long startTime = currentTimeMillis();
        proxy.fib(45);
        long calculatingTime1 = currentTimeMillis() - startTime;

        startTime = currentTimeMillis();
        proxy.fib(45);
        long calculatingTime2 = currentTimeMillis() - startTime;

        assertTrue(calculatingTime2 < calculatingTime1); // Возврат закэшированного значения должен быть быстрее
    }
}
