package edu.hw1;

import edu.project1.Main;
import java.util.logging.Logger;

public final class Task0 {
    private Task0() {

    }

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void printHelloWorld() {
        LOGGER.info("Hello world!");
    }
}
