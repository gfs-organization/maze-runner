package com.example.mazerunner.navigation.steppers;

import static com.example.mazerunner.parts.MazeSpace.OPEN_SPACE;
import static com.example.mazerunner.parts.MazeSpace.WALL;

import com.example.mazerunner.parts.Coordinates;
import com.example.mazerunner.parts.FoundExitException;
import com.example.mazerunner.parts.Maze;
import com.example.mazerunner.parts.MazeSpace;

public abstract class AbstractStepper {

    static final char MAP_WALL = 'W';
    static final char MAP_OPEN_SPACE = '_';
    static final char MAP_EXIT = 'E';

    public abstract MazeSpace step(final Maze maze, final Coordinates coordinates) throws FoundExitException;

    protected MazeSpace getMazeSpace(final char[] newRow, final Coordinates coordinates, final int newRowIndex, final int newColumnIndex)
            throws FoundExitException {

        if (newRow[newColumnIndex] == MAP_OPEN_SPACE) {
            coordinates.setRow(newRowIndex);
            coordinates.setColumn(newColumnIndex);
            return OPEN_SPACE;
        } else if (newRow[newColumnIndex] == MAP_WALL) {
            return WALL;
        } else if (newRow[newColumnIndex] == MAP_EXIT) {
            throw new FoundExitException("You found the exit.");
        }
        return null;
    }

}
