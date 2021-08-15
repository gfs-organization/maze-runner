package com.example.mazerunner.parts;

public class FoundExitException extends Exception {
    public FoundExitException(final String found_the_exit) {
        super(found_the_exit);
    }
}
