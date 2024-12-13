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

import com.github.libraryclean.core.model.catalog.Isbn;
import com.github.libraryclean.core.model.patron.CheckOut;
import com.github.libraryclean.core.model.patron.Hold;
import com.github.libraryclean.core.model.patron.Patron;
import com.github.libraryclean.core.model.patron.PatronId;
import com.github.libraryclean.core.ports.ErrorHandlingOutputPort;

/**
 * Presenter port for "hold book" use case. Declares all presentation methods for successful
 * or erroneous outcomes.
 */
public interface HoldBookPresenterOutputPort extends ErrorHandlingOutputPort {
    void presentErrorValidatingInput(String patronId, String isbn);

    void presentErrorOnAbsentCatalogEntry(Isbn isbn);

    void presentErrorOnTryingToPutHoldOnAvailableBook(Isbn isbn);

    void presentErrorOnInsufficientPatronLevelForHoldType(Isbn isbn, Patron patron, Hold hold);

    void presentErrorOnTooManyOverdueCheckouts(Isbn isbn, Patron patron, Hold hold);

    void presentErrorLoadingPatron(PatronId patronId);

    void presentErrorOnDuplicateHold(Isbn isbn, Patron patron, Hold hold);

    void presentErrorOnHoldingCheckedOutBook(Isbn isbn, Patron patron, Hold hold, CheckOut checkOut);

    void presentErrorSavingPatronWithAdditionalHold(Patron patronWithAdditionalHold);

    void presentSuccessfulPutOnHoldOfBookForPatron(Patron patronWithAdditionalHold);

    void presentErrorIfUserIsNotAuthenticated();
}
