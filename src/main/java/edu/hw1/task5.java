package edu.hw1;

 final class task5 {
    private static String getDescendant(String number){
        char[] digits = number.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < digits.length; i += 2){
            int num1 = Character.getNumericValue(digits[i]);
            int num2 = Character.getNumericValue(digits[i+1]);
            result.append(Integer.toString(num1 + num2));
        }
        return result.toString();
    }
    private static boolean isCurrentPalindrome(String number){
        StringBuilder reversed = new StringBuilder(number).reverse();
        return reversed.toString().equals(number);
    }
    public static boolean isPalindromeDescendant(long number){
        String num = Long.toString(number);
        while (num.length() % 2 != 1){
            if (isCurrentPalindrome(num)){
                return true;
            }
            else{
                num = getDescendant(num);
            }
        }
        return (num.length() > 1 && isCurrentPalindrome(num));
    }
}
