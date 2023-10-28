package edu.project1.Game;

public class Player {
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
