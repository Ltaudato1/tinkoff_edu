package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class DiskMap implements Map<String, String>, Serializable {
    @Serial private static final long serialVersionUID = 1L;
    private final Map<String, String> inMemoryMap;
    private final String inputFilePath;
    private final String outputFilePath;
    private static final int KEY_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public DiskMap(String inputFilePath, String outputFilePath) throws IOException {
        this.outputFilePath = outputFilePath;
        this.inMemoryMap = new HashMap<>();
        this.inputFilePath = inputFilePath;
        loadFromDisk();
    }

    private void loadFromDisk() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line = reader.readLine();
            while (line != null) {
                String[] list = line.split(":");
                inMemoryMap.put(list[KEY_INDEX], list[VALUE_INDEX]);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new IOException("Failed to read info from disk", e);
        }
    }

    public void saveToDisk() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (Entry<String, String> key : inMemoryMap.entrySet()) {
                writer.write(key.getKey() + ":" + key.getValue() + "\n");
            }
        } catch (IOException e) {
            throw new IOException("Failed to save to disk", e);
        }
    }

    @Override
    public int size() {
        return inMemoryMap.size();
    }

    @Override
    public boolean isEmpty() {
        return inMemoryMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return inMemoryMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return inMemoryMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return inMemoryMap.get(key);
    }

    @Override
    public String put(String key, String value) {
        String result = inMemoryMap.put(key, value);
        try {
            saveToDisk();
        } catch (IOException e) {
            throw new RuntimeException("Failed to put info in map", e);
        }
        return result;
    }

    @Override
    public String remove(Object key) {
        String result = inMemoryMap.remove(key);
        try {
            saveToDisk();
        } catch (IOException e) {
            throw new RuntimeException("Failed to remove info from map", e);
        }
        return result;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        inMemoryMap.putAll(m);
        try {
            saveToDisk();
        } catch (IOException e) {
            throw new RuntimeException("Failed to rewrite the map", e);
        }
    }

    @Override
    public void clear() {
        inMemoryMap.clear();
        try {
            saveToDisk();
        } catch (IOException e) {
            throw new RuntimeException("Failed to clear the map", e);
        }
    }

    @Override
    public @NotNull Set<String> keySet() {
        return inMemoryMap.keySet();
    }

    @Override
    public @NotNull Set<Entry<String, String>> entrySet() {
        return inMemoryMap.entrySet();
    }

    @Override
    public @NotNull Collection<String> values() {
        return inMemoryMap.values();
    }

    public Map<String, String> getInMemoryMap() {
        return inMemoryMap;
    }
}

