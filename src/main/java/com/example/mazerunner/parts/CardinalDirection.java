package com.example.mazerunner.parts;

import java.util.HashMap;
import java.util.Map;

public enum CardinalDirection {

    EAST("E"),
    SOUTH("S"),
    NORTH("N"),

    WEST("W"),
    ;

    private final String direction;
    private static final Map<String, CardinalDirection> lookup = new HashMap<>();

    static {
        for (final CardinalDirection direction : CardinalDirection.values()) {
            lookup.put(direction.getDirection(), direction);
        }
    }

    CardinalDirection(final String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public static CardinalDirection getByName(final String name) {
        return lookup.get(name);
    }
}
