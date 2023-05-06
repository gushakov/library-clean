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

import com.github.libraryclean.core.model.catalog.Isbn;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.Optional;

import static com.github.libraryclean.core.Validator.notNull;

/**
 * Book hold is a reservation placed by a patron on the book which is not
 * currently available.
 */
@Value
public class Hold {

    /**
     * ISBN of the book on hold.
     */
    Isbn isbn;

    /**
     * Date when this hold was placed.
     */
    LocalDate startDate;

    /**
     * Duration of this hold in days. Can be {@code null} for open-ended holds.
     */
    Days duration;

    /**
     * Date when this hold was completed.
     */
    LocalDate dateCompleted;

    /**
     * Date when this hold was canceled.
     */
    LocalDate dateCanceled;

    /**
     * Constructs and returns a new instance of an open-ended hold.
     *
     * @param isbn      ISBN of the book on hold
     * @param startDate date this hold starts from
     * @return new hold
     */
    public static Hold of(Isbn isbn, LocalDate startDate) {
        return Hold.builder()
                .isbn(isbn)
                .startDate(startDate)
                .build();
    }

    /**
     * Constructs and returns a new instance of a closed-ended hold.
     *
     * @param isbn     ISBN of the book on hold
     * @param fromDate date this hold starts from
     * @param duration duration of the hold in days
     * @return new hold
     */
    public static Hold of(Isbn isbn, LocalDate fromDate, Days duration) {
        return Hold.builder()
                .isbn(isbn)
                .startDate(fromDate)
                .duration(duration)
                .build();
    }

    @Builder
    private Hold(Isbn isbn, LocalDate startDate, Days duration, LocalDate dateCompleted,
                 LocalDate dateCanceled) {
        this.isbn = notNull(isbn);
        this.startDate = notNull(startDate);

        this.duration = duration;
        this.dateCompleted = dateCompleted;
        this.dateCanceled = dateCanceled;
    }


    /**
     * Type of the hold depending on whether {@link #duration} of the hold
     * was specified or not.
     *
     * @return type of the hold.
     */
    public HoldType type() {
        if (duration == null) {
            return HoldType.OPEN_ENDED;
        } else {
            return HoldType.CLOSED_ENDED;
        }
    }

    /**
     * For closed-ended hold this is the date when the hold is scheduled to end.
     * For open-ended hold this will return an empty {@code Optional}.
     *
     * @return optional date when the hold is scheduled to end
     */
    public Optional<LocalDate> scheduledEndDate() {
        if (type() == HoldType.CLOSED_ENDED) {
            return Optional.of(duration.addToDate(startDate));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Whether this hold was completed: i.e. during the checkout of the book involved
     * in this hold.
     *
     * @return {@code true} if the hold was completed normally
     */
    public boolean wasCompleted() {
        return Optional.ofNullable(dateCompleted).isPresent();
    }

    /**
     * Whether this hold was canceled
     *
     * @return {@code true} if this hold was canceled
     */
    public boolean wasCanceled() {
        return Optional.ofNullable(dateCanceled).isPresent();
    }

    /**
     * Returns whether this hold is expired.
     *
     * @return whether this hold is expired
     */
    public boolean hasExpired() {
        return scheduledEndDate()
                .map(endDate -> LocalDate.now().isAfter(startDate))
                .orElse(false);
    }

    /**
     * Whether this hold is still active.
     *
     * @return {@code true} if this hold is still active
     */
    public boolean isActive() {
        return !hasExpired() && !wasCompleted() && !wasCanceled();
    }

    /**
     * Cancels this hold.
     *
     * @param dateCanceled date this hold was canceled
     * @return new (canceled) hold
     * @throws InvalidHoldStateError if hold is active or {@code dateCanceled} is invalid
     */
    public Hold cancel(LocalDate dateCanceled) {
        // can only cancel hold if the cancellation date is posterior to the date of the start of the hold
        if (notNull(dateCanceled).isBefore(startDate)) {
            throw new InvalidHoldStateError("Cancellation date: %s must be posterior to the start date: %s"
                    .formatted(dateCanceled, startDate));
        }

        // can only cancel an active hold
        if (isActive()) {
            return newHold()
                    .dateCanceled(dateCanceled)
                    .build();
        } else {
            throw new InvalidHoldStateError("Inactive hold cannot be canceled");
        }
    }

    private HoldBuilder newHold() {
        return new HoldBuilder()
                .isbn(isbn)
                .startDate(startDate)
                .duration(duration)
                .dateCanceled(dateCanceled)
                .dateCompleted(dateCompleted);
    }
}
