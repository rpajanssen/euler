package com.abnamro.assessment.euler.project54;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PokerHandDataParserTest {
    @Test
    void shouldParseHands() {
        Table table = PokerHandDataParser.parseHands("JS 4H 8C TH JH 2C 9H 9C AS JD");

        assertEquals(64, table.getPlayerOne().getCardsForSuite(Suite.C));
        assertEquals(0, table.getPlayerOne().getCardsForSuite(Suite.D));
        assertEquals(772, table.getPlayerOne().getCardsForSuite(Suite.H));
        assertEquals(512, table.getPlayerOne().getCardsForSuite(Suite.S));

        assertEquals(129, table.getPlayerTwo().getCardsForSuite(Suite.C));
        assertEquals(512, table.getPlayerTwo().getCardsForSuite(Suite.D));
        assertEquals(128, table.getPlayerTwo().getCardsForSuite(Suite.H));
        assertEquals(4096, table.getPlayerTwo().getCardsForSuite(Suite.S));
    }
}
