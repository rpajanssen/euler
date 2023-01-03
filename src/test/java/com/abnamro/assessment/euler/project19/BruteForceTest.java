package com.abnamro.assessment.euler.project19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BruteForceTest {

    @Test
    void shouldCalculateNrOfSundays() {
        assertEquals(171, BruteForce.calculate());
    }

    @Test
    void shouldDetermineNrOfDaysInMonth() {
        assertEquals(31, BruteForce.numberOfDaysInMonth(1, 1973));
        assertEquals(28, BruteForce.numberOfDaysInMonth(2, 1973));
        assertEquals(30, BruteForce.numberOfDaysInMonth(4, 1973));
        assertEquals(31, BruteForce.numberOfDaysInMonth(7, 1973));
        assertEquals(31, BruteForce.numberOfDaysInMonth(8, 1973));
        assertEquals(30, BruteForce.numberOfDaysInMonth(9, 1973));

        assertEquals(31, BruteForce.numberOfDaysInMonth(1, 1988));
        assertEquals(29, BruteForce.numberOfDaysInMonth(2, 1988)); // leap year
        assertEquals(30, BruteForce.numberOfDaysInMonth(4, 1988));
        assertEquals(31, BruteForce.numberOfDaysInMonth(7, 1988));
        assertEquals(31, BruteForce.numberOfDaysInMonth(8, 1988));
        assertEquals(30, BruteForce.numberOfDaysInMonth(9, 1988));
    }
}
