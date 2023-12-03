package edu.hw8;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static edu.hw8.Task3.multiThreadedBruteForce;
import static edu.hw8.Task3.singleThreadBruteForce;
import static java.lang.System.currentTimeMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test3 {

    private static final Map<String, String> hashes = Map.of(
        "v.n. petrov", "4ef5382e9a3c3962f8f0b81d508b24df",
        "a.b. grigoriev", "637e221241619aab5a55637f70281f6b"
    );
    private static final Map<String, String> answer = Map.of(
        "v.n. petrov", "aba4",
        "a.b. grigoriev", "Dota"
    );

    // В многопоточном режиме поиск идёт примерно в три раза быстрее

    @Test
    void singleThreadTest() {
        long startTime = currentTimeMillis();
        Map<String, String> passwords = singleThreadBruteForce(hashes);
        for (String key : answer.keySet()) {
            assertEquals(answer.get(key), passwords.get(key));
        }
        System.out.println("Brute force time: " + (currentTimeMillis() - startTime) + "ms for single thread");
    }

    @Test
    void multipleThreadTest() {
        long startTime = currentTimeMillis();
        Map<String, String> passwords = multiThreadedBruteForce(hashes);
        for (String key : answer.keySet()) {
            assertEquals(answer.get(key), passwords.get(key));
        }
        System.out.println("Brute force time: " + (currentTimeMillis() - startTime) + "ms for multiple thread");
    }
}
