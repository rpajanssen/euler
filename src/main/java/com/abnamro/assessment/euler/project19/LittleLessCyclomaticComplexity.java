package com.abnamro.assessment.euler.project19;

public class LittleLessCyclomaticComplexity {
    private static final int SUNDAY = 6;

    public static long calculate() {
        int count = 0;

        // given this starting point
        int dayOfTheWeek = 1; // 1 Jan 1901 was a Tuesday
        int monthOfTheYear = 1;
        int year = 1901;

        do {
            if (dayOfTheWeek == SUNDAY) {
                count++;
            }

            // calculate next first day of the week for the next month
            dayOfTheWeek = (dayOfTheWeek + numberOfDaysInMonth(monthOfTheYear, year)) % 7;

            // next month
            if(monthOfTheYear == 12) {
                monthOfTheYear = 1;
                year++;
            } else {
                monthOfTheYear++;
            }
        } while(year <= 2000);

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
