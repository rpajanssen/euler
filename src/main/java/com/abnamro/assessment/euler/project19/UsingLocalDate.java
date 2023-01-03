package com.abnamro.assessment.euler.project19;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class UsingLocalDate {
    private static final LocalDate END_DATE = LocalDate.of(2000, Month.DECEMBER, 31);

    public static long calculate() {
        int count = 0;

        LocalDate date = LocalDate.of(1901, Month.JANUARY, 1);

        do {
            if(date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                count++;
            }
            date = date.plusMonths(1);
        } while (date.isBefore(END_DATE));


        return count;
    }
}
