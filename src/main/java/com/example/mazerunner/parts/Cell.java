package com.example.mazerunner.parts;

public class Cell {
    private int x;
    private int y;
    private boolean open;
    private boolean exit;
    private boolean wall;

    public Cell(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(final boolean open) {
        this.open = open;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(final boolean exit) {
        this.exit = exit;
    }

    public boolean isWall() {
        return wall;
    }

    public void setWall(final boolean wall) {
        this.wall = wall;
    }

    public boolean isUnknown() {
        return !open && !wall && !exit;
    }
}
