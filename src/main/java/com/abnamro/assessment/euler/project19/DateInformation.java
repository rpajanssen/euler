package com.abnamro.assessment.euler.project19;

import java.util.Arrays;

public class DateInformation {
    private static final int SUNDAY = 6;

    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int DECEMBER = 12;

    private static final int NR_OF_WEEKDAYS = 7;

    private static final int[] THIRTY_DAY_MONTHS = {4, 6, 9, 11};

    private final int dayOfTheWeek;
    private final int monthOfTheYear;
    private final int year;

    public DateInformation(int dayOfTheWeek, int monthOfTheYear, int year) {
        this.dayOfTheWeek = dayOfTheWeek;
        this.monthOfTheYear = monthOfTheYear;
        this.year = year;
    }

    public boolean isSunday() {
        return dayOfTheWeek == SUNDAY;
    }

    public boolean beforeYear(int givenYear) {
        return this.year < givenYear;
    }

    DateInformation firstDayInNextMonth() {
        return new DateInformation(
                (dayOfTheWeek + numberOfDaysInMonth()) % NR_OF_WEEKDAYS,
                nextMonth(),
                nextYear()
        );
    }

    int numberOfDaysInMonth() {
        if(Arrays.stream(THIRTY_DAY_MONTHS).anyMatch(i -> i == monthOfTheYear)) {
            return 30;
        }

        if (monthOfTheYear == FEBRUARY) {
            return isLeapYear() ? 29 : 28;
        }

        return 31;
    }

    boolean isLeapYear() {
        return year % 4 == 0;
    }

    int nextMonth() {
        if(monthOfTheYear == DECEMBER) {
            return JANUARY;
        }

        return monthOfTheYear + 1;
    }

    int nextYear() {
        if(monthOfTheYear == JANUARY) {
            return year + 1;
        }

        return year;
    }

}
