package com.github.libraryclean.core.model.patron;

import com.github.libraryclean.core.model.catalog.Isbn;
import lombok.Value;

/**
 * Lease of a {@code Book} to a {@code Partner} by a library for an agreed, fixed duration of time.
 * A {@code CheckOut} is considered overdue, at the moment the lease period has ended and if the
 * book has not been returned to the library.
 */
@Value
public class CheckOut {

    /**
     * ISBN of the checked out book
     */
    Isbn isbn;

}
