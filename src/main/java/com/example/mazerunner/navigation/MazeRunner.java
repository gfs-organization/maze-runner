package com.example.mazerunner.navigation;

import static com.example.mazerunner.parts.MazeSpace.OPEN_SPACE;
import static com.example.mazerunner.parts.MazeSpace.WALL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.mazerunner.parts.CardinalDirection;
import com.example.mazerunner.parts.Coordinates;
import com.example.mazerunner.parts.FoundExitException;
import com.example.mazerunner.parts.Maze;
import com.example.mazerunner.parts.MazeMaster;
import com.example.mazerunner.parts.MazeSpace;

@Component
public class MazeRunner {

    @Autowired
    private List<MazeMaster> mazeMasterList;
    private static final String PASSCODE_MESSAGE = "Your secret passcode is ";

    public String runTheMaze(final int mazeLevel, final List<String> directions) {
        final Maze maze = chooseTheMaze(mazeLevel);

        System.out.println("Running the " + maze.getMazeTitle());
        final Coordinates coordinates = new Coordinates();

        try {
            for (final String direction : directions) {
                final CardinalDirection stepDirection = CardinalDirection.getByName(direction);
                final MazeSpace lastSpace = stepDirection.getStepper().step(maze, coordinates);
                //                System.out.print("\"" + direction + "\",");
                if (lastSpace == WALL) {
                    return WALL.getLongDescription();
                }
            }
        } catch (final FoundExitException e) {
            return buildSuccessMessage(directions, maze);
        }

        return OPEN_SPACE.getLongDescription();

    }

    private Maze chooseTheMaze(final int mazeLevel) {
        final int numberOfMazes = mazeMasterList.size();
        if (mazeLevel < 1 || mazeLevel > numberOfMazes) {
            throw new IllegalArgumentException("You did not enter a valid maze level. Please enter a number between 1 and " + numberOfMazes);
        }

        return mazeMasterList.get(mazeLevel - 1).getMaze();
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
