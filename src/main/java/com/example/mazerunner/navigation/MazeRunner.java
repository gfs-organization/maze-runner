package com.example.mazerunner.navigation;

import static com.example.mazerunner.parts.MazeSpace.OPEN_SPACE;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.mazerunner.exceptions.FoundExitException;
import com.example.mazerunner.exceptions.MazeException;
import com.example.mazerunner.navigation.steppers.CardinalStepper;
import com.example.mazerunner.parts.CardinalDirection;
import com.example.mazerunner.parts.Maze;
import com.example.mazerunner.parts.MazeMaster;
import com.example.mazerunner.parts.SpriteState;
import com.example.mazerunner.parts.Wallet;
import io.split.client.SplitClient;

@Component
public class MazeRunner {

    @Autowired
    private Map<Integer, MazeMaster> mazeMasterMap;
    @Autowired
    private CardinalStepper cardinalStepper;
    @Autowired
    private SplitClient splitClient;

    private static final String PASSCODE_MESSAGE = "Your secret passcode is ";
    private static final int LAST_SINGLE_LEVEL = 8;

    public String runTheMaze(final int mazeLevel, final List<String> directions) {
        final MazeMaster mazeMaster = chooseTheMazes(mazeLevel);

        final SpriteState spriteState = initializeSpriteState(mazeMaster);

        System.out.println("Running the " + spriteState.getCurrentMaze().getMazeTitle());

        try {
            for (final String direction : directions) {
                final CardinalDirection stepDirection = CardinalDirection.getByName(direction);
                cardinalStepper.doStep(spriteState, stepDirection);
            }

        } catch (final FoundExitException e) {
            final String coinsMessage = buildCoinsMessage(mazeMaster, spriteState);
            return buildSuccessMessage(directions, spriteState.getCurrentMaze(), coinsMessage);
        } catch (final MazeException me) {
            return me.getMessage();
        }

        return spriteState.getCurrentSpace().getLongDescription();
    }

    private MazeMaster chooseTheMazes(final int mazeLevel) {
        final String treatment = splitClient.getTreatment("brentt.smith", "multiple-floors");
        final boolean multipleFloorsAllowed = treatment.equals("on");

        if (mazeLevel > LAST_SINGLE_LEVEL && !multipleFloorsAllowed) {
            throw new IllegalArgumentException("Levels with multiple floors have not been turned on. Please try a level below 9.");
        }

        final MazeMaster mazeMaster = mazeMasterMap.get(mazeLevel);
        if (mazeMaster == null) {
            throw new IllegalArgumentException("You did not enter a valid maze level. Please enter a number between 1 and " + mazeMasterMap.size());
        }

        return mazeMaster;
    }

    private SpriteState initializeSpriteState(final MazeMaster mazeMaster) {
        final int floor = 0;
        final SpriteState spriteState = new SpriteState();
        spriteState.setMazes(mazeMaster.getMazes());
        spriteState.setCurrentFloor(floor);
        spriteState.setCurrentSpace(OPEN_SPACE);
        return spriteState;
    }

    private String buildCoinsMessage(final MazeMaster mazeMaster, final SpriteState spriteState) {
        final int copperPieces = mazeMaster.getCopperPieces();
        final int silverPieces = mazeMaster.getSilverPieces();
        final int goldPieces = mazeMaster.getGoldPieces();

        if (copperPieces + silverPieces + goldPieces == 0) {
            return "";
        }

        final StringBuilder builder = new StringBuilder("You found ");
        final Wallet wallet = spriteState.getWallet();
        builder.append(wallet.getGoldPieces());
        builder.append(" Gold Pieces and ");
        builder.append(wallet.getSilverPieces());
        builder.append(" Silver Pieces and ");
        builder.append(wallet.getCopperPieces());
        builder.append(" Copper Pieces.");

        builder.append(" And the maze has ");
        builder.append(goldPieces);
        builder.append(" Gold Pieces and ");
        builder.append(silverPieces);
        builder.append(" Silver Pieces and ");
        builder.append(copperPieces);
        builder.append(" Copper Pieces.");

        return builder.toString();
    }

    private String buildSuccessMessage(final List<String> directions, final Maze maze, final String coinsMessage) {
        final char[] chars = String.join("", directions).toCharArray();
        int code = 0;
        for (final char c : chars) {
            code += c;
        }
        return maze.getSuccessMessage() + PASSCODE_MESSAGE + (code * directions.size()) + ". " + coinsMessage;
    }

}
