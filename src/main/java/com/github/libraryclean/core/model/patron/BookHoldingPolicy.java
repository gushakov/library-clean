package com.github.libraryclean.core.model.patron;

public interface BookHoldingPolicy {

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

    int MAX_NUMBER_OF_OVERDUE_CHECKOUTS = 2;

    default void verifyPatronAllowedToHold(Patron patron, Hold hold) {
        // cannot exceed the maximum number of overdue checkouts for a successful hold
        if (patron.overdueCheckOuts(hold.getStartDate()).size() > MAX_NUMBER_OF_OVERDUE_CHECKOUTS) {
            throw new TooManyOverdueCheckoutsError(hold, "Cannot issue any holds after the maximum number of " +
                    "overdue checkouts has been reached");
        }
    }

}
