package com.example.mazerunner.parts;

import static com.example.mazerunner.parts.CardinalDirection.EAST;
import static com.example.mazerunner.parts.CardinalDirection.NORTH;
import static com.example.mazerunner.parts.CardinalDirection.SOUTH;
import static com.example.mazerunner.parts.CardinalDirection.WEST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.mazerunner.navigation.MazeNavigator;

public class MazeRunnerOld {
    private final MazeMaster mazeMaster;
    private final MazeNavigator mazeNavigator;

    public MazeRunnerOld(final MazeMaster mazeMaster, final MazeNavigator mazeNavigator) {
        this.mazeMaster = mazeMaster;
        this.mazeNavigator = mazeNavigator;
    }

    public String runTheMaze() {
        final List<String> directions = new ArrayList<>();
        final Map<Integer, List<Cell>> mappedCells = new HashMap<>();

        Cell nextCell = new Cell(0, 0);
        nextCell.setOpen(true);

        final List<Cell> firstRow = new ArrayList<>();
        firstRow.add(nextCell);
        mappedCells.put(0, firstRow);

        final Coordinates coordinates = new Coordinates();
        try {
            mapAndExitCheck(directions, mappedCells, nextCell, coordinates);

            // move east?
            nextCell = moveEast(directions, mappedCells, nextCell, coordinates);
            // move south?
            nextCell = moveSouth(directions, mappedCells, nextCell, coordinates);
            //move west?
            nextCell = moveWest(directions, mappedCells, nextCell, coordinates);
            // move north?
            //moveNorth(directions, mappedCells, nextCell, coordinates);

            nextCell = moveSouth(directions, mappedCells, nextCell, coordinates);
            nextCell = moveEast(directions, mappedCells, nextCell, coordinates);

        } catch (final FoundExitException e) {
            // drop through to return if we found the exit
        }
        return String.join(",", directions);
    }

    private Cell moveNorth(final List<String> directions, final Map<Integer, List<Cell>> mappedCells, Cell nextCell, final Coordinates coordinates)
            throws FoundExitException {
        nextCell = mappedCells.get(nextCell.getY() - 1).get(nextCell.getX());
        while (nextCell.isOpen()) {
            mazeMaster.stepNorth(coordinates);
            directions.add(NORTH.getDirection());

            mapAndExitCheck(directions, mappedCells, nextCell, coordinates);

            nextCell = mappedCells.get(nextCell.getY() - 1).get(nextCell.getX());
        }
        nextCell = mappedCells.get(nextCell.getY() + 1).get(nextCell.getX());
        return nextCell;
    }

    private Cell moveWest(final List<String> directions, final Map<Integer, List<Cell>> mappedCells, Cell nextCell, final Coordinates coordinates)
            throws FoundExitException {
        nextCell = mappedCells.get(nextCell.getY()).get(nextCell.getX() - 1);
        while (nextCell.isOpen()) {
            mazeMaster.stepWest(coordinates);
            directions.add(WEST.getDirection());

            mapAndExitCheck(directions, mappedCells, nextCell, coordinates);

            nextCell = mappedCells.get(nextCell.getY()).get(nextCell.getX() - 1);
        }
        nextCell = mappedCells.get(nextCell.getY()).get(nextCell.getX() + 1);
        return nextCell;
    }

    private Cell moveSouth(final List<String> directions, final Map<Integer, List<Cell>> mappedCells, Cell nextCell, final Coordinates coordinates)
            throws FoundExitException {
        nextCell = mappedCells.get(nextCell.getY() + 1).get(nextCell.getX());
        while (nextCell.isOpen()) {
            mazeMaster.stepSouth(coordinates);
            directions.add(SOUTH.getDirection());

            mapAndExitCheck(directions, mappedCells, nextCell, coordinates);

            nextCell = mappedCells.get(nextCell.getY() + 1).get(nextCell.getX());
        }
        nextCell = mappedCells.get(nextCell.getY() - 1).get(nextCell.getX());
        return nextCell;
    }

    private Cell moveEast(final List<String> directions, final Map<Integer, List<Cell>> mappedCells, Cell nextCell, final Coordinates coordinates)
            throws FoundExitException {
        nextCell = mappedCells.get(nextCell.getY()).get(nextCell.getX() + 1);
        while (nextCell.isOpen()) {
            mazeMaster.stepEast(coordinates);
            directions.add(EAST.getDirection());

            mapAndExitCheck(directions, mappedCells, nextCell, coordinates);

            nextCell = mappedCells.get(nextCell.getY()).get(nextCell.getX() + 1);
        }
        nextCell = mappedCells.get(nextCell.getY()).get(nextCell.getX() - 1);
        return nextCell;
    }

    private void mapAndExitCheck(final List<String> directions, final Map<Integer, List<Cell>> mappedCells, final Cell nextCell,
            final Coordinates coordinates) throws FoundExitException {
        final CardinalDirection foundExit = mazeNavigator.mapCellsAndReturnExit(nextCell, mappedCells, coordinates);
        if (foundExit != null) {
            directions.add(foundExit.getDirection());
            throw new FoundExitException("Found the exit");
        }
    }

}
