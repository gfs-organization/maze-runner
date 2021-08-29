package com.example.mazerunner.navigation.steppers;

import static com.example.mazerunner.parts.MazeSpace.OPEN_SPACE;

import com.example.mazerunner.exceptions.FoundExitException;
import com.example.mazerunner.exceptions.MazeException;
import com.example.mazerunner.exceptions.WallException;
import com.example.mazerunner.parts.Coordinates;
import com.example.mazerunner.parts.Maze;
import com.example.mazerunner.parts.MazeSpace;

public abstract class AbstractStepper {

    static final char MAP_WALL = 'W';
    static final char MAP_OPEN_SPACE = '_';
    static final char MAP_EXIT = 'E';

    public abstract MazeSpace step(final Maze maze, final Coordinates coordinates) throws MazeException;

    protected MazeSpace getMazeSpace(final char[] newRow, final Coordinates coordinates, final int newRowIndex, final int newColumnIndex)
            throws MazeException {

        if (newRow[newColumnIndex] == MAP_OPEN_SPACE) {
            coordinates.setRow(newRowIndex);
            coordinates.setColumn(newColumnIndex);
            return OPEN_SPACE;
        } else if (newRow[newColumnIndex] == MAP_WALL) {
            throw new WallException("You hit a wall");
        } else if (newRow[newColumnIndex] == MAP_EXIT) {
            throw new FoundExitException("You found the exit.");
        }
        return null;
    }

}
