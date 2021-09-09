package com.example.mazerunner.parts;

import java.util.List;

public class Maze {

    private List<char[]> mazeMap;
    private int maxRowIndex;
    private int maxColumnIndex;
    private String mazeTitle;
    private String successMessage;
    private Coordinates stairsUp;
    private Coordinates stairsDown;

    public void setMazeMap(final List<char[]> mazeMap) {
        this.mazeMap = mazeMap;
    }

    public List<char[]> getMazeMap() {
        return mazeMap;
    }

    public int getMaxRowIndex() {
        return maxRowIndex;
    }

    public void setMaxRowIndex(final int maxRowIndex) {
        this.maxRowIndex = maxRowIndex;
    }

    public int getMaxColumnIndex() {
        return maxColumnIndex;
    }

    public void setMaxColumnIndex(final int maxColumnIndex) {
        this.maxColumnIndex = maxColumnIndex;
    }

    public String getMazeTitle() {
        return mazeTitle;
    }

    public void setMazeTitle(final String mazeTitle) {
        this.mazeTitle = mazeTitle;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(final String successMessage) {
        this.successMessage = successMessage;
    }

    public Coordinates getStairsUp() {
        return stairsUp;
    }

    public void setStairsUp(final Coordinates stairsUp) {
        this.stairsUp = stairsUp;
    }

    public Coordinates getStairsDown() {
        return stairsDown;
    }

    public void setStairsDown(final Coordinates stairsDown) {
        this.stairsDown = stairsDown;
    }
}
