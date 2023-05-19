/*
    COPYRIGHT DISCLAIMER
    --------------------

    The code in this file may be based on the original work from
    [ddd-by-examples/library](https://github.com/ddd-by-examples/library).

    Please see the original licence at
    https://github.com/ddd-by-examples/library/blob/master/LICENSE

    and the copyright disclaimer notice in "README.md" (in this repository).
 */

package com.github.libraryclean.core.model;

import java.time.LocalDate;

public class SampleDates {

    public static LocalDate anyDate() {
        return LocalDate.now();
    }

    public static LocalDate dayLater(LocalDate date) {
        return date.plusDays(1);
    }

    public static LocalDate dayBefore(LocalDate date) {
        return date.minusDays(1);
    }

    public static LocalDate daysBefore(LocalDate date, int days) {
        return date.minusDays(days);
    }

}
