package com.github.libraryclean.core.usecase.hold;

import com.github.libraryclean.core.model.catalog.Isbn;
import com.github.libraryclean.core.model.patron.Patron;

public interface HoldBookPresenterOutputPort {
    void presentError(Throwable error);

    void presentErrorValidatingInput(String patronId, String isbn);

    void presentErrorOnAbsentCatalogEntry(Isbn isbn);

    void presentErrorOnTryToHoldAvailableBook(Isbn isbn);

    void presentSuccessfulPutOnHoldOfBookForPatron(Patron patronWithAdditionalHold);
}
