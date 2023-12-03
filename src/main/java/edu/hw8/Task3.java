package edu.hw8;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static java.lang.Math.pow;

public class Task3 {
    private Task3() {

    }

    private static final int PASSWORD_ALPHABET_LENGTH = 62;
    private static final int PASSWORD_LENGTH = 4;
    private static final String HASH_ALGORITHM_NAME = "MD5";

    public static Map<String, String> singleThreadBruteForce(Map<String, String> targetHashes) {
        Map<String, String> passwords = new HashMap<>();
        bruteForceInRange(targetHashes, 0, (int) pow(PASSWORD_ALPHABET_LENGTH, PASSWORD_LENGTH), passwords);
        return passwords;
    }

    private static final int NUM_THREADS = 4;
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(NUM_THREADS);

    public static Map<String, String> multiThreadedBruteForce(Map<String, String> targetHashes) {
        Map<String, String> passwords = new ConcurrentHashMap<>();

        try {
            int passwordSpaceSize = (int) Math.pow(PASSWORD_ALPHABET_LENGTH, PASSWORD_LENGTH);
            int batchSize = passwordSpaceSize / NUM_THREADS;

            List<Callable<Void>> tasks = new ArrayList<>();

            for (int i = 0; i < NUM_THREADS; i++) {
                final int startIndex = i * batchSize;
                final int endIndex = (i + 1 == NUM_THREADS) ? passwordSpaceSize : (i + 1) * batchSize;

                tasks.add(() -> {
                    bruteForceInRange(targetHashes, startIndex, endIndex, passwords);
                    return null;
                });
            }

            EXECUTOR_SERVICE.invokeAll(tasks);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            EXECUTOR_SERVICE.shutdown();
        }

        return passwords;
    }

    private static void bruteForceInRange(
        Map<String, String> targetHashes, int start, int end, Map<String, String> passwords
    ) {
        for (int i = start; i < end; i++) {
            final String password = intToPassword(i);
            String currentHash = calcMD5(password);
            if (targetHashes.containsValue(currentHash)) {
                String key = targetHashes
                    .entrySet()
                    .stream()
                    .filter(entry -> currentHash.equals(entry.getValue()))
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse(null);

                if (key != null) {
                    passwords.put(key, password);
                }
            }
        }
    }

    public static String calcMD5(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM_NAME);
            messageDigest.update(input.getBytes());

            byte[] byteData = messageDigest.digest();

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : byteData) {
                stringBuilder.append(String.format("%02x", b));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm");
        }
    }

    private static String intToPassword(int finalN) {
        char[] symbols = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        StringBuilder password = new StringBuilder();

        int n = finalN;

        for (int i = 0; i < PASSWORD_LENGTH; ++i) {
            password.insert(0, symbols[n % symbols.length]);
            n /= symbols.length;
        }

        return password.toString();
    }
}
