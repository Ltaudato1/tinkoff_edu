package edu.hw6.Test1;

import edu.hw6.Task1.DiskMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test1 {

    @Test
    @DisplayName("test for correct writing")
    void test() throws IOException {
        String dir = "C:\\Users\\Александр\\Desktop\\репозиторий java\\tinkoff_edu\\src\\test\\java\\edu\\hw6\\Test1";
        DiskMap diskMap1 = new DiskMap(
            Paths.get(dir, "input.txt").toString(),
            Paths.get(dir, "output.txt").toString()
        );
        diskMap1.saveToDisk();
        DiskMap diskMap2 = new DiskMap(
            Paths.get(dir, "output.txt").toString(),
            Paths.get(dir, "input.txt").toString()
        );
        assertEquals(diskMap1.getInMemoryMap(), diskMap2.getInMemoryMap());
    }

    static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of(Map.ofEntries(
                Map.entry("horse", "12"),
                Map.entry("cat", "9"),
                Map.entry("dog", "4")
            ))
        );
    }
    @ParameterizedTest
    @MethodSource("provideData")
    @DisplayName("test for correct reading")
    void testReading(Map<String, String> answer) throws IOException {
        String dir = "C:\\Users\\Александр\\Desktop\\репозиторий java\\tinkoff_edu\\src\\test\\java\\edu\\hw6\\Test1";
        DiskMap diskMap = new DiskMap(
            Paths.get(dir, "input.txt").toString(),
            Paths.get(dir, "output.txt").toString()
        );
        assertEquals(answer, diskMap.getInMemoryMap());
    }
}
