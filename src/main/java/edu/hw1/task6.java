package edu.hw1;

import java.util.Arrays;

public final class task6 {
    private static final int KAPREKAR_VALUE = 6174;
    private static final int MINIMUM = 1000;
    private static final int MAXIMUM = 9999;

    private static String sort(String number){
        char[] num = number.toCharArray();
        Arrays.sort(num);
        return new String(num);
    }
    public static int kaprekar(long number){
        if (number < MINIMUM || number > MAXIMUM){
            return -1;
        }
        if (number == KAPREKAR_VALUE){
            return 0;
        }
        String num = sort(String.valueOf(number));
        int sortedDigits = Integer.parseInt(num);

        StringBuilder reverse = new StringBuilder(num).reverse();
        int sortedDigitsReversed = Integer.parseInt(reverse.toString());

        return 1 + kaprekar(sortedDigitsReversed - sortedDigits);
    }
}
