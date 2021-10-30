package com.example.mazerunner.navigation.steppers;

import com.example.mazerunner.exceptions.FoundExitException;
import com.example.mazerunner.exceptions.MazeException;
import com.example.mazerunner.exceptions.WallException;
import com.example.mazerunner.parts.SpriteState;

public class SpriteStateAttibuteApplier {

    public static void apply(final SpriteState spriteState, final MapAttribute attribute) throws MazeException {
        switch (attribute) {
            case MAP_WALL:
                throw new WallException("You hit a wall");
            case MAP_EXIT:
                throw new FoundExitException("You found the exit.");
            case MAP_OPEN_SPACE:
                break;
            case MAP_COPPER_PIECE:
                spriteState.incrementCopper();
                break;
            case MAP_SILVER_PIECE:
                spriteState.incrementSilver();
                break;
            case MAP_GOLD_PIECE:
                spriteState.incrementGold();
                break;
        }
    }
}
