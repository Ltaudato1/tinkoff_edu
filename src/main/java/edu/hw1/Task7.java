package edu.hw1;

public final class Task7 {
    private Task7() {

    }

    public static int rotateLeft(int n, int shift) {
        if (shift < 0 || n < 0) {
            return -1;
        }
        int bitsCount = Integer.toBinaryString(n).length();
        int actualShift = shift % bitsCount;
        return (n << actualShift | n >> (bitsCount - actualShift)) & ((1 << bitsCount) - 1);
    }

    public static int rotateRight(int n, int shift) {
        if (shift < 0 || n < 0) {
            return -1;
        }
        int bitsCount = Integer.toBinaryString(n).length();
        int actualShift = shift % bitsCount;
        return (n >> actualShift | n << (bitsCount - actualShift)) & ((1 << bitsCount) - 1);
    }
}
