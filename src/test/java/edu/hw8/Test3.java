package edu.hw8;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static edu.hw8.Task3.multipleThreadBruteForce;
import static edu.hw8.Task3.singleThreadBruteForce;
import static java.lang.System.currentTimeMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test3 {

    private static final Map<String, String> hashes = Map.of(
        "v.n. petrov", "4ef5382e9a3c3962f8f0b81d508b24df",
        "a.b. grigoriev", "637e221241619aab5a55637f70281f6b"
    );
    private static final Map<String, String> passwords = Map.of(
        "v.n. petrov", "aba4",
        "a.b. grigoriev", "Dota"
    );

    @Test
    void singleThreadTest() {
        long startTime = currentTimeMillis();
        for (String key : hashes.keySet()) {
            String password = singleThreadBruteForce(hashes.get(key));
            assertEquals(passwords.get(key), password);
        }
        System.out.println("Brute force time: " + (currentTimeMillis() - startTime) + "ms for single thread");
    }

    @Test
    void multipleThreadTest() {
        long startTime = currentTimeMillis();
        for (String key : hashes.keySet()) {
            String password = multipleThreadBruteForce(hashes.get(key));
            assertEquals(passwords.get(key), password);
        }
        System.out.println("Brute force time: " + (currentTimeMillis() - startTime) + "ms for multiple thread");
    }
}
