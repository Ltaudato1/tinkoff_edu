package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TestProject2 {

    @Test
    @DisplayName("Sample Test for rendering (binary tree algorithm)")
    void testRender() {
        Maze maze = new Maze(10, 10);
        try {
            maze.renderMaze(emptyList());
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    @DisplayName("Sample test for solving (DFS algorithm)")
    void testSolve() {
        Maze maze = new Maze(5, 5);
        MazeSolverDFS solver = new MazeSolverDFS(maze.getMaze());
        int startX = 9;
        int startY = 1;
        int endX = 1;
        int endY = 9;
        List<Point> solvePath = solver.solveMaze(startY, startX, endY, endX);
        try {
            maze.renderMaze(solvePath);
        } catch (Exception e) {
            fail();
        }
        char[][] mazeAsChars = maze.getMaze();
        for (Point point : solvePath) {
            if (mazeAsChars[point.y()][point.x()] == '█') {
                fail();
            }
        }
        assertTrue(solvePath.get(0).y() == startY
        && solvePath.get(0).x() == startX
        && solvePath.get(solvePath.size()-1).y() == endY
        && solvePath.get(solvePath.size()-1).x() == endX);
    }

    @Test
    @DisplayName("Test for correct rendering and solving labyrinth")
    void testForKnownLabyrinth() {
        Maze maze = new Maze(1, 1);
        char[][] mazeAsChars = {
            {'█', '█', '█', '█', '█', '█', '█'},
            {'█', ' ', ' ', ' ', ' ', ' ', '█'},
            {'█', '█', '█', '█', '█', ' ', '█'},
            {'█', ' ', ' ', ' ', ' ', ' ', '█'},
            {'█', '█', '█', ' ', '█', ' ', '█'},
            {'█', ' ', ' ', ' ', '█', ' ', '█'},
            {'█', '█', '█', '█', '█', '█', '█'}
        };
        List<Point> rigthSolvePath = List.of(
            new Point(1, 1),
            new Point(1, 2),
            new Point(1, 3),
            new Point(1, 4),
            new Point(1, 5),
            new Point(2, 5)
        );

        maze.setMaze(mazeAsChars);
        MazeSolverDFS solver = new MazeSolverDFS(mazeAsChars);
        List<Point> actualSolvePath = solver.solveMaze(1, 1, 2, 5);
        try {
            maze.renderMaze(actualSolvePath);
            assertEquals(rigthSolvePath, actualSolvePath);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @DisplayName("Test for rendering invalid labyrinth")
    void testInvalidLabyrinth() {
        try {
            Maze maze = new Maze(-1, -1);
            maze.renderMaze(emptyList());
            fail();
        } catch(Exception e) {
            return;
        }
    }
}
