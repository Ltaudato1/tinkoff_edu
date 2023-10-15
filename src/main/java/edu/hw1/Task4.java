package edu.hw1;

public final class Task4 {
    private Task4() {

    }

    public static String fixString(String input) {
        if (input == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        char[] originalString = input.toCharArray();
        for (int i = 0; i < input.length() - (input.length() % 2); i += 2) {
            builder.append(originalString[i + 1]);
            builder.append(originalString[i]);
        }
        if (input.length() % 2 != 0) {
            builder.append(originalString[input.length() - 1]);
        }
        return builder.toString();
    }
}
