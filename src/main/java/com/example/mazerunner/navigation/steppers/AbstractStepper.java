package com.example.mazerunner.navigation.steppers;

import com.example.mazerunner.parts.Coordinates;
import com.example.mazerunner.parts.FoundExitException;
import com.example.mazerunner.parts.Maze;
import com.example.mazerunner.parts.MazeSpace;

public abstract class AbstractStepper {

    static final char MAP_WALL = 'W';
    static final char MAP_OPEN_SPACE = '_';
    static final char MAP_EXIT = 'E';

    public abstract MazeSpace step(final Maze maze, final Coordinates coordinates) throws FoundExitException;

}
