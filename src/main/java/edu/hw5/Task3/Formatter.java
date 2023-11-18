package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class Formatter {
    protected Formatter nextFormatter;

    public void setNextFormatter(Formatter nextFormatter) {
        this.nextFormatter = nextFormatter;
    }

    public abstract Optional<LocalDate> parse(String input);
}
