package com.example.mazerunner.parts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.io.ClassPathResource;

public class MazeMaster {

    private final Maze maze;

    public MazeMaster(final String commandFileName) throws IOException {

        maze = new Maze();

        try (final InputStream inputStream = new ClassPathResource(commandFileName).getInputStream();
                final Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines()) {

            final List<String> rows = lines.collect(Collectors.toList());

            maze.setMazeTitle(rows.get(0));
            maze.setSuccessMessage(rows.get(1));

            rows.remove(0); //remove title
            rows.remove(0); // remove success message

            buildMaze(rows);
        }
    }

    private void buildMaze(final List<String> rows) {
        final List<char[]> mazeMap = new ArrayList<>(rows.size());
        int maxColumnIndex = 0;

        //        System.out.println("Loaded the map:" + mazeTitle);
        for (final String row : rows) {
            //            System.out.println(row);
            final char[] cells = row.toCharArray();
            mazeMap.add(cells);

            maxColumnIndex = maxColumnIndex > cells.length - 1 ? maxColumnIndex : cells.length - 1;
        }

        maze.setMazeMap(mazeMap);
        maze.setMaxRowIndex(rows.size() - 1);
        maze.setMaxColumnIndex(maxColumnIndex);
    }

    /**
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
        return stepDirection.getStepper().step(maze, coordinates);

        //        switch (stepDirection) {
        //            case EAST:
        //                return stepEast(coordinates);
        //            case WEST:
        //                return stepWest(coordinates);
        //            case NORTH:
        //                return stepNorth(coordinates);
        //            case SOUTH:
        //                return stepSouth(coordinates);
        //            default:
        //                throw new IllegalArgumentException("An invalid direction of " + direction + " was sent.");
        //        }

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

    public Maze getMaze() {
        return maze;
    }
}
