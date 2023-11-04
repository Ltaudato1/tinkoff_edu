package edu.hw3;

import java.util.ArrayList;
import java.util.HashMap;

public class Task3 {
    private Task3() {

    }

    public static HashMap<Object, Integer> freqDict(ArrayList<Object> input) throws Exception {
        HashMap<Object, Integer> output = new HashMap<>();
        for (Object currentObject : input) {
            if (currentObject == null) {
                throw new Exception("invalid input");
            }
            output.merge(currentObject, 1, Integer::sum);
        }
        return output;
    }
}
