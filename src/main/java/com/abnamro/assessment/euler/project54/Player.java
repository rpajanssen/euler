package com.abnamro.assessment.euler.project54;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Player {
    private Map<Suite, Integer> cards = new HashMap<>();

    public Player() {
        Arrays.stream(Suite.values()).forEach(
                s -> {
                    cards.put(s, 0);
                }
        );
    }

    public Player(Map<Suite, Integer> cardsOfPlayer) {
        cardsOfPlayer.keySet().stream().forEach(
                k -> this.cards.put(k, cardsOfPlayer.get(k))
        );
    }

    public Integer getCardsForSuite(Suite suite) {
        return cards.get(suite);
    }

    public void setCardsForSuite(Suite suite, Integer cards) {
        this.cards.put(suite, cards);
    }

    public Collection<Integer> getAllCards() {
        return cards.values();
    }
}
