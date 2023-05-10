/*
    COPYRIGHT DISCLAIMER
    --------------------

    The code in this file may be based on the original work from
    [ddd-by-examples/library](https://github.com/ddd-by-examples/library).

    Please see the original licence at
    https://github.com/ddd-by-examples/library/blob/master/LICENSE

    and the copyright disclaimer notice in "README.md" (in this repository).
 */

package com.github.libraryclean.core.model.patron;

import com.github.libraryclean.core.model.InvalidDomainObjectError;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;

import static com.github.libraryclean.core.Validator.notNull;
import static com.github.libraryclean.core.Validator.positive;

/**
 * Duration in days.
 */
@Value
public class Days {

    @Getter(AccessLevel.NONE)
    int numberOfDays;

    public static Days of(int days) {
        return Days.builder()
                .days(days)
                .build();
    }

    @Builder
    private Days(int days) {
        try {
            this.numberOfDays = positive(days);
        } catch (NumberFormatException e) {
            throw new InvalidDomainObjectError("Cannot parse days from %d. %s".formatted(days,
                    e.getMessage()));
        }
    }

    /**
     * Calculates the date resulting from adding this number of days to the given date.
     *
     * @param date start date
     * @return end date
     */
    public LocalDate addToDate(LocalDate date) {
        return notNull(date).plusDays(this.numberOfDays);
    }

}
