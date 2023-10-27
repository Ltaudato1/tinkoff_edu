package edu.hw3;

import java.util.HashMap;

public class Task1 {
    private Task1() {

    }

    private static final int A_FOR_UPPERCASE = 65;
    private static final int Z_FOR_UPPERCASE = 90;
    private static final int A_FOR_LOWERCASE = 97;
    private static final int Z_FOR_LOWERCASE = 122;
    private static final int ENGLISH_ALPHABET_LENGTH = 26;


    public static String atbash(String message) {
        if (message == null) {
            return null;
        }

        HashMap<Character, Character> atbashMap = new HashMap<>();

        // Заполняем словарь
        // Для верхнего регистра
        for (int i = 0; i < ENGLISH_ALPHABET_LENGTH; ++i) {
            Character key = Character.toChars(A_FOR_UPPERCASE + i)[0];
            Character value = Character.toChars(Z_FOR_UPPERCASE - i)[0];
            atbashMap.put(key, value);
        }

        // Для нижнего регистра
        for (int i = 0; i < ENGLISH_ALPHABET_LENGTH; ++i) {
            Character key = Character.toChars(A_FOR_LOWERCASE + i)[0];
            Character value = Character.toChars(Z_FOR_LOWERCASE - i)[0];
            atbashMap.put(key, value);
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); ++i) {
            Character currentSymbol = message.charAt(i);
            if (Character.isAlphabetic(currentSymbol)) {
                builder.append(atbashMap.get(currentSymbol));
            } else {
                builder.append(currentSymbol);
            }
        }
        return builder.toString();
    }
}
