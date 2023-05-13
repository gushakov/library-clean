package com.github.libraryclean.core.usecase.hold;

import com.github.libraryclean.core.model.InvalidDomainObjectError;
import com.github.libraryclean.core.model.book.Book;
import com.github.libraryclean.core.model.book.BookType;
import com.github.libraryclean.core.model.catalog.Isbn;
import com.github.libraryclean.core.model.patron.*;
import com.github.libraryclean.core.ports.config.ConfigurationOutputPort;
import com.github.libraryclean.core.ports.db.PersistenceError;
import com.github.libraryclean.core.ports.db.PersistenceGatewayOutputPort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@RequiredArgsConstructor
public class HoldBookUseCase implements HoldBookInputPort {

    private final HoldBookPresenterOutputPort presenter;
    private final PersistenceGatewayOutputPort gatewayOps;
    private final ConfigurationOutputPort configOps;

    @Override
    public void holdBook(String patronIdArg, String isbnArg, LocalDate holdStartDate, boolean openEndedHold) {

        /*
            Point of interest
            -----------------
            Declare any variables which may need to be presented
            during successful completion of the use case or any
            error presentation.
         */
        PatronId patronId;
        Isbn isbn;
        Patron patronWithAdditionalHold;

        boolean success = false;
        try {

            // construct and validate IDs for the input arguments
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
                } else {
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

            // look up duration of closed-ended holds and a maximum number of overdue
            // checkouts from the configuration of the application
            Days holdDuration = null;
            if (!openEndedHold) {
                holdDuration = configOps.closedEndedHoldDuration();
            }
            int maxNumOverdueCheckOuts = configOps.maxNumberOverdueCheckoutsForHold();

            try {
                // actual logic for the new hold will be delegated to "Patron" (aggregate)
                patronWithAdditionalHold = patron.holdBook(isbn, LocalDate.now(), holdDuration, maxNumOverdueCheckOuts);
            } catch (InsufficientLevelForHoldTypeError e) {
                throw new RuntimeException(e);
            }

            success = true;
        } catch (Exception e) {
            // present any errors we may have missed
            presenter.presentError(e);
            return;
        } finally {
            /*
                Point of interest
                -----------------
                Don't forget to roll back any current transaction if the use case
                did not complete successfully.
             */
            if (!success) {
                gatewayOps.rollback();
            }
        }

        /*
            Point of interest
            -----------------
            Once the use case completes successfully, present "Patron"
            with the additional hold registered.
         */
        presenter.presentSuccessfulPutOnHoldOfBookForPatron(patronWithAdditionalHold);
    }
}
