package com.abnamro.assessment.euler.project54;

public enum Score {
    ROYAL_FLUSH(1024), STRAIGHT_FLUSH(256), FOUR_OF_A_KIND(128),
    FULL_HOUSE(64), FLUSH(32), STRAIGHT(16), THREE_OF_A_KIND(8),
    TWO_PAIRS(4), ONE_PAIR(2), HIGH_CARD(1),

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
