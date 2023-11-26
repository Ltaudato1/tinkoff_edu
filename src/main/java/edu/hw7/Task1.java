package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {
    private static final int NUM_THREADS = 4;

    public static int parallelIncrement(int num) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; ++i) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < num; ++j) {
                    counter.incrementAndGet();
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new InterruptedException("Failed to join thread");
            }
        }
        return counter.get();
    }
}
