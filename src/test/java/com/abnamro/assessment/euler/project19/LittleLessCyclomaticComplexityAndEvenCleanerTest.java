package com.abnamro.assessment.euler.project19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LittleLessCyclomaticComplexityAndEvenCleanerTest {
    @Test
    void shouldCalculateNrOfSundays() {
        assertEquals(171, LittleLessCyclomaticComplexityAndEvenCleaner.calculate());
    }
}
