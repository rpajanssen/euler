package com.abnamro.assessment.euler.project19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateInformationTest {

    @Test
    void shouldDetermineNrOfDaysForJanuary() {
        DateInformation underTest = new DateInformation(1, 1, 1973);

        assertEquals(31, underTest.numberOfDaysInMonth());
    }

    @Test
    void shouldDetermineNrOfDaysForFebruary() {
        DateInformation underTest = new DateInformation(1, 2, 1973);

        assertEquals(28, underTest.numberOfDaysInMonth());
    }

    @Test
    void shouldDetermineNrOfDaysForFebruaryInLeapYear() {
        DateInformation underTest = new DateInformation(1, 2, 1988);

        assertEquals(29, underTest.numberOfDaysInMonth());
    }

    @Test
    void shouldDetermineNrOfDaysForFebruaryInYearDividableBy100() {
        DateInformation underTest = new DateInformation(1, 2, 1900);

        assertEquals(28, underTest.numberOfDaysInMonth());
    }

    @Test
    void shouldDetermineNrOfDaysForFebruaryInLeapYearDividableBy400() {
        DateInformation underTest = new DateInformation(1, 2, 2000);

        assertEquals(29, underTest.numberOfDaysInMonth());
    }

    @Test
    void shouldDetermineNrOfDaysForJuli() {
        DateInformation underTest = new DateInformation(1, 7, 1973);

        assertEquals(31, underTest.numberOfDaysInMonth());
    }

    @Test
    void shouldDetermineNrOfDaysForAugust() {
        DateInformation underTest = new DateInformation(1, 8, 1973);

        assertEquals(31, underTest.numberOfDaysInMonth());
    }

    @Test
    void shouldDetermineNrOfDaysForNovember() {
        DateInformation underTest = new DateInformation(1, 11, 1973);

        assertEquals(30, underTest.numberOfDaysInMonth());
    }

    @Test
    void shouldDetermineJanuaryFollowsDecember() {
        DateInformation underTest = new DateInformation(1, 12, 1973);
        assertEquals(1, underTest.nextMonth());
    }

    @Test
    void shouldDetermineJulyFollowsJune() {
        DateInformation underTest = new DateInformation(1, 6, 1973);
        assertEquals(7, underTest.nextMonth());
    }

    @Test
    void shouldDetermineDecemberFollowsNovember() {
        DateInformation underTest = new DateInformation(1, 11, 1973);
        assertEquals(12, underTest.nextMonth());
    }

    @Test
    void shouldIncreaseYearWhenWeReachJanuary() {
        DateInformation underTest = new DateInformation(1, 1, 1901);
        assertEquals(1902, underTest.nextYear());
    }

    @Test
    void shouldNotIncreaseYearWhenWeReachDecember() {
        DateInformation underTest = new DateInformation(1, 12, 1950);
        assertEquals(1950, underTest.nextYear());
    }
}
