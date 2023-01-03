package com.abnamro.assessment.euler.project19;

public class LittleLessBruteForce {
    private static final int JANUARY = 1;
    private static final int DECEMBER = 12;
    private static final int SUNDAY = 6;

    public static long calculate() {
        int count = 0;

        int dayOfTheWeek = 1; // 1 Jan 1901 was a Tuesday

        for (int year = 1901; year <= 2000; year++) {
            for (int month = JANUARY; month <= DECEMBER; month++) {
                if (dayOfTheWeek == SUNDAY) {
                        count++;
                }

                /*
                 * We start the day of the week on Tuesday 1st Jan 1901. We use zero based
                 * weekday numbers because of the modulo calculation.
                 *
                 * We are only interested in the first day of the month, so if we add the
                 * number of days of the month and then apply modulo by number of days of the week
                 * then the remainder is the day of the week in the next month of the first day
                 * of that month.
                 */
                dayOfTheWeek = (dayOfTheWeek + numberOfDaysInMonth(month, year)) % 7;
            }
        }

        return count;
    }

    static int numberOfDaysInMonth(int month, int year) {
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }

        if (month == 2) {
            return year % 4 == 0 ? 29 : 28;
        }

        return 31;
    }
}
