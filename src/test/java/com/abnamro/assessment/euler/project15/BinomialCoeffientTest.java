package com.abnamro.assessment.euler.project15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinomialCoeffientTest {
    @Test
    void shouldCalculateForATwoByTwoGrid() {
        BinomialCoeffient underTest = new BinomialCoeffient(2);

        assertEquals(6L, underTest.calculate());
    }

    @Test
    void shouldCalculateForAThreeByThreeGrid() {
        BinomialCoeffient underTest = new BinomialCoeffient(3);

        assertEquals(20L, underTest.calculate());
    }

    @Test
    void shouldCalculateForAFourByFourGrid() {
        BinomialCoeffient underTest = new BinomialCoeffient(4);

        assertEquals(70L, underTest.calculate());
    }

    @Test
    void shouldCalculateForAFiveByFiveGrid() {
        BinomialCoeffient underTest = new BinomialCoeffient(5);

        assertEquals(252L, underTest.calculate());
    }

    @Test
    void shouldCalculateForATwentyByTwentyGrid() {
        BinomialCoeffient underTest = new BinomialCoeffient(20);

        assertEquals(137846528820L, underTest.calculate());
    }
}
