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
import lombok.Value;

/**
 * Lease of a {@code Book} to a {@code Patron} by a library for an agreed, fixed duration of time.
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
