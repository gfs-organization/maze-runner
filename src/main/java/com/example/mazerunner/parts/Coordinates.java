package com.example.mazerunner.parts;

public class Coordinates {
    private int row;
    private int column;
    private int goldPieces;
    private int silverPieces;
    private int copperPieces;

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

    public int getGoldPieces() {
        return goldPieces;
    }

    public void incrementGold() {
        goldPieces++;
    }

    public int getSilverPieces() {
        return silverPieces;
    }

    public void incrementSilver() {
        silverPieces++;
    }

    public int getCopperPieces() {
        return copperPieces;
    }

    public void incrementCopper() {
        copperPieces++;
    }
}
