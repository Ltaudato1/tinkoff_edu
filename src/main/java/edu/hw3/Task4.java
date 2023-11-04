package edu.hw3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Task4 {
    private Task4() {

    }

    private static final int ONE = 1;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int NINE = 9;
    private static final int TEN = 10;
    private static final int FORTY = 40;
    private static final int FIFTY = 50;
    private static final int NINETY = 90;
    private static final int HUNDRED = 100;
    private static final int FOUR_HUNDRED = 400;
    private static final int FIVE_HUNDRED = 500;
    private static final int NINE_HUNDRED = 900;
    private static final int THOUSAND = 1000;

    public static String convertToRoman(Integer input) {
        HashMap<Integer, String> arabicToRoman = new HashMap<>(Map.ofEntries(
            Map.entry(ONE, "I"),
            Map.entry(FOUR, "IV"),
            Map.entry(FIVE, "V"),
            Map.entry(NINE, "IX"),
            Map.entry(TEN, "X"),
            Map.entry(FORTY, "XL"),
            Map.entry(FIFTY, "L"),
            Map.entry(NINETY, "XC"),
            Map.entry(HUNDRED, "C"),
            Map.entry(FOUR_HUNDRED, "CD"),
            Map.entry(FIVE_HUNDRED, "D"),
            Map.entry(NINE_HUNDRED, "CM"),
            Map.entry(THOUSAND, "M")
        ));

        Integer num = input;
        StringBuilder builder = new StringBuilder();
        ArrayList<Integer> keyList = new ArrayList<>(arabicToRoman.keySet());
        keyList.sort(Collections.reverseOrder());
        for (Integer key : keyList) {
            while (num >= key) {
                builder.append(arabicToRoman.get(key));
                num -= key;
            }
        }
        return builder.toString();
    }
}
