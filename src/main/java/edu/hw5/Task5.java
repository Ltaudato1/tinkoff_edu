package edu.hw5;

import java.util.regex.Pattern;

public class Task5 {
    private Task5() {

    }

    public static Boolean isRussianSign(String input) {
        if (input == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("^[А-Я]{1}\\d{3}[А-Я]{2}\\d{2,3}$");
        return pattern.matcher(input).matches();
    }
}
