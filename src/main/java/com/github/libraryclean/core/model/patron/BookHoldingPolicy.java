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
