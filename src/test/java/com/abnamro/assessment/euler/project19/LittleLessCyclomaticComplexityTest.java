package com.abnamro.assessment.euler.project19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LittleLessCyclomaticComplexityTest {
    @Test
    void shouldCalculateNrOfSundays() {
        assertEquals(171, LittleLessCyclomaticComplexity.calculate());
    }

    @Test
    void shouldDetermineNrOfDaysInMonth() {
        assertEquals(31, LittleLessCyclomaticComplexity.numberOfDaysInMonth(1, 1973));
        assertEquals(28, LittleLessCyclomaticComplexity.numberOfDaysInMonth(2, 1973));
        assertEquals(30, LittleLessCyclomaticComplexity.numberOfDaysInMonth(4, 1973));
        assertEquals(31, LittleLessCyclomaticComplexity.numberOfDaysInMonth(7, 1973));
        assertEquals(31, LittleLessCyclomaticComplexity.numberOfDaysInMonth(8, 1973));
        assertEquals(30, LittleLessCyclomaticComplexity.numberOfDaysInMonth(9, 1973));

        assertEquals(31, LittleLessCyclomaticComplexity.numberOfDaysInMonth(1, 1988));
        assertEquals(29, LittleLessCyclomaticComplexity.numberOfDaysInMonth(2, 1988)); // leap year
        assertEquals(30, LittleLessCyclomaticComplexity.numberOfDaysInMonth(4, 1988));
        assertEquals(31, LittleLessCyclomaticComplexity.numberOfDaysInMonth(7, 1988));
        assertEquals(31, LittleLessCyclomaticComplexity.numberOfDaysInMonth(8, 1988));
        assertEquals(30, LittleLessCyclomaticComplexity.numberOfDaysInMonth(9, 1988));

        assertEquals(28, LittleLessCyclomaticComplexity.numberOfDaysInMonth(2, 1900)); // not a leap year
        assertEquals(29, LittleLessCyclomaticComplexity.numberOfDaysInMonth(2, 2000)); // a leap year
    }
}
