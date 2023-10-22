package edu.project1;

import edu.project1.Game.GameSession;

public final class Main {
    private Main() {

    }

    public static void main(String[] args) {
        while (true) {
            try {
                GameSession.run();
            } catch (GameSession.GameIsOverException e) {
                break;
            }
        }
        UI.thanks();
    }
}
