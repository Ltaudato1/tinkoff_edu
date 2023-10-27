package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test7 {
    @Test
    @DisplayName("Sample test")
    void test() {
        Comparator<String> nullSafeComparator = new Task7<>(String.CASE_INSENSITIVE_ORDER);
        TreeMap<String, String> map = new TreeMap<>(nullSafeComparator);
        map.put(null, "test");
        map.put("test", null);
        map.put(null, null);
        assertTrue(map.containsKey(null) && map.containsValue(null));
    }
}
