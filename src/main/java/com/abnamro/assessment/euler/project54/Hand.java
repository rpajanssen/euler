package com.abnamro.assessment.euler.project54;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hand {
    private final Score score;
    private final List<Integer> highCards;

    public Hand(Score score, Integer highCard) {
        this.score = score;
        this.highCards = Arrays.asList(highCard);
    }

    public Hand(Score score, List<Integer> highCards) {
        this.score = score;
        this.highCards = highCards != null ? highCards : new ArrayList<>();
    }

    public Score getScore() {
        return score;
    }

    public List<Integer> getHighCards() {
        return highCards;
    }

    public Hand addHighCard(Card card) {
        highCards.add(card.getValue());

        return this;
    }
}
