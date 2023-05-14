package com.github.libraryclean.core.usecase.hold;

import com.github.libraryclean.core.model.catalog.Isbn;
import com.github.libraryclean.core.model.patron.Hold;
import com.github.libraryclean.core.model.patron.Patron;
import com.github.libraryclean.core.model.patron.PatronId;

public interface HoldBookPresenterOutputPort {
    void presentError(Throwable error);

    void presentErrorValidatingInput(String patronId, String isbn);

    void presentErrorOnAbsentCatalogEntry(Isbn isbn);

    void presentErrorOnTryingToPutHoldOnAvailableBook(Isbn isbn);

    void presentErrorOnInsufficientPatronLevelForHoldType(Isbn isbn, Patron patron, Hold hold);

    void presentErrorOnTooManyOverdueCheckouts(Isbn isbn, Patron patron, Hold hold);

    void presentErrorLoadingPatron(PatronId patronId);

    void presentErrorSavingPatronWithAdditionalHold(Patron patronWithAdditionalHold);

    void presentSuccessfulPutOnHoldOfBookForPatron(Patron patronWithAdditionalHold);
}
