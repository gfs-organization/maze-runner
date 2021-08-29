package com.example.mazerunner.exceptions;

public class FoundExitException extends MazeException {
    public FoundExitException(final String found_the_exit) {
        super(found_the_exit);
    }
}
