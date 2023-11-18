package edu.hw5;

import java.util.regex.Pattern;

public class Task8 {
    private Task8() {

    }

    // Пункт 1
    public static Boolean notDefine(String input) {
        if (input == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("^([01]{2})*[01]$");
        return pattern.matcher(input).matches();
    }

    // Пункт 2
    public static Boolean starts0DefineOrStarts1NotDefine(String input) {
        if (input == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("^(0([01]{2})*$)|(1([01]{2})*[01]$)");
        return pattern.matcher(input).matches();
    }

    // Пункт 3
    public static Boolean zeroesMultipleOf3(String input) {
        if (input == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("^(((1*0){3,})|1*)$");
        return pattern.matcher(input).matches();
    }

    // Пункт 4
    public static Boolean anyExcept11And111(String input) {
        if (input == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("^1{2}|1{3}$");
        return !pattern.matcher(input).matches();
    }

    // Пункт 5
    // Здесь я положил, что нумерация идёт с 1
    public static Boolean notDefineSymIs1(String input) {
        if (input == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("^(1[01])*$");
        return pattern.matcher(input).matches();
    }

    // Пункт 7
    public static Boolean noConsistent1(String input) {
        if (input == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("11");
        return !pattern.matcher(input).find();
    }
}
