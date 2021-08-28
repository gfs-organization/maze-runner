package com.example.mazerunner.navigation.steppers;

import static com.example.mazerunner.parts.MazeSpace.WALL;

import com.example.mazerunner.parts.Coordinates;
import com.example.mazerunner.parts.FoundExitException;
import com.example.mazerunner.parts.Maze;
import com.example.mazerunner.parts.MazeSpace;

public class SouthWestStepper extends AbstractStepper {
    @Override
    public MazeSpace step(final Maze maze, final Coordinates coordinates) throws FoundExitException {
        final int currentRow = coordinates.getRow();
        final int currentColumn = coordinates.getColumn();

        if (currentRow == maze.getMaxRowIndex() || currentColumn == 0) {
            return WALL;
        }

        final int newRowIndex = currentRow + 1;
        final int newColumnIndex = currentColumn - 1;
        final char[] newRow = maze.getMazeMap().get(newRowIndex);

        return getMazeSpace(newRow, coordinates, newRowIndex, newColumnIndex);
    }
}
