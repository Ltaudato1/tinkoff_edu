package edu.project1.Game;

import java.util.HashMap;
import java.util.Map;

public class WordDictionary {
    private static final HashMap<String, String> WORD_MAP = new HashMap<>(Map.ofEntries(
        Map.entry("0", "apple"),
        Map.entry("1", "banana"),
        Map.entry("2", "tinkoff"),
        Map.entry("3", "manager"),
        Map.entry("4", "hello")
    ));

    public static int getWordMapSize() {
        return WORD_MAP.size();
    }

    private WordDictionary() {

    }

    public static String getWord(Integer id) {
        return WORD_MAP.get(Integer.toString(id));
    }

}
