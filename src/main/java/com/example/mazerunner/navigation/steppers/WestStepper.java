package com.example.mazerunner.navigation.steppers;

import static com.example.mazerunner.parts.MazeSpace.WALL;

import com.example.mazerunner.parts.Coordinates;
import com.example.mazerunner.parts.FoundExitException;
import com.example.mazerunner.parts.Maze;
import com.example.mazerunner.parts.MazeSpace;

public class WestStepper extends AbstractStepper {
    @Override
    public MazeSpace step(final Maze maze, final Coordinates coordinates) throws FoundExitException {
        if (coordinates.getColumn() == 0) {
            return WALL;
        }

        final int newColumnIndex = coordinates.getColumn() - 1;
        final int newRowIndex = coordinates.getRow();
        final char[] newRow = maze.getMazeMap().get(newRowIndex);

        return getMazeSpace(newRow, coordinates, newRowIndex, newColumnIndex);
    }
}
