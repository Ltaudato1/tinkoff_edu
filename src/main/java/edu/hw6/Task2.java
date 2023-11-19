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
        String fileName = path.toString();
        String fileExtension = fileName.substring(fileName.lastIndexOf('.'));
        fileName = fileName.substring(0, fileName.lastIndexOf("."));
        int i = 0;
        String currentFileName = fileName;
        while (new File(currentFileName + fileExtension).exists()) {
            currentFileName = fileName;
            if (i == 0) {
                currentFileName = fileName + " - копия";
                ++i;
            } else {
                currentFileName = fileName + " - копия (" + Integer.toString(++i) + ")";
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
