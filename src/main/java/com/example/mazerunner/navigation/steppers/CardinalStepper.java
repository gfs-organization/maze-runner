package com.example.mazerunner.navigation.steppers;

import static com.example.mazerunner.parts.MazeSpace.OPEN_SPACE;
import static com.example.mazerunner.parts.MazeSpace.UP_STAIRS;

import org.springframework.stereotype.Component;

import com.example.mazerunner.exceptions.FoundExitException;
import com.example.mazerunner.exceptions.MazeException;
import com.example.mazerunner.exceptions.WallException;
import com.example.mazerunner.parts.CardinalDirection;
import com.example.mazerunner.parts.Coordinates;
import com.example.mazerunner.parts.Maze;
import com.example.mazerunner.parts.MazeSpace;

@Component
public class CardinalStepper {

    static final char MAP_WALL = 'W';
    static final char MAP_OPEN_SPACE = '_';
    static final char MAP_EXIT = 'E';
    static final char MAP_UP_STAIR = 'U';
    static final char MAP_DOWN_STAIR = 'D';

    public MazeSpace doStep(final Maze maze, final CardinalDirection cardinalDirection, final Coordinates coordinates) throws MazeException {

        final int newRowIndex = coordinates.getRow() + cardinalDirection.getStepRow();
        final int newColumnIndex = coordinates.getColumn() + cardinalDirection.getStepColumn();

        checkIfSteppingOutsideMaze(maze, newRowIndex, newColumnIndex);

        final char[] newRow = maze.getMazeMap().get(newRowIndex);

        return getMazeSpace(newRow, coordinates, newRowIndex, newColumnIndex);
    }

    private void checkIfSteppingOutsideMaze(final Maze maze, final int newRowIndex, final int newColumnIndex) throws WallException {
        if (newRowIndex < 0 || newRowIndex > maze.getMaxRowIndex() || newColumnIndex < 0 || newColumnIndex > maze.getMaxColumnIndex()) {
            throw new WallException("You hit a wall.");
        }
    }

    private MazeSpace getMazeSpace(final char[] newRow, final Coordinates coordinates, final int newRowIndex, final int newColumnIndex)
            throws MazeException {

        final char nextCell = newRow[newColumnIndex];
        if (nextCell == MAP_OPEN_SPACE) {
            coordinates.setRow(newRowIndex);
            coordinates.setColumn(newColumnIndex);
            return OPEN_SPACE;
        } else if (nextCell == MAP_UP_STAIR) {
            coordinates.setRow(newRowIndex);
            coordinates.setColumn(newColumnIndex);
            return UP_STAIRS;
        } else if (nextCell == MAP_DOWN_STAIR) {
            coordinates.setRow(newRowIndex);
            coordinates.setColumn(newColumnIndex);
            return MazeSpace.DOWN_STAIRS;
        } else if (nextCell == MAP_WALL) {
            throw new WallException("You hit a wall");
        } else if (nextCell == MAP_EXIT) {
            throw new FoundExitException("You found the exit.");
        }
        return null;
    }
}
