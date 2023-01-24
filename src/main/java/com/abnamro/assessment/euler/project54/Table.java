package com.abnamro.assessment.euler.project54;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Table {
    Map<Suite, Integer> playerOne = new HashMap<>();
    Map<Suite, Integer> playerTwo = new HashMap<>();

    public Table() {
        Arrays.stream(Suite.values()).forEach(
                s -> {
                    playerOne.put(s, 0);
                    playerTwo.put(s, 0);
                }
        );
    }

    public Table(Map<Suite, Integer> playerOneCards, Map<Suite, Integer> playerTwoCards) {
        playerOneCards.keySet().stream().forEach(
                k -> this.playerOne.put(k, playerOneCards.get(k))
        );

        playerTwoCards.keySet().stream().forEach(
                k -> this.playerTwo.put(k, playerTwoCards.get(k))
        );
    }

    public Map<Suite, Integer> getPlayerOne() {
        return playerOne;
    }

    public Map<Suite, Integer> getPlayerTwo() {
        return playerTwo;
    }
}
