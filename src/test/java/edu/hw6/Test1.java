package edu.hw6;

import edu.hw6.task1.DiskMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test1 {

    String dir = "src/test/java/edu/hw6/test1";

    @Test
    @DisplayName("test for correct writing")
    void testForWriting() throws IOException {
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

    @Test
    @DisplayName("test for correct reading")
    void testForReading() throws IOException {
        Map<String, String> answer = Map.of(
            "horse", "12",
            "cat", "9",
            "dog", "4"
        );
        DiskMap diskMap = new DiskMap(
            Paths.get(dir, "input.txt").toString(),
            Paths.get(dir, "output.txt").toString()
        );
        assertEquals(answer, diskMap.getInMemoryMap());
    }
}
