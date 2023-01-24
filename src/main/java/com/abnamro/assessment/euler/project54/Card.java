package com.abnamro.assessment.euler.project54;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

enum Card {
    TWO("2", 1), THREE("3",2), FOUR("4", 4),
    FIVE("5", 8), SIX("6",16), SEVEN("7", 32),
    EIGHT("8", 64), NINE("9",128), TEN("T", 256),
    JACK("J", 512), QUEEN("Q",1024), KING("K", 2048),
    ACE("A", 4096)
    ;

    private final String card;
    private final int value;

    Card(String card, int value) {
        this.card = card;
        this.value = value;
    }

    public static Card forCard(String aCard) {
        // todo : defensive programming
        return Arrays.stream(Card.values())
                .filter(c -> c.getCard().equals(aCard))
                .findFirst()
                .get();
    }

    String getCard() {
        return card;
    }

    int getValue() {
        return value;
    }

    public static List<Card> reverseValues(){
        return Arrays.stream(Card.values()).sorted(new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.compareTo(o2);
            }
        }).collect(Collectors.toList());
    }
}
