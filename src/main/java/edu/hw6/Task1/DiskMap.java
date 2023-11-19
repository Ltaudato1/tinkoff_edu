package edu.hw6.Task1;

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

public class DiskMap implements Map<String, String>, Serializable {
    @Serial private static final long serialVersionUID = 1L;
    private final Map<String, String> inMemoryMap;
    private final String inputFilePath;
    private final String outputFilePath;
    private static final int KEY_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public DiskMap(String inputFilePath, String outputFilePath) {
        this.outputFilePath = outputFilePath;
        this.inMemoryMap = new HashMap<>();
        this.inputFilePath = inputFilePath;
        loadFromDisk();
    }

    private void loadFromDisk() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line = reader.readLine();
            while (line != null) {
                String[] list = line.split(":");
                inMemoryMap.put(list[KEY_INDEX], list[VALUE_INDEX]);
                line = reader.readLine();
            }
        } catch (IOException e) {
            return;
        }
    }

    public void saveToDisk() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (String key : inMemoryMap.keySet()) {
                writer.write(key + ":" + inMemoryMap.get(key) + "\n");
            }
        } catch (IOException e) {
            return;
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
        saveToDisk();
        return result;
    }

    @Override
    public String remove(Object key) {
        String result = inMemoryMap.remove(key);
        saveToDisk();
        return result;
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        inMemoryMap.putAll(m);
        saveToDisk();
    }

    @Override
    public void clear() {
        inMemoryMap.clear();
        saveToDisk();
    }

    @Override
    public Set<String> keySet() {
        return inMemoryMap.keySet();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return inMemoryMap.entrySet();
    }

    @Override
    public Collection<String> values() {
        return inMemoryMap.values();
    }

    public Map<String, String> getInMemoryMap() {
        return inMemoryMap;
    }
}

