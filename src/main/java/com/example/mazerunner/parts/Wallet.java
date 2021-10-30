package com.example.mazerunner.parts;

public class Wallet {
    private int goldPieces;
    private int silverPieces;
    private int copperPieces;

    public void incrementGold() {
        goldPieces++;
    }

    public void incrementSilver() {
        silverPieces++;
    }

    public void incrementCopper() {
        copperPieces++;
    }

    public int getGoldPieces() {
        return goldPieces;
    }

    public void setGoldPieces(final int goldPieces) {
        this.goldPieces = goldPieces;
    }

    public int getSilverPieces() {
        return silverPieces;
    }

    public void setSilverPieces(final int silverPieces) {
        this.silverPieces = silverPieces;
    }

    public int getCopperPieces() {
        return copperPieces;
    }

    public void setCopperPieces(final int copperPieces) {
        this.copperPieces = copperPieces;
    }
}
