package edu.hw3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Task4 {
    private Task4() {

    }

    private static final int I = 1;
    private static final int IV = 4;
    private static final int V = 5;
    private static final int IX = 9;
    private static final int X = 10;
    private static final int XL = 40;
    private static final int L = 50;
    private static final int XC = 90;
    private static final int C = 100;
    private static final int CD = 400;
    private static final int D = 500;
    private static final int CM = 900;
    private static final int M = 1000;

    public static String convertToRoman(Integer input) {
        HashMap<Integer, String> arabicToRoman = new HashMap<>(Map.ofEntries(
            Map.entry(I, "I"),
            Map.entry(IV, "IV"),
            Map.entry(V, "V"),
            Map.entry(IX, "IX"),
            Map.entry(X, "X"),
            Map.entry(XL, "XL"),
            Map.entry(L, "L"),
            Map.entry(XC, "XC"),
            Map.entry(C, "C"),
            Map.entry(CD, "CD"),
            Map.entry(D, "D"),
            Map.entry(CM, "CM"),
            Map.entry(M, "M")
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
