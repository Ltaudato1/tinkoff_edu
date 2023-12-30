package edu.hw6.task3;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

@FunctionalInterface
public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    default AbstractFilter and(AbstractFilter other) {
        return entry -> accept(entry) && other.accept(entry);
    }

    default AbstractFilter or(AbstractFilter other) {
        return entry -> accept(entry) || other.accept(entry);
    }

    default AbstractFilter negate() {
        return entry -> !accept(entry);
    }

    static AbstractFilter regularFile() {
        return Files::isRegularFile;
    }

    static AbstractFilter readable() {
        return Files::isReadable;
    }

    static AbstractFilter writable() {
        return Files::isWritable;
    }

    static AbstractFilter largerThan(long size) {
        return entry -> {
            try {
                return Files.size(entry) > size;
            } catch (Exception e) {
                return false;
            }
        };
    }

    static AbstractFilter magicNumber(int... magicBytes) {
        return entry -> {
            try (InputStream inputStream = Files.newInputStream(entry)) {
                for (int magicByte : magicBytes) {
                    int readByte = inputStream.read();
                    if (readByte != magicByte) {
                        return false;
                    }
                }
                return true;
            } catch (IOException e) {
                return false;
            }
        };
    }

    static AbstractFilter globMatches(String glob) {
        return entry -> entry.getFileName().toString().matches(glob);
    }

    static AbstractFilter regexContains(String regex) {
        return entry -> entry.toString().contains(regex);
    }
}
