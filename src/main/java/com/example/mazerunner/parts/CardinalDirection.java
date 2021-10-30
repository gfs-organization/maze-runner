package com.example.mazerunner.parts;

import java.util.HashMap;
import java.util.Map;

public enum CardinalDirection {

    NORTH("N", -1, 0),
    EAST("E", 0, 1),
    WEST("W", 0, -1),
    SOUTH("S", 1, 0),
    NORTHEAST("NE", -1, 1),
    SOUTHEAST("SE", 1, 1),
    SOUTHWEST("SW", 1, -1),
    NORTHWEST("NW", -1, -1),
    UP("U", 0, 0),
    DOWN("D", 0, 0),
    ;

    private final String direction;
    private final int stepRow;
    private final int stepColumn;
    private static final Map<String, CardinalDirection> lookup = new HashMap<>();

    static {
        for (final CardinalDirection direction : CardinalDirection.values()) {
            lookup.put(direction.getDirection(), direction);
        }
    }

    CardinalDirection(final String direction, final int stepRow, final int stepColumn) {
        this.direction = direction;
        this.stepRow = stepRow;
        this.stepColumn = stepColumn;
    }

    public String getDirection() {
        return direction;
    }

    public int getStepRow() {
        return stepRow;
    }

    public int getStepColumn() {
        return stepColumn;
    }

    public static CardinalDirection getByName(final String name) {
        final CardinalDirection cardinalDirection = lookup.get(name);

        if (cardinalDirection == null) {
            throwAnError(name);
        }
        return cardinalDirection;
    }

    private static void throwAnError(final String name) {
        final StringBuilder error = new StringBuilder("You passed in an invalid direction: ").append(name).append(". Please enter ");
        for (final CardinalDirection direction : CardinalDirection.values()) {
            error.append(direction.getDirection()).append(", ");
        }
        error.replace(error.length() - 2, error.length() - 1, ".");
        throw new IllegalArgumentException(error.toString());
    }
}
