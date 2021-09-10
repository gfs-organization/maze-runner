package com.example.mazerunner.navigation.steppers;

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

        final MapAttribute attribute = MapAttribute.getByAttribute(newRow[newColumnIndex]);
        switch (attribute) {
            case MAP_WALL:
                throw new WallException("You hit a wall");
            case MAP_EXIT:
                throw new FoundExitException("You found the exit.");
            case MAP_COPPER_PIECE:
                coordinates.incrementCopper();
                break;
            case MAP_SILVER_PIECE:
                coordinates.incrementSilver();
                break;
            case MAP_GOLD_PIECE:
                coordinates.incrementGold();
                break;
        }
        coordinates.setRow(newRowIndex);
        coordinates.setColumn(newColumnIndex);
        return attribute.getMazeSpace();
    }
}
