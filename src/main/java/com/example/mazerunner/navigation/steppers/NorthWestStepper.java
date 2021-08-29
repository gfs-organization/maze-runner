package com.example.mazerunner.navigation.steppers;

import com.example.mazerunner.exceptions.MazeException;
import com.example.mazerunner.exceptions.WallException;
import com.example.mazerunner.parts.Coordinates;
import com.example.mazerunner.parts.Maze;
import com.example.mazerunner.parts.MazeSpace;

public class NorthWestStepper extends AbstractStepper {
    @Override
    public MazeSpace step(final Maze maze, final Coordinates coordinates) throws MazeException {
        final int currentRow = coordinates.getRow();
        final int currentColumn = coordinates.getColumn();

        if (currentRow == 0 || coordinates.getColumn() == 0) {
            throw new WallException("You hit a wall");
        }

        final int newRowIndex = currentRow - 1;
        final int newColumnIndex = currentColumn - 1;
        final char[] newRow = maze.getMazeMap().get(newRowIndex);

        return getMazeSpace(newRow, coordinates, newRowIndex, newColumnIndex);
    }
}
