package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw7.Task4.monteCarloManyThreads;
import static edu.hw7.Task4.monteCarloSingleThread;

public class Test4 {
    @Test
    @DisplayName("sample test")
    void test() throws InterruptedException {
        monteCarloSingleThread(10000000);
        monteCarloSingleThread(100000000);
        monteCarloSingleThread(1000000000);

        monteCarloManyThreads(10000000);
        monteCarloManyThreads(100000000);
        monteCarloManyThreads(1000000000);
    }
}
