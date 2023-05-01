package com.github.libraryclean.core.model.patron;

import lombok.Value;

import java.time.Instant;

/**
 * Hold represents a reservation placed by a patron on a book currently checked out
 * by someone else. Once the book becomes available, it may only be checked out
 * by the patron who has placed the hold on it. Only one patron can hold any one
 * book at any particular moment.
 */
@Value
public class Hold {

    /**
     * Type of hold: "open-ended" or "closed-ended".
     */
    HoldType duration;

    /**
     * Instant when hold was placed.
     */
    Instant from;

}
