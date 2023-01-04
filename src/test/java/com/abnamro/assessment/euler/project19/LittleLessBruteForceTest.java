package com.abnamro.assessment.euler.project19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LittleLessBruteForceTest {

    @Test
    void shouldCalculateNrOfSundays() {
        assertEquals(171, LittleLessBruteForce.calculate());
    }

    @Test
    void shouldDetermineNrOfDaysInMonth() {
        assertEquals(31, LittleLessBruteForce.numberOfDaysInMonth(1, 1973));
        assertEquals(28, LittleLessBruteForce.numberOfDaysInMonth(2, 1973));
        assertEquals(30, LittleLessBruteForce.numberOfDaysInMonth(4, 1973));
        assertEquals(31, LittleLessBruteForce.numberOfDaysInMonth(7, 1973));
        assertEquals(31, LittleLessBruteForce.numberOfDaysInMonth(8, 1973));
        assertEquals(30, LittleLessBruteForce.numberOfDaysInMonth(9, 1973));

        assertEquals(31, LittleLessBruteForce.numberOfDaysInMonth(1, 1988));
        assertEquals(29, LittleLessBruteForce.numberOfDaysInMonth(2, 1988)); // leap year
        assertEquals(30, LittleLessBruteForce.numberOfDaysInMonth(4, 1988));
        assertEquals(31, LittleLessBruteForce.numberOfDaysInMonth(7, 1988));
        assertEquals(31, LittleLessBruteForce.numberOfDaysInMonth(8, 1988));
        assertEquals(30, LittleLessBruteForce.numberOfDaysInMonth(9, 1988));

        assertEquals(28, LittleLessBruteForce.numberOfDaysInMonth(2, 1900)); // not a leap year
        assertEquals(29, LittleLessBruteForce.numberOfDaysInMonth(2, 2000)); // a leap year
    }
}
