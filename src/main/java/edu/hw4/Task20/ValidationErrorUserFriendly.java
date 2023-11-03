package edu.hw4.Task20;

public record ValidationErrorUserFriendly(Type type) {
    public enum Type {
        CAPITALIZATION_ERROR, INVALID_CHARACTERS_ERROR
    }

    public String getStringError() {
        if (type == Type.CAPITALIZATION_ERROR) {
            return "Name must start with upper case\n";
        } else if (type == Type.INVALID_CHARACTERS_ERROR) {
            return "Name must contain only english characters, spaces and -\n";
        } else {
            throw new IllegalArgumentException("Unexpected type");
        }
    }
}
