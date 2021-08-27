package com.example.mazerunner.navigation;

import static com.example.mazerunner.parts.MazeSpace.WALL;

import com.example.mazerunner.parts.Coordinates;
import com.example.mazerunner.parts.FoundExitException;
import com.example.mazerunner.parts.Maze;
import com.example.mazerunner.parts.MazeSpace;

public class WestStepper extends AbstractEastWestStepper {
    @Override
    public MazeSpace step(final Maze maze, final Coordinates coordinates) throws FoundExitException {
        if (coordinates.getColumn() == 0) {
            return WALL;
        }

        final int newColumn = coordinates.getColumn() - 1;
        return getEastWestMazeSpace(maze, coordinates, newColumn);
    }
}
