package edu.project1;

import edu.project1.Game.GameIsOverException;
import edu.project1.Game.GameSession;

public final class Main {
    private Main() {

    }

    public static void main(String[] args) {
        while (true) {
            try {
                GameSession game = new GameSession();
                game.run();
            } catch (GameIsOverException e) {
                break;
            }
        }
        UI.thanks();
    }
}
