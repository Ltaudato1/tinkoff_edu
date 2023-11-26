package edu.hw7;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import static java.lang.System.currentTimeMillis;

@SuppressWarnings("MultipleStringLiterals")
public class Task4 {
    private Task4() {

    }

    private static final int RADIUS = 8;
    private static final int THREADS = 4;
    private static final double FOUR_DOUBLED = 4.0;
    private static final Logger LOGGER = Logger.getLogger(Task4.class.getName());

    public static void monteCarloSingleThread(int n) {
        int circleCount = 0;
        Random random = new Random();
        long startTime = currentTimeMillis();
        for (int i = 0; i < n; ++i) {
            double x = random.nextDouble(-RADIUS, RADIUS);
            double y = random.nextDouble(-RADIUS, RADIUS);
            if (x * x + y * y <= RADIUS * RADIUS) {
                circleCount++;
            }
        }
        long endTime = currentTimeMillis();
        LOGGER.info("pi = " + (FOUR_DOUBLED * circleCount / n));
        LOGGER.info("approximating time: " + (endTime - startTime) + "ms");
    }

    public static void monteCarloManyThreads(int n) throws InterruptedException {
        AtomicInteger circleCount = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(THREADS);
        long startTime = currentTimeMillis();
        for (int i = 0; i < THREADS; ++i) {
            new Thread(() -> {
                int localCircleCount = 0;
                for (int j = 0; j < (n / THREADS); ++j) {
                    double x = ThreadLocalRandom.current().nextDouble(-RADIUS, RADIUS);
                    double y = ThreadLocalRandom.current().nextDouble(-RADIUS, RADIUS);
                    if (x * x + y * y <= RADIUS * RADIUS) {
                        localCircleCount++;
                    }
                }
                circleCount.addAndGet(localCircleCount);
                latch.countDown();
            }).start();
        }
        latch.await();
        long endTime = currentTimeMillis();
        LOGGER.info("pi = " + (FOUR_DOUBLED * circleCount.get() / n));
        LOGGER.info("approximating time: " + (endTime - startTime) + "ms");
    }
}
