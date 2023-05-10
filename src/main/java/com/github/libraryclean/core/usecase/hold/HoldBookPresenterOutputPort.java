package com.github.libraryclean.core.usecase.hold;

import com.github.libraryclean.core.GenericLibraryError;
import com.github.libraryclean.core.model.catalog.Isbn;

public interface HoldBookPresenterOutputPort {
    void presentError(GenericLibraryError error);

    void presentErrorValidatingInput(String patronId, String isbn);

    void presentErrorOnAbsentCatalogEntry(Isbn isbn);

    void presentErrorOnTryToHoldAvailableBook(Isbn isbn);
}
