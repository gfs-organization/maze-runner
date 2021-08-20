package com.example.mazerunner.parts;

import static com.example.mazerunner.parts.MazeSpace.OPEN_SPACE;
import static com.example.mazerunner.parts.MazeSpace.WALL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MazeRunner {

    @Autowired
    private List<MazeMaster> mazeMasterList;

    public String runTheMaze(final int mazeLevel, final List<String> directions) {
        final MazeMaster mazeMaster = chooseTheMaze(mazeLevel);
        System.out.println("Running the " + mazeMaster.getMazeTitle());
        final Coordinates coordinates = new Coordinates();

        MazeSpace lastSpace = OPEN_SPACE;
        try {
            for (final String direction : directions) {
                lastSpace = mazeMaster.step(direction, coordinates);
                //                System.out.print("\"" + direction + "\",");
                if (lastSpace == WALL) {
                    return WALL.getLongDescription();
                }
            }
        } catch (final FoundExitException e) {
            return mazeMaster.getSuccessMessage();
        }

        return OPEN_SPACE.getLongDescription();

    }

    private MazeMaster chooseTheMaze(final int mazeLevel) {
        final int numberOfMazes = mazeMasterList.size();
        if (mazeLevel < 1 || mazeLevel > numberOfMazes) {
            throw new IllegalArgumentException("You did not enter a valid maze level. Please a number between 1 and " + numberOfMazes);
        }

        return mazeMasterList.get(mazeLevel - 1);
    }
}
