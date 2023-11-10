package edu.hw5;

import java.util.regex.Pattern;

public class Task4 {
    private Task4() {

    }

    public static Boolean containsAtLeastOne(String input) {
        if (input == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("[" + Pattern.quote("~!@#$%^&*|") + "]");
        return pattern.matcher(input).find();
    }
}
