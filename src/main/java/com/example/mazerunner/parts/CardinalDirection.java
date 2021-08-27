package com.example.mazerunner.parts;

import java.util.HashMap;
import java.util.Map;

import com.example.mazerunner.navigation.AbstractStepper;
import com.example.mazerunner.navigation.EastStepper;
import com.example.mazerunner.navigation.NorthStepper;
import com.example.mazerunner.navigation.SouthStepper;
import com.example.mazerunner.navigation.WestStepper;

public enum CardinalDirection {

    EAST("E", new EastStepper()),
    SOUTH("S", new SouthStepper()),
    NORTH("N", new NorthStepper()),
    WEST("W", new WestStepper()),
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
        return lookup.get(name);
    }
}
