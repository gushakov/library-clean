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
