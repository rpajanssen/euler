package com.abnamro.assessment.euler.project19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LittleLessCyclomaticComplexityAndCleanTest {
    @Test
    void shouldCalculateNrOfSundays() {
        assertEquals(171, LittleLessCyclomaticComplexityAndClean.calculate());
    }

    @Test
    void shouldDetermineNrOfDaysInMonth() {
        assertEquals(31, LittleLessCyclomaticComplexityAndClean.numberOfDaysInMonth(1, 1973));
        assertEquals(28, LittleLessCyclomaticComplexityAndClean.numberOfDaysInMonth(2, 1973));
        assertEquals(30, LittleLessCyclomaticComplexityAndClean.numberOfDaysInMonth(4, 1973));
        assertEquals(31, LittleLessCyclomaticComplexityAndClean.numberOfDaysInMonth(7, 1973));
        assertEquals(31, LittleLessCyclomaticComplexityAndClean.numberOfDaysInMonth(8, 1973));
        assertEquals(30, LittleLessCyclomaticComplexityAndClean.numberOfDaysInMonth(9, 1973));

        assertEquals(31, LittleLessCyclomaticComplexityAndClean.numberOfDaysInMonth(1, 1988));
        assertEquals(29, LittleLessCyclomaticComplexityAndClean.numberOfDaysInMonth(2, 1988)); // leap year
        assertEquals(30, LittleLessCyclomaticComplexityAndClean.numberOfDaysInMonth(4, 1988));
        assertEquals(31, LittleLessCyclomaticComplexityAndClean.numberOfDaysInMonth(7, 1988));
        assertEquals(31, LittleLessCyclomaticComplexityAndClean.numberOfDaysInMonth(8, 1988));
        assertEquals(30, LittleLessCyclomaticComplexityAndClean.numberOfDaysInMonth(9, 1988));

        assertEquals(28, LittleLessCyclomaticComplexityAndClean.numberOfDaysInMonth(2, 1900)); // not a leap year
        assertEquals(29, LittleLessCyclomaticComplexityAndClean.numberOfDaysInMonth(2, 2000)); // a leap year
    }

    @Test
    void shouldDetermineNextMonth() {
        assertEquals(1, LittleLessCyclomaticComplexityAndClean.nextMonth(12));
        assertEquals(7, LittleLessCyclomaticComplexityAndClean.nextMonth(6));
        assertEquals(12, LittleLessCyclomaticComplexityAndClean.nextMonth(11));
    }

    @Test
    void shouldDetermineNextYear() {
        assertEquals(1902, LittleLessCyclomaticComplexityAndClean.nextYear(1901, 1));
        assertEquals(1950, LittleLessCyclomaticComplexityAndClean.nextYear(1950, 12));
        assertEquals(1955, LittleLessCyclomaticComplexityAndClean.nextYear(1954, 1));
    }
}
