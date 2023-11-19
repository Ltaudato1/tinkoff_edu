package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static edu.hw6.Task4.writeInFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test4 {
    @Test
    @DisplayName("sample test")
    void test() throws IOException {
        Path path = Paths.get("src/test/java/edu/hw6/Test4/output.txt");
        writeInFile(path);
        File file = new File(path.toString());
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String answer = reader.readLine();
            assertEquals("Programming is learned by writing programs. ― Brian Kernighan", answer);
        }
    }
}
