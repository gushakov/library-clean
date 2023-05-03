package com.github.libraryclean.core.model.patron;

import com.github.libraryclean.core.model.InvalidDomainObjectError;
import lombok.*;

import java.time.Duration;
import java.time.LocalDate;

/**
 * Duration in days.
 */
@Value
public class Days {

    @Getter(AccessLevel.NONE)
    Duration duration;

    public static Days of(int days){
        return Days.builder()
                .days(days)
                .build();
    }

    @Builder
    private Days(int days){
        try {
            this.duration = Duration.ofDays(Long.parseLong(String.valueOf(days)));
        } catch (NumberFormatException e) {
            throw new InvalidDomainObjectError(e.getMessage());
        }
    }

    /**
     * Calculates the date resulting from adding this number of days to the given date.
     * @param date from date
     * @return end date
     */
    public LocalDate addToDate(LocalDate date){
        return date.plus(duration);
    }

}
