package com.abnamro.assessment.euler.project15;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BruteForceTest {

    @Test
    void shouldCalculateForATwoByTwoGrid() {
        BruteForce underTest = new BruteForce(2,2);

        assertEquals(6L, underTest.calculate());
    }

    @Test
    void shouldCalculateForAThreeByThreeGrid() {
        BruteForce underTest = new BruteForce(3,3);

        assertEquals(20L, underTest.calculate());
    }

    @Test
    void shouldCalculateForAFourByFourGrid() {
        BruteForce underTest = new BruteForce(4,4);

        assertEquals(70L, underTest.calculate());
    }

    @Test
    void shouldCalculateForAFiveByFiveGrid() {
        BruteForce underTest = new BruteForce(5,5);

        assertEquals(252L, underTest.calculate());
    }

    @Disabled
    @Test
    void shouldCalculateForATwentyByTwentyGrid() {
        BruteForce underTest = new BruteForce(20,20);

        assertEquals(137846528820L, underTest.calculate());
    }
}
