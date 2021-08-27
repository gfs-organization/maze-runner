package com.example.mazerunner.navigation.steppers;

import static com.example.mazerunner.parts.MazeSpace.OPEN_SPACE;
import static com.example.mazerunner.parts.MazeSpace.WALL;

import com.example.mazerunner.parts.Coordinates;
import com.example.mazerunner.parts.FoundExitException;
import com.example.mazerunner.parts.Maze;
import com.example.mazerunner.parts.MazeSpace;

public abstract class AbstractNorthSouthStepper extends AbstractStepper {

    @Override
    public abstract MazeSpace step(final Maze maze, final Coordinates coordinates) throws FoundExitException;

    protected MazeSpace getNorthSouthMazeSpace(final Maze maze, final Coordinates coordinates, final int newRowIndex) throws FoundExitException {
        final int currentColumn = coordinates.getColumn();
        final char[] newRow = maze.getMazeMap().get(newRowIndex);

        if (newRow[currentColumn] == MAP_OPEN_SPACE) {
            coordinates.setRow(newRowIndex);
            return OPEN_SPACE;
        } else if (newRow[currentColumn] == MAP_WALL) {
            return WALL;
        } else if (newRow[currentColumn] == MAP_EXIT) {
            throw new FoundExitException("You found the exit.");
        }
        return null;
    }
}
