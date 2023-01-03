package com.abnamro.assessment.euler.project19;

public class LittleLessCyclomaticComplexityAndEvenCleaner {
    public static long calculate() {
        int count = 0;

        DateInformation dateInfo = new DateInformation(1, 1, 1901);

        do {
            if (dateInfo.isSunday()) {
                count++;
            }

            dateInfo = dateInfo.firstDayInNextMonth();
        } while(dateInfo.beforeYear(2001));

        return count;
    }
}
