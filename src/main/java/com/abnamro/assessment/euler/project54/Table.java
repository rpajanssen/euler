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
}
