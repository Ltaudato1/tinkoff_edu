package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task6.scanPorts;

public class Test6 {
    @Test
    @DisplayName("sample test")
    void test() {
        scanPorts(0, 49151);
    }
}
