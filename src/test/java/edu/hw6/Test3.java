package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import static edu.hw6.Task3.AbstractFilter.globMatches;
import static edu.hw6.Task3.AbstractFilter.largerThan;
import static edu.hw6.Task3.AbstractFilter.magicNumber;
import static edu.hw6.Task3.AbstractFilter.readable;
import static edu.hw6.Task3.AbstractFilter.regexContains;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Test3 {
    @Test
    @DisplayName("sample test")
    void test() throws IOException {
        String dir = "src/test/java/edu/hw6/Test3";
        DirectoryStream.Filter<Path> filter = readable()
            .and(largerThan(10))
            .and(magicNumber(0xEF, 0xBB, 0xBF))
            .and(globMatches(".*.txt"))
            .and(regexContains("abac"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of(dir), filter)) {
            assertFalse(entries.iterator().hasNext());
        }
    }
}
