package com.abnamro.assessment.euler.project19;

/**
 * You are given the following information, but you may prefer to do some research for yourself.
 *
 * 1 Jan 1900 was a Monday.
 * Thirty days has September, April, June and November.
 * All the rest have thirty-one,
 * Saving February alone, which has twenty-eight, rain or shine. And on leap years, twenty-nine.
 * A leap year occurs on any year evenly divisible by 4, but not on a century unless
 * it is divisible by 400.
 * How many Sundays fell on the first of the month during the twentieth century
 * (1 Jan 1901 to 31 Dec 2000)?
 */
public class BruteForce {
    private static final int JANUARY = 1;
    private static final int DECEMBER = 12;
    private static final int MONDAY = 1;
    private static final int SUNDAY = 7;
    private static final int FIRST_DAY_OF_THE_MONTH = 1;

    public static long calculate() {
        int count = 0;

        int dayOfTheWeek = 2; // 1 Jan 1901 was a Tuesday

        for (int year = 1901; year <= 2000; year++) {
            for (int month = JANUARY; month <= DECEMBER; month++) {
                for (int dayOfTheMonth = FIRST_DAY_OF_THE_MONTH; dayOfTheMonth <= numberOfDaysInMonth(month, year); dayOfTheMonth++) {
                    if (dayOfTheWeek == SUNDAY) {
                        if (dayOfTheMonth == FIRST_DAY_OF_THE_MONTH)
                            count++;

                        dayOfTheWeek = MONDAY;
                    } else {
                        dayOfTheWeek++;
                    }
                }
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
