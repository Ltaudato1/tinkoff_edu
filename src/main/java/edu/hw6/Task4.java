package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public class Task4 {
    private Task4() {

    }

    public static void writeInFile(Path path) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(
            new OutputStreamWriter(
                new BufferedOutputStream(
                    new CheckedOutputStream(
                        new FileOutputStream(
                            path.toString()), new CRC32()))))) {

            printWriter.println("Programming is learned by writing programs. ― Brian Kernighan");
        } catch (IOException e) {
            return;
        }
    }
}
