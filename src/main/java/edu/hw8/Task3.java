package edu.hw8;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import static java.lang.Math.pow;

public class Task3 {
    private Task3() {

    }

    private static final int PASSWORD_ALPHABET_LENGTH = 62;
    private static final int PASSWORD_LENGTH = 4;
    private static final String HASH_ALGORITHM_NAME = "MD5";

    public static String singleThreadBruteForce(String targetHash) {
        for (int i = 0; i < pow(PASSWORD_ALPHABET_LENGTH, PASSWORD_LENGTH); ++i) {
            final String password = intToPassword(i);
            String currentHash = calcMD5(password);
            if (currentHash.equals(targetHash)) {
                return password;
            }
        }
        return null;
    }

    public static String multipleThreadBruteForce(String targetHash) {
        final int NUM_THREADS = 4;

        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);

        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < pow(PASSWORD_ALPHABET_LENGTH, PASSWORD_LENGTH); ++i) {
            final String currentPassword = intToPassword(i);

           Callable<String> task = () -> {
               if (calcMD5(currentPassword).equals(targetHash)) {
                   return currentPassword;
               }
               return null;
            };

            Future<String> future = executorService.submit(task);
            futures.add(future);
        }

        try {
            for (Future<String> future : futures) {
                String result = future.get();
                if (result != null) {
                    return result;
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to get a password");
        } finally {
            executorService.shutdown();
        }
        return null;
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
