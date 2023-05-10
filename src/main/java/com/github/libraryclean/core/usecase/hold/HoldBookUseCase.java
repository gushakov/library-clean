package com.github.libraryclean.core.usecase.hold;

import com.github.libraryclean.core.model.InvalidDomainObjectError;
import com.github.libraryclean.core.model.book.Book;
import com.github.libraryclean.core.model.book.BookType;
import com.github.libraryclean.core.model.catalog.Isbn;
import com.github.libraryclean.core.model.patron.Days;
import com.github.libraryclean.core.model.patron.Patron;
import com.github.libraryclean.core.model.patron.PatronId;
import com.github.libraryclean.core.model.patron.PatronLevel;
import com.github.libraryclean.core.ports.db.PersistenceError;
import com.github.libraryclean.core.ports.db.PersistenceGatewayOutputPort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@RequiredArgsConstructor
public class HoldBookUseCase implements HoldBookInputPort {

    private final HoldBookPresenterOutputPort presenter;
    private final PersistenceGatewayOutputPort gatewayOps;

    @Override
    public void holdBook(String patronIdArg, String isbnArg, boolean openEndedHold) {

        PatronId patronId;
        Isbn isbn;

        // construct and validate IDs
        try {
            patronId = PatronId.of(patronIdArg);
            isbn = Isbn.of(isbnArg);
        } catch (InvalidDomainObjectError e) {
            presenter.presentErrorValidatingInput(patronIdArg, isbnArg);
            return;
        }

        // make sure book exists in the catalog
        if (!gatewayOps.existsInCatalog(isbn)) {
            presenter.presentErrorOnAbsentCatalogEntry(isbn);
            return;
        }

        // load patron
        Patron patron;
        try {
            patron = gatewayOps.loadPatron(patronId);
        } catch (PersistenceError e) {
            presenter.presentError(e);
            return;
        }

        // find any available book instances depending on the level
        // of the patron
        Set<Book> availableBooks;
        try {
            if (patron.getLevel() == PatronLevel.REGULAR) {
                // for regular patrons only circulating books may be available
                availableBooks = gatewayOps.findAvailableBooks(isbn, BookType.CIRCULATING);
            }
            else {
                // for researchers book of any type may be available
                availableBooks = gatewayOps.findAvailableBooks(isbn, null);
            }
        } catch (PersistenceError e) {
            presenter.presentError(e);
            return;
        }

        // if there are any books available, this is an error: patron can
        // simply check one of them out
        if (!availableBooks.isEmpty()) {
            presenter.presentErrorOnTryToHoldAvailableBook(isbn);
            return;
        }

        // determine the duration of the hold and delegate book holding operation
        // to "Patron" (aggregate)
        Days holdDuration = null;
        if (!openEndedHold){
            holdDuration = Days.of(30);
        }
        Patron patronWithAdditionalHold = patron.holdBook(isbn, LocalDate.now(), holdDuration);
    }
}
