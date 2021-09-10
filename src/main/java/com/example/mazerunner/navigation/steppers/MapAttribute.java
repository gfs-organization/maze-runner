package com.example.mazerunner.navigation.steppers;

import static com.example.mazerunner.parts.MazeSpace.COPPER_PIECE;
import static com.example.mazerunner.parts.MazeSpace.DOWN_STAIRS;
import static com.example.mazerunner.parts.MazeSpace.EXIT;
import static com.example.mazerunner.parts.MazeSpace.GOLD_PIECE;
import static com.example.mazerunner.parts.MazeSpace.OPEN_SPACE;
import static com.example.mazerunner.parts.MazeSpace.SILVER_PIECE;
import static com.example.mazerunner.parts.MazeSpace.UP_STAIRS;
import static com.example.mazerunner.parts.MazeSpace.WALL;

import java.util.HashMap;
import java.util.Map;

import com.example.mazerunner.parts.MazeSpace;

public enum MapAttribute {

    MAP_WALL('W', WALL),
    MAP_OPEN_SPACE('_', OPEN_SPACE),
    MAP_COPPER_PIECE('C', COPPER_PIECE),
    MAP_SILVER_PIECE('S', SILVER_PIECE),
    MAP_GOLD_PIECE('G', GOLD_PIECE),
    MAP_EXIT('E', EXIT),
    MAP_UP_STAIR('U', UP_STAIRS),
    MAP_DOWN_STAIR('D', DOWN_STAIRS),
    ;

    private final char attribute;
    private final MazeSpace mazeSpace;
    private static final Map<Character, MapAttribute> lookup = new HashMap<>();

    static {
        for (final MapAttribute attribute : MapAttribute.values()) {
            lookup.put(attribute.getAttribute(), attribute);
        }
    }

    MapAttribute(final char attribute, final MazeSpace mazeSpace) {
        this.attribute = attribute;
        this.mazeSpace = mazeSpace;
    }

    public char getAttribute() {
        return this.attribute;
    }

    public MazeSpace getMazeSpace() {
        return this.mazeSpace;
    }

    public static MapAttribute getByAttribute(final char attribute) {
        final MapAttribute attributes = lookup.get(attribute);

        if (attributes == null) {
            final StringBuilder error = new StringBuilder("An error occurred looking up a Map Attribute: ").append(attribute);
            throw new IllegalArgumentException(error.toString());
        }
        return attributes;
    }
}
