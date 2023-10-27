package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static edu.hw3.Task1.atbash;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test1 {

    static Stream<Arguments> provideStringsForTest() {
        return Stream.of(
            Arguments.of("Hello, world!", "Svool, dliow!"),
            Arguments.of("Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler",
                "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"),
            Arguments.of("123!@#", "123!@#"),
            Arguments.of("", ""),
            Arguments.of("AbCdEfGhIjKlMnOpQrStUvWxYz", "ZyXwVuTsRqPoNmLkJiHgFeDcBa"),
            Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForTest")
    void yourTest(String input, String answer) {
        String output = atbash(input);
        assertEquals(output, answer);
    }
}
