package edu.project1.Game;

import edu.project1.UI;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

@SuppressWarnings("HideUtilityClassConstructor")

public class GameSession {
    private static final int MAX_ATTEMPTS = 5;
    public static HangmanWord hangmanWord;
    static HashSet<Character> inputedCharacters = new HashSet<>();
    private static boolean win = false;

    public GameSession() throws GameIsOverException {
        hangmanWord = new HangmanWord();
    }

    public GameSession(String word) throws GameIsOverException {
        if (word == null) {
            throw new GameIsOverException("Invalid word");
        }
        hangmanWord = new HangmanWord();
        hangmanWord.setWord(word);
    }


    public static void playTurn(Player player) {
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

    public static boolean gameOver(Player player) {
        if (hangmanWord.wordIsGuessed()) {
            win = true;
            return true;
        } else if (player.getAttemptsUsed() == MAX_ATTEMPTS) {
            win = false;
            return true;
        } else {
            return false;
        }
    }

    public static boolean isWin() {
        return win;
    }

    public static void startAnotherGame(Player player) throws GameIsOverException {
        UI.offerToNewGame();
        do {
            userInput(player);
        } while (player.getInput() != 'y' && player.getInput() != 'n');
        if (player.getInput() == 'n') {
            throw new GameIsOverException("Game is over!");
        }
    }

    public static void run() throws GameIsOverException {
        Player player = new Player();
        while (!gameOver(player)) {
            playTurn(player);
        }
        if (isWin()) {
            UI.won();
        } else {
            UI.lost();
        }
        startAnotherGame(player);
    }

    public static void userInput(Player player) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String input = sc.nextLine();
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
}
