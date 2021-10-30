package com.example.mazerunner.navigation.steppers;

import static com.example.mazerunner.parts.CardinalDirection.DOWN;
import static com.example.mazerunner.parts.CardinalDirection.UP;
import static com.example.mazerunner.parts.MazeSpace.DOWN_STAIRS;
import static com.example.mazerunner.parts.MazeSpace.UP_STAIRS;

import org.springframework.stereotype.Component;

import com.example.mazerunner.exceptions.MazeException;
import com.example.mazerunner.exceptions.WallException;
import com.example.mazerunner.parts.CardinalDirection;
import com.example.mazerunner.parts.Coordinates;
import com.example.mazerunner.parts.Maze;
import com.example.mazerunner.parts.SpriteState;

@Component
public class CardinalStepper {

    public void doStep(final SpriteState spriteState, final CardinalDirection cardinalDirection) throws MazeException {
        checkForWalkingStairs(spriteState, cardinalDirection);

        final int newRowIndex = spriteState.getCoordinates().getRow() + cardinalDirection.getStepRow();
        final int newColumnIndex = spriteState.getCoordinates().getColumn() + cardinalDirection.getStepColumn();
        final Maze currentMaze = spriteState.getCurrentMaze();

        checkIfSteppingOutsideMaze(currentMaze, newRowIndex, newColumnIndex);
        assignMazeSpace(spriteState, newRowIndex, newColumnIndex);
    }

    private void checkForWalkingStairs(final SpriteState spriteState, final CardinalDirection cardinalDirection) {
        if (spriteState.getCurrentSpace() == UP_STAIRS && cardinalDirection == UP) {
            spriteState.incrementFloor();
            final Coordinates stairsDown = spriteState.getCurrentMaze().getStairsDown();
            spriteState.resetCoordinates(stairsDown.getRow(), stairsDown.getColumn());
            System.out.println("Went upstairs to the " + spriteState.getCurrentMaze().getMazeTitle());
        } else if (spriteState.getCurrentSpace() == DOWN_STAIRS && cardinalDirection == DOWN) {
            spriteState.decrementFloor();
            final Coordinates stairsUp = spriteState.getCurrentMaze().getStairsUp();
            spriteState.resetCoordinates(stairsUp.getRow(), stairsUp.getColumn());
            System.out.println("Went downstairs to the " + spriteState.getCurrentMaze().getMazeTitle());
        }
    }

    private void checkIfSteppingOutsideMaze(final Maze maze, final int newRowIndex, final int newColumnIndex) throws WallException {
        if (newRowIndex < 0 || newRowIndex > maze.getMaxRowIndex() || newColumnIndex < 0 || newColumnIndex > maze.getMaxColumnIndex()) {
            throw new WallException("You hit a wall.");
        }
    }

    private void assignMazeSpace(final SpriteState spriteState, final int newRowIndex, final int newColumnIndex) throws MazeException {

        final char[] newRow = spriteState.getCurrentMaze().getMazeMap().get(newRowIndex);

        final MapAttribute attribute = MapAttribute.getByAttribute(newRow[newColumnIndex]);
        SpriteStateAttibuteApplier.apply(spriteState, attribute);
        spriteState.getCoordinates().setRow(newRowIndex);
        spriteState.getCoordinates().setColumn(newColumnIndex);
        spriteState.setCurrentSpace(attribute.getMazeSpace());
    }
}
