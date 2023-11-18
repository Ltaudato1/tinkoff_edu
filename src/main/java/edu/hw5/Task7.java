package edu.hw5;

import java.util.regex.Pattern;

public class Task7 {
    private Task7() {

    }

    public static Boolean atLeast3EndsWithZero(String input) {
        if (input == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("^[0-1]{2,}0$");
        return pattern.matcher(input).matches();
    }

    public static Boolean startsAndEndsWithOneSym(String input) {
        if (input == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("^(0|1)([0-1]*)\\1$");
        return pattern.matcher(input).matches();
    }

    public static Boolean lengthOneToThree(String input) {
        if (input == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("^[0-1]{1,3}$");
        return pattern.matcher(input).matches();
    }
}
