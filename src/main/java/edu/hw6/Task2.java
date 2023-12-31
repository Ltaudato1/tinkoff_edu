package edu.hw6;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task2 {
    private Task2() {

    }

    public static void cloneFile(Path path) throws IOException {
        String fileName = path.toString().substring(0, path.toString().lastIndexOf('.'));
        String fileExtension = path.toString().substring(path.toString().lastIndexOf('.'));
        int i = 0;
        String currentFileName = fileName;
        while (new File(currentFileName + fileExtension).exists()) {
            currentFileName = fileName;
            if (i == 0) {
                currentFileName = fileName + " - копия";
                ++i;
            } else {
                currentFileName = fileName + " - копия (" + ++i + ")";
            }
        }
        File file = new File(currentFileName + fileExtension);
        fileToFileCopy(file, new File(fileName + fileExtension));
        file.createNewFile();
    }

    private static void fileToFileCopy(File destination, File source) throws IOException {
        String content = Files.readString(source.toPath(), StandardCharsets.UTF_8);
        Files.writeString(destination.toPath(), content, StandardCharsets.UTF_8);
    }
}
