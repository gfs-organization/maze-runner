package com.example.mazerunner.parts;

import java.util.HashMap;
import java.util.Map;

import com.example.mazerunner.navigation.steppers.AbstractStepper;
import com.example.mazerunner.navigation.steppers.EastStepper;
import com.example.mazerunner.navigation.steppers.NorthEastStepper;
import com.example.mazerunner.navigation.steppers.NorthStepper;
import com.example.mazerunner.navigation.steppers.NorthWestStepper;
import com.example.mazerunner.navigation.steppers.SouthEastStepper;
import com.example.mazerunner.navigation.steppers.SouthStepper;
import com.example.mazerunner.navigation.steppers.SouthWestStepper;
import com.example.mazerunner.navigation.steppers.WestStepper;

public enum CardinalDirection {

    NORTH("N", new NorthStepper()),
    EAST("E", new EastStepper()),
    WEST("W", new WestStepper()),
    SOUTH("S", new SouthStepper()),
    NORTHEAST("NE", new NorthEastStepper()),
    SOUTHEAST("SE", new SouthEastStepper()),
    SOUTHWEST("SW", new SouthWestStepper()),
    NORTHWEST("NW", new NorthWestStepper()),
    ;

    private final String direction;
    private final AbstractStepper stepper;
    private static final Map<String, CardinalDirection> lookup = new HashMap<>();

    static {
        for (final CardinalDirection direction : CardinalDirection.values()) {
            lookup.put(direction.getDirection(), direction);
        }
    }

    CardinalDirection(final String direction, final AbstractStepper stepper) {
        this.direction = direction;
        this.stepper = stepper;
    }

    public String getDirection() {
        return direction;
    }

    public AbstractStepper getStepper() {
        return stepper;
    }

    public static CardinalDirection getByName(final String name) {
        final CardinalDirection cardinalDirection = lookup.get(name);
        
        if (cardinalDirection == null) {
            final StringBuilder error = new StringBuilder("You passed in an invalid direction: ").append(name).append(". Please enter ");
            for (final CardinalDirection direction : CardinalDirection.values()) {
                error.append(direction.getDirection()).append(", ");
            }
            error.replace(error.length() - 2, error.length() - 1, ".");
            throw new IllegalArgumentException(error.toString());
        }
        return cardinalDirection;
    }
}
