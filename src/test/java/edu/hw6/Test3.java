package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import static edu.hw6.task3.AbstractFilter.globMatches;
import static edu.hw6.task3.AbstractFilter.largerThan;
import static edu.hw6.task3.AbstractFilter.magicNumber;
import static edu.hw6.task3.AbstractFilter.readable;
import static edu.hw6.task3.AbstractFilter.regexContains;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test3 {
    @Test
    @DisplayName("sample test")
    void test() throws IOException {
        String dir = "src\\test\\java\\edu\\hw6\\test3";

        // создадим файл который должен проходить тест
        String filePath = dir + "\\file_with_bom.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Добавляем магическое число для UTF-8
            writer.write("\uFEFF");
            // Записываем контент файла
            writer.write("THIS FILE WILL PASS THE TEST abac");
        } catch (IOException e) {
            e.printStackTrace();
        }


        DirectoryStream.Filter<Path> filter = readable()
            .and(largerThan(10))
            .and(magicNumber(0xEF, 0xBB, 0xBF))
            .and(globMatches(".*.txt"))
            .and(regexContains("file_with_bom"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of(dir), filter)) {
            for (Path path : entries) {
                assertEquals(path.toString(), dir + "\\file_with_bom.txt");
            }
        }
    }
}
