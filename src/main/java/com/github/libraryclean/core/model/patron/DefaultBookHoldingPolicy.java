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

/**
 * Implementation of {@link BookHoldingPolicy} which verifies if {@code Patron}
 * has not exceeded the allowed number of overdue checkouts.
 */
public class DefaultBookHoldingPolicy implements BookHoldingPolicy {

    int MAX_NUMBER_OF_OVERDUE_CHECKOUTS = 2;

    @Override
    public void verifyPatronAllowedToHold(Patron patron, Hold hold) {
        // cannot exceed the maximum number of overdue checkouts for a successful hold
        if (patron.overdueCheckOuts(hold.getStartDate()).size() > MAX_NUMBER_OF_OVERDUE_CHECKOUTS) {
            throw new TooManyOverdueCheckoutsError(hold, "Cannot issue any holds after the maximum number of " +
                    "overdue checkouts has been reached");
        }
    }

}
