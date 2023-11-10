package edu.hw5;

import java.util.regex.Pattern;

public class Task6 {
    private Task6() {

    }

    public static Boolean isSubstr(String string, String substring) {
        if (string == null || substring == null) {
            return null;
        }
        Pattern pattern = Pattern.compile(substring);
        return pattern.matcher(string).find();
    }
}
