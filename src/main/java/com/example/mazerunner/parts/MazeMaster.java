package com.example.mazerunner.parts;

import static com.example.mazerunner.parts.CardinalDirection.EAST;
import static com.example.mazerunner.parts.CardinalDirection.NORTH;
import static com.example.mazerunner.parts.CardinalDirection.SOUTH;
import static com.example.mazerunner.parts.CardinalDirection.WEST;
import static com.example.mazerunner.parts.MazeSpace.EXIT;
import static com.example.mazerunner.parts.MazeSpace.OPEN_SPACE;
import static com.example.mazerunner.parts.MazeSpace.WALL;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MazeMaster {

    private List<char[]> mazeMap;
    private int maxRowIndex;
    private int maxColumnIndex;

    private static final char MAP_WALL = 'W';
    private static final char MAP_OPEN_SPACE = '_';
    private static final char MAP_EXIT = 'E';

    private String mazeTitle;
    private String successMessage;

    public MazeMaster(final String commandFileName) throws IOException, URISyntaxException {
        final Path path = Paths.get(getClass().getClassLoader().getResource(commandFileName).toURI());
        final List<String> rows = Files.lines(path).collect(Collectors.toList());

        mazeTitle = rows.get(0);
        successMessage = rows.get(1);

        rows.remove(0); //remove title
        rows.remove(0); // remove success message

        buildMaze(rows);
    }

    private void buildMaze(final List<String> rows) {
        mazeMap = new ArrayList<>(rows.size());
        maxRowIndex = rows.size() - 1;

        System.out.println("Loaded the map:" + mazeTitle);
        for (final String row : rows) {
            System.out.println(row);
            final char[] cells = row.toCharArray();
            mazeMap.add(cells);

            maxColumnIndex = maxColumnIndex > cells.length - 1 ? maxColumnIndex : cells.length - 1;
        }
    }

    public MazeSpace step(final String direction, final Coordinates coordinates) {
        if (direction.equals(EAST.getDirection())) {
            return stepEast(coordinates);
        } else if (direction.equals(WEST.getDirection())) {
            return stepWest(coordinates);
        } else if (direction.equals(NORTH.getDirection())) {
            return stepNorth(coordinates);
        } else if (direction.equals(SOUTH.getDirection())) {
            return stepSouth(coordinates);
        } else {
            throw new IllegalArgumentException("An invalid direction of " + direction + " was sent.");
        }
    }

    public MazeSpace stepNorth(final Coordinates coordinates) {
        final int currentRow = coordinates.getRow();
        final int currentColumn = coordinates.getColumn();

        if (currentRow == 0) {
            return WALL; // do not change coordinates
        }

        final char[] newRow = mazeMap.get(currentRow - 1);
        if (newRow[currentColumn] == MAP_OPEN_SPACE) {
            coordinates.setRow(currentRow - 1);
            return OPEN_SPACE;
        } else if (newRow[currentColumn] == MAP_WALL) {
            return WALL;
        } else if (newRow[currentColumn] == MAP_EXIT) {
            return EXIT;
        }
        return null;
    }

    public MazeSpace stepSouth(final Coordinates coordinates) {
        final int currentRow = coordinates.getRow();
        final int currentColumn = coordinates.getColumn();

        if (coordinates.getRow() == maxRowIndex) {
            return WALL;
        }

        final char[] newRow = mazeMap.get(currentRow + 1);
        if (newRow[currentColumn] == MAP_OPEN_SPACE) {
            coordinates.setRow(currentRow + 1);
            return OPEN_SPACE;
        } else if (newRow[currentColumn] == MAP_WALL) {
            return WALL;
        } else if (newRow[currentColumn] == MAP_EXIT) {
            return EXIT;
        }
        return null;
    }

    public MazeSpace stepEast(final Coordinates coordinates) {
        final int currentRow = coordinates.getRow();
        final int currentColumn = coordinates.getColumn();
        final char[] currentRowCells = mazeMap.get(currentRow);

        if (coordinates.getColumn() == maxColumnIndex) {
            return WALL;
        }

        final int newColumn = currentColumn + 1;

        if (currentRowCells[newColumn] == MAP_WALL) {
            return WALL;
        } else if (currentRowCells[newColumn] == MAP_OPEN_SPACE) {
            coordinates.setColumn(newColumn);
            return OPEN_SPACE;
        } else if (currentRowCells[newColumn] == MAP_EXIT) {
            return EXIT;
        }
        return null;
    }

    public MazeSpace stepWest(final Coordinates coordinates) {
        final int currentRow = coordinates.getRow();
        final int currentColumn = coordinates.getColumn();
        final char[] currentRowCells = mazeMap.get(currentRow);

        if (coordinates.getColumn() == 0) {
            return WALL;
        }

        final int newColumn = currentColumn - 1;

        if (currentRowCells[newColumn] == MAP_WALL) {
            return WALL;
        } else if (currentRowCells[newColumn] == MAP_OPEN_SPACE) {
            coordinates.setColumn(newColumn);
            return OPEN_SPACE;
        } else if (currentRowCells[newColumn] == MAP_EXIT) {
            return EXIT;
        }
        return null;
    }

    public String getMazeTitle() {
        return mazeTitle;
    }

    public void setMazeTitle(final String mazeTitle) {
        this.mazeTitle = mazeTitle;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(final String successMessage) {
        this.successMessage = successMessage;
    }
}
