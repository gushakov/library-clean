/*
    COPYRIGHT DISCLAIMER
    --------------------

    The code in this file may be based on the original work from
    [ddd-by-examples/library](https://github.com/ddd-by-examples/library).

    Please see the original licence at
    https://github.com/ddd-by-examples/library/blob/master/LICENSE

    and the copyright disclaimer notice in "README.md" (in this repository).
 */

package com.github.libraryclean.core.usecase.hold;

import java.time.LocalDate;

public interface HoldBookInputPort {

    /**
     * Use case when a patron puts a hold on a book with a given ISBN.
     *
     * @param patronIdArg   ID of the patron
     * @param isbnArg       ISBN of the catalog entry for a book to be put on hold
     * @param holdStartDate date on which the hold will start
     * @param openEndedHold whether a hold is open-ended or not
     */
    void holdBook(String patronIdArg, String isbnArg, LocalDate holdStartDate, boolean openEndedHold);

}
