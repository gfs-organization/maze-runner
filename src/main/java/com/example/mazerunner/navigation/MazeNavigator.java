package com.example.mazerunner.navigation;

import static com.example.mazerunner.parts.MazeSpace.EXIT;
import static com.example.mazerunner.parts.MazeSpace.OPEN_SPACE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.mazerunner.parts.CardinalDirection;
import com.example.mazerunner.parts.Cell;
import com.example.mazerunner.parts.Coordinates;
import com.example.mazerunner.parts.FoundExitException;
import com.example.mazerunner.parts.MazeMaster;
import com.example.mazerunner.parts.MazeSpace;

public class MazeNavigator {

    private MazeMaster mazeMaster;
    
    public MazeNavigator(final MazeMaster mazeMaster) {
        this.mazeMaster = mazeMaster;
    }

    public CardinalDirection mapCellsAndReturnExit(final Cell currentCell, final Map<Integer, List<Cell>> mappedCells, final Coordinates coordinates)
            throws FoundExitException {

        if (mapEastAndReturnExit(currentCell, mappedCells, coordinates)) {
            return CardinalDirection.EAST;
        }
        if (mapWestAndReturnExit(currentCell, mappedCells, coordinates)) {
            return CardinalDirection.WEST;
        }
        if (mapSouthAndReturnExit(currentCell, mappedCells, coordinates)) {
            return CardinalDirection.SOUTH;
        }
        if (mapNorthAndReturnExit(currentCell, mappedCells, coordinates)) {
            return CardinalDirection.NORTH;
        }
        return null;
    }

    private boolean mapEastAndReturnExit(final Cell currentCell, final Map<Integer, List<Cell>> mappedCells, final Coordinates coordinates)
            throws FoundExitException {
        final int currentX = currentCell.getX();
        final int currentY = currentCell.getY();
        final int newX = currentX + 1;

        final List<Cell> cellsInRow = mappedCells.get(currentY);
        if (cellsInRow.size() > newX && !cellsInRow.get(newX).isUnknown()) {
            return false;
        }

        final Cell testCell = new Cell(newX, currentY);

        final MazeSpace mazeSpace = mazeMaster.stepEast(coordinates);
        if (mazeSpace == EXIT) {
            testCell.setExit(true);
            mazeMaster.stepWest(coordinates);
            return true;
        } else if (mazeSpace == OPEN_SPACE) {
            testCell.setOpen(true);
            mazeMaster.stepWest(coordinates);
        } else {
            testCell.setWall(true);
        }

        fillUnknownCellsInRow(mappedCells, currentY, newX, testCell);
        return false;
    }

    private void fillUnknownCellsInRow(final Map<Integer, List<Cell>> mappedCells, final int targetY, final int targetX, final Cell westCell) {
        final List<Cell> currentRowCells = mappedCells.computeIfAbsent(targetY, k -> new ArrayList<>());
        for (int x = 0; x < targetX; x++) {
            if (x >= currentRowCells.size()) {
                final Cell unknownCell = new Cell(x, targetY);
                currentRowCells.add(unknownCell);
            }
        }
        if (currentRowCells.size() > targetX) {
            currentRowCells.set(targetX, westCell);
        } else {
            currentRowCells.add(westCell);
        }
    }

    private boolean mapWestAndReturnExit(final Cell currentCell, final Map<Integer, List<Cell>> mappedCells, final Coordinates coordinates)
            throws FoundExitException {
        final int currentX = currentCell.getX();
        final int currentY = currentCell.getY();
        final int newX = currentX - 1;

        final List<Cell> cellsInRow = mappedCells.get(currentY);
        if (newX < 0 || !cellsInRow.get(newX).isUnknown()) {
            return false;
        }

        final Cell testCell = new Cell(newX, currentY);

        final MazeSpace mazeSpace = mazeMaster.stepWest(coordinates);
        if (mazeSpace == EXIT) {
            testCell.setExit(true);
            mazeMaster.stepEast(coordinates);
            return true;
        } else if (mazeSpace == OPEN_SPACE) {
            testCell.setOpen(true);
            mazeMaster.stepEast(coordinates);
        } else {
            testCell.setWall(true);
        }

        fillUnknownCellsInRow(mappedCells, currentY, newX, testCell);

        return false;
    }

    private boolean mapSouthAndReturnExit(final Cell currentCell, final Map<Integer, List<Cell>> mappedCells, final Coordinates coordinates)
            throws FoundExitException {
        final int currentX = currentCell.getX();
        final int currentY = currentCell.getY();
        final int newY = currentY + 1;

        final List<Cell> cellsInRow = mappedCells.get(newY);
        if (cellsInRow != null && cellsInRow.size() > currentX && !cellsInRow.get(currentX).isUnknown()) {
            return false;
        }

        final Cell testCell = new Cell(currentX, newY);

        final MazeSpace mazeSpace = mazeMaster.stepSouth(coordinates);
        if (mazeSpace == EXIT) {
            testCell.setExit(true);
            mazeMaster.stepNorth(coordinates);
            return true;
        } else if (mazeSpace == OPEN_SPACE) {
            testCell.setOpen(true);
            mazeMaster.stepNorth(coordinates);
        } else {
            testCell.setWall(true);
        }

        fillUnknownCellsInRow(mappedCells, newY, currentX, testCell);
        return false;
    }

    private boolean mapNorthAndReturnExit(final Cell currentCell, final Map<Integer, List<Cell>> mappedCells, final Coordinates coordinates)
            throws FoundExitException {
        final int currentX = currentCell.getX();
        final int currentY = currentCell.getY();
        final int newY = currentY - 1;

        final List<Cell> cellsInRow = mappedCells.get(newY);
        if (newY < 0 || !cellsInRow.get(currentX).isUnknown()) {
            return false;
        }

        final Cell testCell = new Cell(currentX, newY);

        final MazeSpace mazeSpace = mazeMaster.stepNorth(coordinates);
        if (mazeSpace == EXIT) {
            testCell.setExit(true);
            mazeMaster.stepSouth(coordinates);
            return true;
        } else if (mazeSpace == OPEN_SPACE) {
            testCell.setOpen(true);
            mazeMaster.stepSouth(coordinates);
        } else {
            testCell.setWall(true);
        }

        fillUnknownCellsInRow(mappedCells, newY, currentX, testCell);
        return false;
    }
}