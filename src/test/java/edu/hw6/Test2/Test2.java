package edu.hw6.Test2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import static edu.hw6.Task2.cloneFile;
import static org.junit.jupiter.api.Assertions.fail;

public class Test2 {
    @Test
    @DisplayName("Sample test")
    void test() throws IOException {
        String dir = "C:\\Users\\Александр\\Desktop\\репозиторий java\\tinkoff_edu\\src\\test\\java\\edu\\hw6\\Test2";
        String name = "The_Real_Tinkoff_Bank_Biggest_Secret";
        for (int i = 0; i < 4; ++i) {
            cloneFile(Paths.get(dir, (name + ".txt")));
        }

        for (int i = 0; i < 4; ++i) {
            String fileName;
            if (i == 0) {
                fileName = name + " - копия.txt";
            }
            else {
                fileName = name + " - копия (" + Integer.toString(i+1) + ").txt";
            }
            File file = new File(Paths.get(dir, fileName).toString());
            if (!file.exists()) {
                fail();
            }
            else {
                file.delete();
            }

        }
    }
}
