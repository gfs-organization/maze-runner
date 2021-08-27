package com.example.mazerunner.navigation.steppers;

import static com.example.mazerunner.parts.MazeSpace.WALL;

import com.example.mazerunner.parts.Coordinates;
import com.example.mazerunner.parts.FoundExitException;
import com.example.mazerunner.parts.Maze;
import com.example.mazerunner.parts.MazeSpace;

public class NorthStepper extends AbstractNorthSouthStepper {
    @Override
    public MazeSpace step(final Maze maze, final Coordinates coordinates) throws FoundExitException {
        final int currentRow = coordinates.getRow();
        if (currentRow == 0) {
            return WALL; // do not change coordinates
        }

        final int newRowIndex = currentRow - 1;
        return getNorthSouthMazeSpace(maze, coordinates, newRowIndex);
    }
}
