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

import com.github.libraryclean.core.model.book.BookId;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

import static com.github.libraryclean.core.Validator.notNull;

/**
 * Lease of a {@code Book} to a {@code Patron} by a library for an agreed, fixed duration of time.
 * A {@code CheckOut} is considered overdue, at the moment the lease period has ended and if the
 * book has not been returned to the library.
 */
@Value
public class CheckOut {

    /**
     * ID of the checked out book instance.
     */
    BookId bookId;

    /**
     * Date this checkout has started.
     */
    LocalDate startDate;

    /**
     * Duration of this checkout.
     */
    Days duration;

    /**
     * Date the checkout book was actually returned.
     */
    LocalDate actualReturnDate;

    public static CheckOut of(BookId bookId, LocalDate startDate, Days duration) {
        return new CheckOutBuilder()
                .bookId(bookId)
                .startDate(startDate)
                .duration(duration)
                .build();
    }

    @Builder
    private CheckOut(BookId bookId, LocalDate startDate, Days duration, LocalDate actualReturnDate) {
        this.bookId = notNull(bookId);
        this.startDate = notNull(startDate);
        this.duration = notNull(duration);

        // can be null
        this.actualReturnDate = actualReturnDate;
    }

    public LocalDate scheduledReturnDate() {
        return duration.addToDate(startDate);
    }

    /**
     * Returns new checkout for the returned book.
     *
     * @param returnDate date the checked out book was returned
     * @return new checkout
     */
    public CheckOut returnBook(LocalDate returnDate) {

        // cannot return on the date before this checkout started
        if (notNull(returnDate).isBefore(startDate)) {
            throw new InvalidCheckOutStateError("Cannot return a checkout on the date before the checkout started");
        }

        return newCheckOut()
                .actualReturnDate(returnDate)
                .build();
    }

    /**
     * Returns {@code true} if this check-out was completed, i.e. book has been returned.
     *
     * @return {@code true} if this check-out was completed
     */
    public boolean isBookReturned() {
        return actualReturnDate != null;
    }

    /**
     * Returns whether this check-out is overdue for the given date.
     *
     * @return {@code true} if this check-out is overdue
     */
    public boolean isOverdue(LocalDate atDate) {
        return !isBookReturned() && notNull(atDate).isAfter(scheduledReturnDate());
    }

    private CheckOutBuilder newCheckOut() {
        return new CheckOutBuilder()
                .bookId(bookId)
                .startDate(startDate)
                .duration(duration)
                .actualReturnDate(actualReturnDate);
    }

}
