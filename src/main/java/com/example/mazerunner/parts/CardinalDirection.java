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

    EAST("E", new EastStepper()),
    SOUTH("S", new SouthStepper()),
    NORTH("N", new NorthStepper()),
    WEST("W", new WestStepper()),
    SOUTHWEST("SW", new SouthWestStepper()),
    SOUTHEAST("SE", new SouthEastStepper()),
    NORTHWEST("NW", new NorthWestStepper()),
    NORTHEAST("NE", new NorthEastStepper()),
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
            throw new IllegalArgumentException("You passed in an invalid direction: " + name + " Please enter N, S, E, W, NE, NW, SE, SW.");
        }
        return cardinalDirection;
    }
}
