package com.example.mazerunner.parts;

public enum MazeSpace {
    WALL("W", "Wall"),
    OPEN_SPACE("_", "Open Space"),
    EXIT("E", "Exit"),
    ;

    private final String shortDescription;
    private final String longDescription;

    MazeSpace(final String shortDescription, final String longDescription) {

        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }
}
