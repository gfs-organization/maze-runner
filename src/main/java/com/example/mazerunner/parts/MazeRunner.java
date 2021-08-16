package com.example.mazerunner.parts;

import static com.example.mazerunner.parts.MazeSpace.OPEN_SPACE;
import static com.example.mazerunner.parts.MazeSpace.WALL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MazeRunner {
    @Autowired
    private MazeMaster levelOneMazeMaster;
    @Autowired
    private MazeMaster levelTwoMazeMaster;
    @Autowired
    private MazeMaster levelThreeMazeMaster;
    @Autowired
    private MazeMaster levelFourMazeMaster;

    public String runTheMaze(final int mazeLevel, final List<String> directions) {
        final MazeMaster mazeMaster = chooseTheMaze(mazeLevel);
        final Coordinates coordinates = new Coordinates();

        MazeSpace lastSpace = OPEN_SPACE;

        for (final String direction : directions) {
            //            System.out.println(direction);
            lastSpace = mazeMaster.step(direction, coordinates);
            if (lastSpace == WALL) {
                return WALL.getLongDescription();
            }
        }

        if (lastSpace == OPEN_SPACE) {
            return OPEN_SPACE.getLongDescription();
        }

        return mazeMaster.getSuccessMessage();
    }

    private MazeMaster chooseTheMaze(final int mazeLevel) {
        switch (mazeLevel) {
            case 1:
                return levelOneMazeMaster;
            case 2:
                return levelTwoMazeMaster;
            case 3:
                return levelThreeMazeMaster;
            case 4:
                return levelFourMazeMaster;
            default:
                throw new IllegalArgumentException("You did not enter a valid maze level. Please enter 1, 2, 3, or 4.");
        }
    }
}
