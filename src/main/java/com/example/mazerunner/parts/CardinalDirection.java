package com.example.mazerunner.parts;

public enum CardinalDirection {

    EAST("E"),
    SOUTH("S"),
    NORTH("N"),

    WEST("W"),
    ;

    private final String direction;

    CardinalDirection(final String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}
