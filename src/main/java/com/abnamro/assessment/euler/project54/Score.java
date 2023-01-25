package com.abnamro.assessment.euler.project54;

/**
 * Represents the score with a royal flush as highest and a high card as lowest. Each hand will
 * be valued to have on of these scores and the hand with the highest score wins.
 *
 * Hands with equal scores will be determined on the highest scoring card(s).
 */
public enum Score {
    ROYAL_FLUSH(10), STRAIGHT_FLUSH(9), FOUR_OF_A_KIND(8),
    FULL_HOUSE(7), FLUSH(6), STRAIGHT(5), THREE_OF_A_KIND(4),
    TWO_PAIRS(3), ONE_PAIR(2), HIGH_CARD(1),

    UNDETERMINED(0)
    ;

    private final int value;

    Score(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
