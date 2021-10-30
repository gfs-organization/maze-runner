package com.example.mazerunner.parts;

import java.util.List;

public class SpriteState {

    private Coordinates coordinates = new Coordinates();
    private Wallet wallet = new Wallet();
    private List<Maze> mazes;
    private MazeSpace currentSpace;
    private int currentFloor;

    public void incrementGold() {
        wallet.incrementGold();
    }

    public void incrementSilver() {
        wallet.incrementSilver();
    }

    public void incrementCopper() {
        wallet.incrementCopper();
    }

    public void incrementFloor() {
        currentFloor++;
    }

    public void decrementFloor() {
        currentFloor--;
    }

    public Maze getCurrentMaze() {
        return mazes.get(currentFloor);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(final Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<Maze> getMazes() {
        return mazes;
    }

    public void setMazes(final List<Maze> mazes) {
        this.mazes = mazes;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(final Wallet wallet) {
        this.wallet = wallet;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(final int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public MazeSpace getCurrentSpace() {
        return currentSpace;
    }

    public void setCurrentSpace(final MazeSpace currentSpace) {
        this.currentSpace = currentSpace;
    }

    public void resetCoordinates(final int row, final int column) {
        this.coordinates.setRow(row);
        this.coordinates.setColumn(column);
    }
}
