package edu.hw1;

import java.util.Arrays;

public final class Task3 {
    private Task3() {

    }

    public static boolean isNestable(long[] internal, long[] external) {
        if (internal == null || internal.length == 0 || external.length == 0 || external == null) {
            return false;
        }
        long internalMax = Arrays.stream(internal).max().getAsLong(); // Получаем минимумы и максимумы
        long internalMin = Arrays.stream(internal).min().getAsLong();
        long externalMax = Arrays.stream(external).max().getAsLong();
        long externalMin = Arrays.stream(external).min().getAsLong();

        return (internalMin > externalMin) && (internalMax < externalMax);
    }
}
