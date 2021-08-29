package com.example.mazerunner.parts;

import static com.example.mazerunner.parts.MazeSpace.OPEN_SPACE;
import static com.example.mazerunner.parts.MazeSpace.WALL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;

public class MazeMaster {

    @Autowired
    private ResourceLoader resourceLoader;

    private List<char[]> mazeMap;
    private int maxRowIndex;
    private int maxColumnIndex;

    private static final char MAP_WALL = 'W';
    private static final char MAP_OPEN_SPACE = '_';
    private static final char MAP_EXIT = 'E';

    private String mazeTitle;
    private String successMessage;

    public MazeMaster(final String commandFileName) throws IOException, URISyntaxException {

        final InputStream inputStream = new ClassPathResource(commandFileName).getInputStream();
        final Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines();
        final List<String> rows = lines.collect(Collectors.toList());

        mazeTitle = rows.get(0);
        successMessage = rows.get(1);

        rows.remove(0); //remove title
        rows.remove(0); // remove success message

        buildMaze(rows);
    }

    private void buildMaze(final List<String> rows) {
        mazeMap = new ArrayList<>(rows.size());
        maxRowIndex = rows.size() - 1;

        //        System.out.println("Loaded the map:" + mazeTitle);
        for (final String row : rows) {
            //            System.out.println(row);
            final char[] cells = row.toCharArray();
            mazeMap.add(cells);

            maxColumnIndex = maxColumnIndex > cells.length - 1 ? maxColumnIndex : cells.length - 1;
        }
    }

    /**
     * https://www.geeksforgeeks.org/switch-vs-else/
     * <p>
     * A switch statement is usually more efficient than a set of nested ifs. Deciding whether to use if-then-else statements or a switch statement
     * is based on readability and the expression that the statement is testing.
     * <p>
     * # Check the Testing Expression: An if-then-else statement can test expressions based on ranges of values or conditions, whereas a switch
     * statement tests expressions based only on a single integer, enumerated value, or String object.
     * <p>
     * # Switch better for Multi way branching: When compiler compiles a switch statement, it will inspect each of the case constants and create a
     * “jump table” that it will use for selecting the path of execution depending on the value of the expression. Therefore, if we need to select
     * among a large group of values, a switch statement will run much faster than the equivalent logic coded using a sequence of if-elses. The
     * compiler can do this because it knows that the case constants are all the same type and simply must be compared for equality with the switch
     * expression, while in case of if expressions, the compiler has no such knowledge.
     * <p>
     * # if-else better for boolean values: If-else conditional branches are great for variable conditions that result into a boolean, whereas switch
     * statements are great for fixed data values.
     * <p>
     * # Speed: A switch statement might prove to be faster than ifs provided number of cases are good. If there are only few cases, it might not
     * effect the speed in any case. Prefer switch if the number of cases are more than 5 otherwise, you may use if-else too.
     * If a switch contains more than five items, it’s implemented using a lookup table or a hash list. This means that all items get the same
     * access time, compared to a list of if:s where the last item takes much more time to reach as it has to evaluate every previous condition first.
     * <p>
     * # Clarity in readability: A switch looks much cleaner when you have to combine cases. Ifs  are quite vulnerable to errors too. Missing an else
     * statement can land you up in havoc. Adding/removing labels is also easier with a switch and makes your code significantly easier to change
     * and maintain.
     *
     * @param direction
     * @param coordinates
     * @return
     * @throws FoundExitException
     */
    public MazeSpace step(final String direction, final Coordinates coordinates) throws FoundExitException {

        final CardinalDirection stepDirection = CardinalDirection.getByName(direction);
        switch (stepDirection) {
            case EAST:
                return stepEast(coordinates);
            case WEST:
                return stepWest(coordinates);
            case NORTH:
                return stepNorth(coordinates);
            case SOUTH:
                return stepSouth(coordinates);
            default:
                throw new IllegalArgumentException("An invalid direction of " + direction + " was sent.");
        }

        //        if (stepDirection == EAST) {
        //            return stepEast(coordinates);
        //        } else if (stepDirection == WEST) {
        //            return stepWest(coordinates);
        //        } else if (stepDirection == NORTH) {
        //            return stepNorth(coordinates);
        //        } else if (stepDirection == SOUTH) {
        //            return stepSouth(coordinates);
        //        } else {
        //            throw new IllegalArgumentException("An invalid direction of " + direction + " was sent.");
        //        }
    }

    public MazeSpace stepNorth(final Coordinates coordinates) throws FoundExitException {
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
            throw new FoundExitException("You found the exit.");
        }
        return null;
    }

    public MazeSpace stepSouth(final Coordinates coordinates) throws FoundExitException {
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
            throw new FoundExitException("You found the exit.");
        }
        return null;
    }

    public MazeSpace stepEast(final Coordinates coordinates) throws FoundExitException {
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
            throw new FoundExitException("You found the exit.");
        }
        return null;
    }

    public MazeSpace stepWest(final Coordinates coordinates) throws FoundExitException {
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
            throw new FoundExitException("You found the exit.");
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
