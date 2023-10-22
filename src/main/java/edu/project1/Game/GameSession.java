package edu.project1.Game;

import edu.project1.UI;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameSession {
    private GameSession() {

    }

    private static final int MAX_ATTEMPTS = 5;

    public static void run() throws GameIsOverException {
        Game currentSession = new Game();
        Player player = new Player();
        while (!currentSession.gameOver(player)) {
            currentSession.playTurn(player);
        }
        if (currentSession.isWin()) {
            UI.won();
        } else {
            UI.lost();
        }
        currentSession.startAnotherGame(player);
    }

    public static void userInput(Player player) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String input = sc.nextLine().trim();
                if (input.length() > 1) {
                    UI.invalidType();
                    UI.offerToGuess();
                } else {
                    player.setInput(input.charAt(0));
                    break;
                }
            } catch (InputMismatchException e) {
                UI.invalidType();
            }
        }
    }

    public static class Game {
        public WordProvider.HangmanWord hangmanWord;
        HashSet<Character> inputedCharacters = new HashSet<>();
        private boolean win;

        public Game() throws GameIsOverException {
            hangmanWord = new WordProvider.HangmanWord();
            this.win = false;
        }

        public Game(String word) throws GameIsOverException { // для ручного задания слова
            hangmanWord = new WordProvider.HangmanWord();
            hangmanWord.setWord(word);
            this.win = false;
        }

        public void playTurn(Player player) {
            UI.offerToGuess();
            userInput(player);
            if (inputedCharacters.contains(player.getInput())) {
                UI.invalidType();
            } else if (hangmanWord.checkGuess(player.getInput())) {
                UI.correctAnswer();
                hangmanWord.setGuessedLetters(player.getInput());
                inputedCharacters.add(player.getInput());
                hangmanWord.output();
            } else {
                player.useAttempt();
                inputedCharacters.add(player.getInput());
                UI.wrongAnswer(player.getAttemptsUsed(), MAX_ATTEMPTS);
            }
        }

        public boolean gameOver(Player player) {
            if (this.hangmanWord.wordIsGuessed()) {
                this.win = true;
                return true;
            } else if (player.getAttemptsUsed() == MAX_ATTEMPTS) {
                this.win = false;
                return true;
            } else {
                return false;
            }
        }

        public boolean isWin() {
            return this.win;
        }

        public void startAnotherGame(Player player) throws GameIsOverException {
            UI.offerToNewGame();
            do {
                userInput(player);
            } while (player.getInput() != 'y' && player.getInput() != 'n');
            if (player.getInput() == 'n') {
                throw new GameIsOverException("Game is over!");
            }
        }
    }

    public static class Player {
        private int attemptsUsed;
        char input;

        public Player() {
            this.attemptsUsed = 0;
        }

        public char getInput() {
            return input;
        }

        public void setInput(char input) {
            this.input = input;
        }

        public void useAttempt() {
            this.attemptsUsed++;
        }

        public int getAttemptsUsed() {
            return attemptsUsed;
        }
    }

    public static class GameIsOverException extends Exception {
        public GameIsOverException(String message) {
            super(message);
        }
    }

}
