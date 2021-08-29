package com.example.mazerunner.exceptions;

public class WallException extends MazeException {
    public WallException(final String hitAWall) {
        super(hitAWall);
    }
}
