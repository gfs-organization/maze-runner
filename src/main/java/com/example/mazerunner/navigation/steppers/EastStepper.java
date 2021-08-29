package com.example.mazerunner.navigation.steppers;

import com.example.mazerunner.exceptions.MazeException;
import com.example.mazerunner.exceptions.WallException;
import com.example.mazerunner.parts.Coordinates;
import com.example.mazerunner.parts.Maze;
import com.example.mazerunner.parts.MazeSpace;

public class EastStepper extends AbstractStepper {
    @Override
    public MazeSpace step(final Maze maze, final Coordinates coordinates) throws MazeException {
        if (coordinates.getColumn() == maze.getMaxColumnIndex()) {
            throw new WallException("You hit a wall");
        }

        final int newColumnIndex = coordinates.getColumn() + 1;
        final int newRowIndex = coordinates.getRow();
        final char[] newRow = maze.getMazeMap().get(newRowIndex);

        return getMazeSpace(newRow, coordinates, newRowIndex, newColumnIndex);
    }
}
