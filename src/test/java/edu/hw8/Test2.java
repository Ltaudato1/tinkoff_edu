package edu.hw8;

import edu.hw8.task2.FixedThreadPool;
import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;
import static edu.hw8.task2.FixedThreadPool.fibonacciCalculate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test2 {
    @Test
    void fibonacciCalcTest() throws Exception {
        final CountDownLatch latch = new CountDownLatch(20);
        int[] answer = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181};
        int[] fibonacciElements = new int[20];
        try (FixedThreadPool threadPool = FixedThreadPool.create(4)) {
            threadPool.start();

            for (int i = 0; i < 20; ++i) {
                int finalI = i;
                threadPool.execute(() -> {
                    int result = fibonacciCalculate(finalI);
                    fibonacciElements[finalI] = result;
                    latch.countDown();
                });
            }

            latch.await();

            for (int i = 0; i < 20; ++i) {
                assertEquals(answer[i], fibonacciElements[i]);
            }
        }
    }
}
