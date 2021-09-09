package com.example.mazerunner.navigation;

import static com.example.mazerunner.parts.CardinalDirection.DOWN;
import static com.example.mazerunner.parts.CardinalDirection.UP;
import static com.example.mazerunner.parts.MazeSpace.DOWN_STAIRS;
import static com.example.mazerunner.parts.MazeSpace.OPEN_SPACE;
import static com.example.mazerunner.parts.MazeSpace.UP_STAIRS;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.mazerunner.exceptions.FoundExitException;
import com.example.mazerunner.exceptions.MazeException;
import com.example.mazerunner.navigation.steppers.CardinalStepper;
import com.example.mazerunner.parts.CardinalDirection;
import com.example.mazerunner.parts.Coordinates;
import com.example.mazerunner.parts.Maze;
import com.example.mazerunner.parts.MazeMaster;
import com.example.mazerunner.parts.MazeSpace;

@Component
public class MazeRunner {

    @Autowired
    private Map<Integer, MazeMaster> mazeMasterMap;
    @Autowired
    private CardinalStepper cardinalStepper;
    private static final String PASSCODE_MESSAGE = "Your secret passcode is ";

    public String runTheMaze(final int mazeLevel, final List<String> directions) {
        final Map<Integer, Maze> mazes = chooseTheMazes(mazeLevel);
        int floor = 0;
        Maze maze = mazes.get(floor);

        System.out.println("Running the " + maze.getMazeTitle());
        Coordinates coordinates = new Coordinates();
        MazeSpace lastSpace = OPEN_SPACE;

        try {
            for (final String direction : directions) {
                final CardinalDirection stepDirection = CardinalDirection.getByName(direction);
                if (lastSpace == UP_STAIRS && stepDirection == UP) {
                    coordinates = new Coordinates();
                    maze = mazes.get(++floor);
                } else if (lastSpace == DOWN_STAIRS && stepDirection == DOWN) {
                    coordinates = new Coordinates();
                    maze = mazes.get(++floor);
                }

                lastSpace = cardinalStepper.doStep(maze, stepDirection, coordinates);
            }

        } catch (final FoundExitException e) {
            return buildSuccessMessage(directions, maze);
        } catch (final MazeException me) {
            return me.getMessage();
        }

        return lastSpace.getLongDescription();
    }

    private Map<Integer, Maze> chooseTheMazes(final int mazeLevel) {
        final MazeMaster mazeMaster = mazeMasterMap.get(mazeLevel);
        if (mazeMaster == null) {
            throw new IllegalArgumentException("You did not enter a valid maze level. Please enter a number between 1 and " + mazeMasterMap.size());
        }

        return mazeMaster.getMazes();
    }

    private String buildSuccessMessage(final List<String> directions, final Maze maze) {
        final char[] chars = String.join("", directions).toCharArray();
        int code = 0;
        for (final char c : chars) {
            code += c;
        }
        return maze.getSuccessMessage() + PASSCODE_MESSAGE + (code * directions.size()) + ".";
    }

}
