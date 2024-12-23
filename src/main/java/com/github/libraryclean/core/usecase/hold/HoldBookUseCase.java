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

import com.github.libraryclean.core.model.InvalidDomainObjectError;
import com.github.libraryclean.core.model.book.Book;
import com.github.libraryclean.core.model.book.BookType;
import com.github.libraryclean.core.model.catalog.Isbn;
import com.github.libraryclean.core.model.patron.*;
import com.github.libraryclean.core.ports.config.ConfigurationOutputPort;
import com.github.libraryclean.core.ports.db.PersistenceError;
import com.github.libraryclean.core.ports.db.PersistenceGatewayOutputPort;
import com.github.libraryclean.core.ports.security.SecurityOperationsOutputPort;
import com.github.libraryclean.core.ports.security.UserNotAuthenticatedError;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Set;

@RequiredArgsConstructor
public class HoldBookUseCase implements HoldBookInputPort {

    /*
        We have borrowed this technique from DDDSample:
        https://github.com/citerus/dddsample-core
     */
    private static final BookType ANY_BOOK_TYPE = null;
    private static final Days DURATION_NOT_APPLICABLE = null;

    private final HoldBookPresenterOutputPort presenter;
    private final SecurityOperationsOutputPort securityOps;
    private final PersistenceGatewayOutputPort gatewayOps;
    private final ConfigurationOutputPort configOps;

    /*
        Note that we are marking this use case as transactional because
        it will involve modifying the persistence store: i.e., when an
        additional hold must be recorded for a patron.
     */
    @Override
    @Transactional
    public void holdBook(String isbnArg, LocalDate holdStartDate, boolean openEndedHold) {

        PatronId patronId;
        Isbn isbn;
        Patron patronWithAdditionalHold;

        // outcome of the use case: success or error
        boolean success = false;
        try {

            /*
                UPDATE, 13.12.2024
                Obtain username of the currently authenticated user via
                an output port.
            */

            // patron ID corresponds to the username of the authenticated user
            try {
                patronId = securityOps.usernameOfLoggedInUser();
            } catch (UserNotAuthenticatedError e) {
                presenter.presentErrorIfUserIsNotAuthenticated();
                return;
            }

            /*
                Construct and validate "Isbn" value object. Create
                "PatronId" corresponding to the username of the currently
                authenticated user.
             */
            try {
                isbn = Isbn.of(isbnArg);
            } catch (InvalidDomainObjectError e) {
                presenter.presentErrorValidatingInput(patronId.getId(), isbnArg);
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
                presenter.presentErrorLoadingPatron(patronId);
                return;
            }

            // find any available book instances depending on the level
            // of the patron
            Set<Book> availableBooks;
            try {
                if (patron.getLevel() == PatronLevel.REGULAR) {
                    // for regular patrons only circulating books may be available
                    availableBooks = gatewayOps.findAvailableBooks(isbn, BookType.CIRCULATING, holdStartDate);
                } else {
                    // for researchers book of any type may be available
                    availableBooks = gatewayOps.findAvailableBooks(isbn, ANY_BOOK_TYPE, holdStartDate);
                }
            } catch (PersistenceError e) {
                presenter.presentError(e);
                return;
            }

            // if there are any books available, this is an error: patron can
            // simply check one of them out
            if (!availableBooks.isEmpty()) {
                presenter.presentErrorOnTryingToPutHoldOnAvailableBook(isbn);
                return;
            }

            // look up duration of closed-ended holds and a maximum number of overdue
            // checkouts from the configuration of the application
            Days holdDuration = DURATION_NOT_APPLICABLE;
            if (!openEndedHold) {
                holdDuration = configOps.closedEndedHoldDuration();
            }

            try {

                /*
                    Point of interest
                    -----------------
                    At this point we have asserted all the preconditions necessary for
                    a book hold logic to be processed by the involved "Patron" aggregate.
                    These are:
                        - inputs (IDs) correspond to valid domain objects
                        - ISBN must correspond to an existing catalog entry
                        - there must not be any books available for checkout
                          to the patron at the date of the hold

                    We have also gathered all the information external to "Patron"
                    aggregate (from application configuration) and which
                    it will require to process its book holding logic. This includes
                    the number of days for closed-ended holds, for example.

                    After this point, it's up to the instance of "Patron" aggregate to
                    perform actual logic related to the book hold. We load the instance
                    of the aggregate and call the corresponding business method providing
                    it with all the necessary parameters.
                 */

                patronWithAdditionalHold = patron.holdBook(isbn, holdStartDate, holdDuration);
            } catch (DuplicateHoldError e) {
                presenter.presentErrorOnDuplicateHold(isbn, patron, e.getHold());
                return;
            } catch (HoldingCheckedOutBookError e) {
                presenter.presentErrorOnHoldingCheckedOutBook(isbn, patron, e.getHold(), e.getCheckOut());
                return;
            } catch (InsufficientPatronLevelForHoldTypeError e) {
                presenter.presentErrorOnInsufficientPatronLevelForHoldType(isbn, patron, e.getHold());
                return;
            } catch (TooManyOverdueCheckoutsError e) {
                presenter.presentErrorOnTooManyOverdueCheckouts(isbn, patron, e.getHold());
                return;
            }

            // persist the modified state: patron with an additional active book hold
            try {
                gatewayOps.savePatron(patronWithAdditionalHold);
            } catch (PersistenceError e) {
                presenter.presentErrorSavingPatronWithAdditionalHold(patronWithAdditionalHold);
                return;
            }

            // use case completed successfully
            success = true;
        } catch (Exception e) {
            // present any errors we may have missed
            presenter.presentError(e);
            return;
        } finally {

            // roll back any current transaction if the use case
            // did not complete successfully
            if (!success) {
                gatewayOps.rollback();
            }
        }

        // present successful outcome of the book holding use case: a patron
        // with additional active hold
        presenter.presentSuccessfulPutOnHoldOfBookForPatron(patronWithAdditionalHold);
    }
}
