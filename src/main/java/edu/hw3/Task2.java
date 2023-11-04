package edu.hw3;

import java.util.ArrayList;

public class Task2 {
    private Task2() {

    }

    private static final String INPUT_ERROR = "input error";

    public static ArrayList<String> clusterize(String input) throws Exception {
        if (input.charAt(0) != '(') {
            throw new Exception(INPUT_ERROR);
        }

        StringBuilder builder = new StringBuilder();
        builder.append(input.charAt(0));
        ArrayList<String> result = new ArrayList<>();
        int counter = 1; // Встречается '(' - +1, ')' - -1, когда 0 - записываем строку в result
        for (int i = 1; i < input.length(); ++i) {
            Character currentSymbol = input.charAt(i);
            builder.append(currentSymbol);
            if (currentSymbol.equals('(')) {
                ++counter;
            } else if (currentSymbol.equals(')')) {
                --counter;
            } else {
                throw new Exception(INPUT_ERROR);
            }

            if (counter == 0) {
                result.add(builder.toString());
                builder = new StringBuilder();
            } else if (counter < 0) {
                throw new Exception(INPUT_ERROR);
            }
        }
        return result;
    }
}
