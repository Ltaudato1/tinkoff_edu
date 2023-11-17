package edu.hw5;

import java.util.regex.Pattern;

public class Task6 {
    private Task6() {

    }

    public static Boolean isSubstr(String string, String substring) {
        if (string == null || substring == null) {
            return null;
        }
        StringBuilder patternBuilder = new StringBuilder();
        for (char c : substring.toCharArray()) {
            patternBuilder.append(Pattern.quote(String.valueOf(c))).append(".*");
        }
        Pattern pattern = Pattern.compile(patternBuilder.toString());
        return pattern.matcher(string).find();
    }
}
