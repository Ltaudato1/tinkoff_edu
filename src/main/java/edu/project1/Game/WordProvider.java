package edu.project1.Game;

import edu.project1.UI;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WordProvider {
    private WordProvider() {

    }

    private static final String INVALID_WORD = "Invalid Word";

    public static class HangmanWord {
        private String word;
        boolean[] guessedLetters;

        public HangmanWord() throws GameSession.GameIsOverException {
            createNewWord();
            if (this.word == null || this.word.isEmpty()) {
                throw new GameSession.GameIsOverException(INVALID_WORD);
            }
            guessedLetters = new boolean[this.word.length()];
        }

        public void output() {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < this.word.length(); ++i) {
                if (guessedLetters[i]) {
                    builder.append(this.word.charAt(i));
                } else {
                    builder.append('*');
                }
            }
            UI.type(builder.toString());
        }

        public void setWord(String word) throws GameSession.GameIsOverException {
            this.word = word;
            if (this.word == null || this.word.isEmpty()) {
                throw new GameSession.GameIsOverException(INVALID_WORD);
            }
            guessedLetters = new boolean[this.word.length()];
        }

        public String getWord() {
            return word;
        }

        public void createNewWord() {
            Random random = new Random();
            WordDictionary dictionary = new WordDictionary();
            this.word = dictionary.getWord(random.nextInt(dictionary.wordMap.size()));
        }

        public boolean checkGuess(char guess) {
            return this.word.contains(Character.toString(guess));
        }

        public void setGuessedLetters(char letter) {
            for (int i = 0; i < this.word.length(); ++i) {
                if (letter == this.word.charAt(i)) {
                    this.guessedLetters[i] = true;
                }
            }
        }

        public boolean wordIsGuessed() {
            for (int i = 0; i < this.word.length(); ++i) {
                if (!guessedLetters[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    private static class WordDictionary {
        private final Map<String, String> wordMap;

        private WordDictionary() {
            wordMap = new HashMap<>();
            wordMap.put("0", "apple");
            wordMap.put("1", "banana");
            wordMap.put("2", "tinkoff");
            wordMap.put("3", "manager");
            wordMap.put("4", "hello");
        }

        private String getWord(Integer id) {
            return wordMap.get(Integer.toString(id));
        }

    }
}
