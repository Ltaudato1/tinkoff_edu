package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import static edu.hw3.Task5.parseContacts;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test5 {
    static Stream<Arguments> provideData() {
        ArrayList<String> list1 = new ArrayList<>(Arrays.asList("John Doe", "Alex Smith", "Bob Johnson"));
        ArrayList<String> list2 = new ArrayList<>(Arrays.asList("Paul Erdos", "Leonhard Euler", "Carl Gauss"));
        ArrayList<String> list3 = new ArrayList<>(Arrays.asList("Alex Smith", "Jane Doe", "Bob Johnson", "Tom", "Jerry"));
        ArrayList<String> list4 = new ArrayList<>(Arrays.asList(""));
        ArrayList<String> list5 = new ArrayList<>(Arrays.asList("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"));

        ArrayList<Object> answer1 = new ArrayList<>(Arrays.asList(new Task5.Contact("John Doe"), new Task5.Contact("Bob Johnson"), new Task5.Contact("Alex Smith")));
        ArrayList<Object> answer2 = new ArrayList<>(Arrays.asList(new Task5.Contact("Carl Gauss"), new Task5.Contact("Leonhard Euler"), new Task5.Contact("Paul Erdos")));
        ArrayList<Object> answer3 = new ArrayList<>(Arrays.asList(new Task5.Contact("Jane Doe"), new Task5.Contact("Jerry"), new Task5.Contact("Bob Johnson"), new Task5.Contact("Alex Smith"), new Task5.Contact("Tom")));
        ArrayList<Object> answer4 = new ArrayList<>(Arrays.asList(new Task5.Contact("")));
        ArrayList<Object> answer5 = new ArrayList<>(Arrays.asList(new Task5.Contact("Thomas Aquinas"), new Task5.Contact("Rene Descartes"), new Task5.Contact("David Hume"), new Task5.Contact("John Locke")));
        return Stream.of(
            Arguments.of(list1, "ASC", answer1),
            Arguments.of(list2, "DESC", answer2),
            Arguments.of(list3, "ASC", answer3),
            Arguments.of(list4, "ASC", answer4),
            Arguments.of(null, "DESC", null),
            Arguments.of(list5, "ASC", answer5)
        );
    }

    @ParameterizedTest
    @MethodSource("provideData")
    void test(ArrayList<String> listInput, String typeInput, ArrayList<Object> answer) {
        ArrayList<Task5.Contact> result = parseContacts(listInput, typeInput);
        assertEquals(result, answer);
    }
}
