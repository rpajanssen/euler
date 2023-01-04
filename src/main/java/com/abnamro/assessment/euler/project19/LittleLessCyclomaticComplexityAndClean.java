package com.abnamro.assessment.euler.project19;

public class LittleLessCyclomaticComplexityAndClean {
    private static final int SUNDAY = 6;

    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int DECEMBER = 12;

    private static final int NR_OF_WEEKDAYS = 7;

    public static long calculate() {
        int count = 0;

        // given this starting point
        int dayOfTheWeek = 1; // 1 Jan 1901 was a Tuesday
        int monthOfTheYear = JANUARY;
        int year = 1901;

        do {
            if (dayOfTheWeek == SUNDAY) {
                count++;
            }

            // calculate next first day of the week for the next month
            dayOfTheWeek = (dayOfTheWeek + numberOfDaysInMonth(monthOfTheYear, year)) % NR_OF_WEEKDAYS;

            // extracted the logic to methods having only one responsibility
            // ... but it created a temporal dependency... you have to call nextMonth before
            // calling nextYear
            monthOfTheYear = nextMonth(monthOfTheYear);
            year = nextYear(year, monthOfTheYear);
        } while(year <= 2000);

        return count;
    }

    static int nextMonth(int monthOfTheYear) {
        if(monthOfTheYear == DECEMBER) {
            return JANUARY;
        }

        return ++monthOfTheYear;
    }

    static int nextYear(int year, int monthOfTheYear) {
        if(monthOfTheYear == JANUARY) {
            return ++year;
        }

        return year;
    }

    static int numberOfDaysInMonth(int month, int year) {
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }

        if (month == FEBRUARY) {
            return year % 4 == 0 || year % 400 == 0 && year % 100 != 0 ? 29 : 28;
        }

        return 31;
    }
}
