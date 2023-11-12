package edu.project2;

import java.util.ArrayList;
import java.util.List;

public class MazeSolverDFS {
    private final char[][] maze;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private final List<Point> path;
    private final boolean[][] visited;

    public MazeSolverDFS(char[][] maze) {
        this.maze = maze;
        this.startX = 1;
        this.startY = 1;
        this.endX = 1;
        this.endY = 1;
        this.path = new ArrayList<>();
        this.visited = new boolean[maze.length][maze[0].length];
    }

    public List<Point> solveMaze(int startY, int startX, int endY, int endX) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        dfs(startY, startX);
        return path.isEmpty() ? null : path;
    }

    @SuppressWarnings("ReturnCount")
    private void dfs(int y, int x) {
        if (y < 0 || y >= maze.length || x < 0 || x >= maze[0].length || maze[y][x] == '█' || visited[y][x]) {
            return;
        }

        visited[y][x] = true;
        path.add(new Point(y, x));

        if (x == endX && y == endY) {
            return;
        }

        dfs(y + 1, x);
        dfs(y, x + 1);
        dfs(y - 1, x);
        dfs(y, x - 1);

        if (path.get(path.size() - 1).x() != endX || path.get(path.size() - 1).y() != endY) {
            path.remove(path.size() - 1);
        }
    }
}
