package edu.project1.Game;

import edu.project1.UI;
import java.util.Random;

public class HangmanWord {
    private static final String INVALID_WORD = "Invalid Word";
    private String word;
    boolean[] guessedLetters;

    public HangmanWord() throws GameIsOverException {
        createNewWord();
        if (this.word == null || this.word.isEmpty()) {
            throw new GameIsOverException(INVALID_WORD);
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

    public void setWord(String word) throws GameIsOverException {
        this.word = word;
        if (this.word == null || this.word.isEmpty()) {
            throw new GameIsOverException(INVALID_WORD);
        }
        guessedLetters = new boolean[this.word.length()];
    }

    public String getWord() {
        return word;
    }

    public void createNewWord() {
        Random random = new Random();
        this.word = WordDictionary.getWord(random.nextInt(WordDictionary.getWordMapSize()));
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
