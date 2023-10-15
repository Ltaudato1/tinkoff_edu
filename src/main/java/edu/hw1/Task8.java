package edu.hw1;

public final class Task8 {
    private Task8() {

    }

    private static final int BOARD_SIZE = 8;
    private static final int SHORT_MOVE = 1; // Конь ходит на 1 клетку по одной координате...
    private static final int LONG_MOVE = 2; //  ...и на две по противоположной

    private static boolean canBeSlained(int x, int y, int[][] board) {
        return (x >= 0 && y >= 0 && x < BOARD_SIZE && y < BOARD_SIZE && board[x][y] == 1);
    }

    private static boolean canSlain(int x, int y, int[][] board) {
        return canBeSlained(x + SHORT_MOVE, y + LONG_MOVE, board)
            || canBeSlained(x - SHORT_MOVE, y + LONG_MOVE, board)
            || canBeSlained(x - SHORT_MOVE, y - LONG_MOVE, board)
            || canBeSlained(x + SHORT_MOVE, y - LONG_MOVE, board)
            || canBeSlained(x + LONG_MOVE, y + SHORT_MOVE, board)
            || canBeSlained(x + LONG_MOVE, y - SHORT_MOVE, board)
            || canBeSlained(x - LONG_MOVE, y + SHORT_MOVE, board)
            || canBeSlained(x - LONG_MOVE, y - SHORT_MOVE, board);
    }

    public static boolean knightBoardCapture(int[][] board) {
        if (board.length != BOARD_SIZE) { // Проверка на корректный размер (8х8)
            return false;
        }
        for (int i = 0; i < BOARD_SIZE; ++i) {
            if (board[i].length != BOARD_SIZE) {
                return false;
            }
        }

        for (int i = 0; i < BOARD_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIZE; ++j) {
                if (board[i][j] == 1 && canSlain(i, j, board)) {
                    return false;
                }
            }
        }
        return true;
    }
}
