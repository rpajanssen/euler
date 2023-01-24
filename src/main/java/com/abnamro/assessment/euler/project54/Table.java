package com.abnamro.assessment.euler.project54;

public class Table {
    private Player playerOne = new Player();
    private Player playerTwo = new Player();

    public Table() {
        // todo
    }

    public Table(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }
}
