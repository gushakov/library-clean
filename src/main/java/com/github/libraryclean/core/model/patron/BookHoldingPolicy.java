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

/*
    Point of interest
    -----------------

    Modeling number of overdue checkouts as a policy.
    It is a deeply embedded domain constraint which would
    not be easy to change without processing all existing
    "Patron" aggregates and verifying that they are still
    valid (no more than 2 overdue checkouts before a hold
    can be placed).
 */

public interface BookHoldingPolicy {

    /**
     * Verifies if {@code patron} is allowed to put a {@code hold} on a book.
     *
     * @param patron patron trying to put a hold on a book
     * @param hold   hold describing a reservation for a book (catalog entry)
     */
    void verifyPatronAllowedToHold(Patron patron, Hold hold);

}
