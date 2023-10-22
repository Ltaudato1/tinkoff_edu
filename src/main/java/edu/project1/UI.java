package edu.project1;

import java.util.logging.Logger;

public class UI {
    private UI() {

    }

    private static final Logger LOGGER = Logger.getLogger(UI.class.getName());

    public static void type(String message) {
        LOGGER.info(message);
    }

    public static void thanks() {
        LOGGER.info("Thanks for playing!");
    }

    public static void offerToGuess() {
        LOGGER.warning("Type a letter: ");
    }

    public static void offerToNewGame() {
        LOGGER.warning("Want to try again? (y/n): ");
    }

    public static void invalidType() {
        LOGGER.severe("Something went wrong... Try again");
    }

    public static void correctAnswer() {
        LOGGER.info("That's right!");
    }

    public static void wrongAnswer(int attempts, int maxAttempts) {
        LOGGER.info("It's wrong... Mistake " + attempts + " out of " + maxAttempts);
    }

    public static void won() {
        LOGGER.info("You have won! Congratulations!");
    }

    public static void lost() {
        LOGGER.info("You have lost...");
    }
}
