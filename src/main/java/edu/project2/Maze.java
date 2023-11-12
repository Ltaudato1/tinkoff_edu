package edu.project2;

import java.util.List;
import java.util.Random;

public class Maze {
    private static int width;
    private static int height;
    private static Cell[][] grid;
    private static char[][] maze;

    public Maze(int rows, int columns) {
        width = columns;
        height = rows;
        grid = createGrid();
        createMaze();
    }

    public char[][] getMaze() {
        return maze;
    }

    private Cell[][] createGrid() {
        Cell[][] mazeGrid = new Cell[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                mazeGrid[y][x] = new Cell(true, true, true, true);
            }
        }
        return mazeGrid;
    }

    public void createMaze() {
        Random random = new Random();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y != 0) {
                    if (random.nextInt(2) == 0) {
                        if (x != width - 1) {
                            grid[y][x].setRightWall(false);
                            grid[y][x + 1].setLeftWall(false);
                        } else {
                            grid[y - 1][x].setBottomWall(false);
                            grid[y][x].setTopWall(false);
                        }
                    } else {
                        grid[y - 1][x].setBottomWall(false);
                        grid[y][x].setTopWall(false);
                    }
                } else {
                    if (x != width - 1) {
                        grid[y][x].setRightWall(false);
                        grid[y][x + 1].setLeftWall(false);
                    }
                }
            }
        }
        setMaze();
    }

    public void setMaze() {
        int rows = height;
        int cols = width;

        maze = new char[2 * rows + 1][2 * cols + 1];

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j] = '█';
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int row = 2 * i + 1;
                int col = 2 * j + 1;
                maze[row][col] = ' ';

                if (!grid[i][j].hasTopWall()) {
                    maze[row - 1][col] = ' ';
                }

                if (!grid[i][j].hasBottomWall()) {
                    maze[row + 1][col] = ' ';
                }

                if (!grid[i][j].hasLeftWall()) {
                    maze[row][col - 1] = ' ';
                }

                if (!grid[i][j].hasRightWall()) {
                    maze[row][col + 1] = ' ';
                }
            }
        }
    }

    public void setMaze(char[][] maze) {
        Maze.maze = maze;
    }

    @SuppressWarnings("RegexpSinglelineJava") void renderMaze(List<Point> solvePath) {
        for (int i = 0; i < maze.length; ++i) {
            for (int j = 0; j < maze[0].length; ++j) {
                if (solvePath.contains(new Point(i, j))) {
                    System.out.print('.');
                } else {
                    System.out.print(maze[i][j]);
                }
            }
            System.out.println();
        }
    }
}
