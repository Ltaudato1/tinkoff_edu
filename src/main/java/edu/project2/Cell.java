package edu.project2;

public class Cell {
    private boolean bottomWall;
    private boolean rightWall;
    private boolean leftWall;
    private boolean topWall;

    public Cell(boolean bottomWall, boolean rightWall, boolean leftWall, boolean topWall) {
        this.bottomWall = bottomWall;
        this.rightWall = rightWall;
        this.leftWall = leftWall;
        this.topWall = topWall;
    }

    public boolean hasBottomWall() {
        return bottomWall;
    }

    public boolean hasRightWall() {
        return rightWall;
    }

    public boolean hasLeftWall() {
        return leftWall;
    }

    public boolean hasTopWall() {
        return topWall;
    }

    public void setBottomWall(boolean bottomWall) {
        this.bottomWall = bottomWall;
    }

    public void setRightWall(boolean rightWall) {
        this.rightWall = rightWall;
    }

    public void setTopWall(boolean topWall) {
        this.topWall = topWall;
    }

    public void setLeftWall(boolean leftWall) {
        this.leftWall = leftWall;
    }
}
