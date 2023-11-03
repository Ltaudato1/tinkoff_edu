package edu.hw4.Task19;

public record ValidationError(Type type, String message) {
    public enum Type {
        CAPITALIZATION_ERROR, INVALID_CHARACTERS_ERROR
    }
}
