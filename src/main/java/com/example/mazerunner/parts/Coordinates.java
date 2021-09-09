package com.example.mazerunner.parts;

public class Coordinates {
    private int row;
    private int column;

    public Coordinates() {
    }

    public Coordinates(final int row, final int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(final int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(final int column) {
        this.column = column;
    }

}
