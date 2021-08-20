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
    @Autowired
    private MazeMaster levelFiveMazeMaster;
    @Autowired
    private MazeMaster levelSixMazeMaster;

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
        switch (mazeLevel) {
            case 1:
                return levelOneMazeMaster;
            case 2:
                return levelTwoMazeMaster;
            case 3:
                return levelThreeMazeMaster;
            case 4:
                return levelFourMazeMaster;
            case 5:
                return levelFiveMazeMaster;
            case 6:
                return levelSixMazeMaster;
            default:
                throw new IllegalArgumentException("You did not enter a valid maze level. Please enter 1, 2, 3, or 4.");
        }
    }
}
