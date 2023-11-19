package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public class Task4 {
    private Task4() {

    }

    public static void writeInFile(Path path) throws IOException {
        try (OutputStream fileOutputStream = new FileOutputStream(path.toString());
            CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new CRC32());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedOutputStream);
            PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {

            printWriter.println("Programming is learned by writing programs. ― Brian Kernighan");
        } catch (IOException e) {
            return;
        }
    }
}
