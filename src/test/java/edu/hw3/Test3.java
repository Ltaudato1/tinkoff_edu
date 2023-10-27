package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import static edu.hw3.Task3.freqDict;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Test3 {
    static Stream<Arguments> provideData() {
        ArrayList<String> list1 = new ArrayList<>(Arrays.asList("a", "bb", "a", "bb"));
        ArrayList<String> list2 = new ArrayList<>(Arrays.asList("this", "and", "that", "and"));
        ArrayList<String> list3 = new ArrayList<>(Arrays.asList("код", "код", "код", "bug"));
        ArrayList<Integer> list4 = new ArrayList<>(Arrays.asList(1, 1, 2, 2));
        ArrayList<String> list5 = new ArrayList<>(Arrays.asList(""));

        HashMap<Object, Integer> map1 = new HashMap<>(Map.ofEntries(
            Map.entry("a", 2),
            Map.entry("bb", 2)
        ));
        HashMap<Object, Integer> map2 = new HashMap<>(Map.ofEntries(
            Map.entry("this", 1),
            Map.entry("and", 2),
            Map.entry("that", 1)
        ));
        HashMap<Object, Integer> map3 = new HashMap<>(Map.ofEntries(
            Map.entry("код", 3),
            Map.entry("bug", 1)
        ));
        HashMap<Object, Integer> map4 = new HashMap<>(Map.ofEntries(
            Map.entry(1, 2),
            Map.entry(2, 2)
        ));
        HashMap<Object, Integer> map5 = new HashMap<>(Map.ofEntries(
            Map.entry("", 1)
        ));

        return Stream.of(
            Arguments.of(list1, map1),
            Arguments.of(list2, map2),
            Arguments.of(list3, map3),
            Arguments.of(list4, map4),
            Arguments.of(list5, map5)
        );
    }

    @ParameterizedTest
    @DisplayName("Sample tests")
    @MethodSource("provideData")
    void sampleTest(ArrayList<Object> input, HashMap<Object, Integer> answer) throws Exception {
        HashMap<Object, Integer> result = freqDict(input);
        assertEquals(result, answer);
    }

    @Test
    @DisplayName("Test with null")
    void invalidTest() {
        try {
            ArrayList<Object> input = new ArrayList<>(Arrays.asList((Object) null));
            HashMap<Object, Integer> result = freqDict(input);
            fail();
        } catch (Exception e) {
            return;
        }
    }
}
