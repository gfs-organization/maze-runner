package com.example.mazerunner.navigation.steppers;

import static com.example.mazerunner.parts.MazeSpace.OPEN_SPACE;
import static com.example.mazerunner.parts.MazeSpace.WALL;

import com.example.mazerunner.parts.Coordinates;
import com.example.mazerunner.parts.FoundExitException;
import com.example.mazerunner.parts.Maze;
import com.example.mazerunner.parts.MazeSpace;

public abstract class AbstractEastWestStepper extends AbstractStepper {
    public abstract MazeSpace step(final Maze maze, final Coordinates coordinates) throws FoundExitException;

    protected MazeSpace getEastWestMazeSpace(final Maze maze, final Coordinates coordinates, final int newColumn) throws FoundExitException {

        final char[] currentRowCells = maze.getMazeMap().get(coordinates.getRow());

        if (currentRowCells[newColumn] == MAP_WALL) {
            return WALL;
        } else if (currentRowCells[newColumn] == MAP_OPEN_SPACE) {
            coordinates.setColumn(newColumn);
            return OPEN_SPACE;
        } else if (currentRowCells[newColumn] == MAP_EXIT) {
            throw new FoundExitException("You found the exit.");
        }
        return null;
    }
}
