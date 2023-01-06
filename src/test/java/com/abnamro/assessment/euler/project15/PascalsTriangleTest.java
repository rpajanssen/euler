package com.abnamro.assessment.euler.project15;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PascalsTriangleTest {
    @Test
    void shouldCalculateForATwoByTwoGrid() {
        PascalsTriangle underTest = new PascalsTriangle(2);

        assertEquals(6L, underTest.calculate());
    }

    @Test
    void shouldCalculateForAThreeByThreeGrid() {
        PascalsTriangle underTest = new PascalsTriangle(3);

        assertEquals(20L, underTest.calculate());
    }

    @Test
    void shouldCalculateForAFourByFourGrid() {
        PascalsTriangle underTest = new PascalsTriangle(4);

        assertEquals(70L, underTest.calculate());
    }

    @Test
    void shouldCalculateForAFiveByFiveGrid() {
        PascalsTriangle underTest = new PascalsTriangle(5);

        assertEquals(252L, underTest.calculate());
    }

    //@Disabled
    @Test
    void shouldCalculateForATwentyByTwentyGrid() {
        PascalsTriangle underTest = new PascalsTriangle(20);

        assertEquals(137846528820L, underTest.calculate());
    }
}
