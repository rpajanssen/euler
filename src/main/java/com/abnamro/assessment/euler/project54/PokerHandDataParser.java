package com.abnamro.assessment.euler.project54;

public final class PokerHandDataParser {
    private PokerHandDataParser() {
        throw new UnsupportedOperationException();
    }

    public static Table parseHands(String hands) {
        Table table = new Table();
        String[] allCards = hands.split(" ");

        for (int cardNr = 0; cardNr <= 4; cardNr++) {
            Card card = readCard(cardNr, allCards);
            Suite suite = readSuite(card, cardNr, allCards);

            table.getPlayerOne().setCardsForSuite(suite, table.getPlayerOne().getCardsForSuite(suite).intValue() + card.getValue());
        }

        for (int cardNr = 5; cardNr <= 9; cardNr++) {
            Card card = readCard(cardNr, allCards);
            Suite suite = readSuite(card, cardNr, allCards);

            table.getPlayerTwo().setCardsForSuite(suite, table.getPlayerTwo().getCardsForSuite(suite).intValue() + card.getValue());
        }

        return table;
    }

    private static Card readCard(int cardNr, String[] allCards) {
        return Card.forCard(allCards[cardNr].substring(0, 1));
    }

    private static Suite readSuite(Card card, int cardNr, String[] allCards) {
        return Suite.valueOf(allCards[cardNr].substring(1, 2));
    }
}
