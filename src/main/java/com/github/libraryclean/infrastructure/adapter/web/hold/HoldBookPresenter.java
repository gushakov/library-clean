package com.github.libraryclean.infrastructure.adapter.web.hold;

import com.github.libraryclean.core.model.catalog.Isbn;
import com.github.libraryclean.core.model.patron.CheckOut;
import com.github.libraryclean.core.model.patron.Hold;
import com.github.libraryclean.core.model.patron.Patron;
import com.github.libraryclean.core.model.patron.PatronId;
import com.github.libraryclean.core.usecase.hold.HoldBookPresenterOutputPort;
import com.github.libraryclean.infrastructure.adapter.web.AbstractWebPresenter;
import com.github.libraryclean.infrastructure.adapter.web.LocalDispatcherServlet;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Presenter which will be called by the "hold book" use case to present results of a successful outcome
 * or any erroneous outcomes.
 *
 * @see AbstractWebPresenter
 */
@Component
@Scope(scopeName = WebApplicationContext.SCOPE_REQUEST)
public class HoldBookPresenter extends AbstractWebPresenter implements HoldBookPresenterOutputPort {
    public HoldBookPresenter(LocalDispatcherServlet dispatcher, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        super(dispatcher, httpRequest, httpResponse);
    }

    @Override
    public void presentErrorValidatingInput(String patronId, String isbn) {
        // TODO: not yet implemented
        throw new UnsupportedOperationException("Present error...");
    }

    @Override
    public void presentErrorOnAbsentCatalogEntry(Isbn isbn) {
        // TODO: not yet implemented
        throw new UnsupportedOperationException("Present error...");
    }

    @Override
    public void presentErrorOnTryingToPutHoldOnAvailableBook(Isbn isbn) {
        // TODO: not yet implemented
        throw new UnsupportedOperationException("Present error...");
    }

    @Override
    public void presentErrorOnInsufficientPatronLevelForHoldType(Isbn isbn, Patron patron, Hold hold) {
        // TODO: not yet implemented
        throw new UnsupportedOperationException("Present error...");
    }

    @Override
    public void presentErrorOnTooManyOverdueCheckouts(Isbn isbn, Patron patron, Hold hold) {
        // TODO: not yet implemented
        throw new UnsupportedOperationException("Present error...");
    }

    @Override
    public void presentErrorLoadingPatron(PatronId patronId) {
        // TODO: not yet implemented
        throw new UnsupportedOperationException("Present error...");
    }

    @Override
    public void presentErrorOnDuplicateHold(Isbn isbn, Patron patron, Hold hold) {
        // here we are presenting error on duplicate hold
        redirectError("Patron with ID: %s already has a hold on entry with ISBN: %s"
                .formatted(patron.getPatronId(), isbn));
    }

    @Override
    public void presentErrorOnHoldingCheckedOutBook(Isbn isbn, Patron patron, Hold hold, CheckOut checkOut) {
        // TODO: not yet implemented
        throw new UnsupportedOperationException("Present error...");
    }

    @Override
    public void presentErrorSavingPatronWithAdditionalHold(Patron patronWithAdditionalHold) {
        // TODO: not yet implemented
        throw new UnsupportedOperationException("Present error...");
    }

    @Override
    public void presentSuccessfulPutOnHoldOfBookForPatron(Patron patronWithAdditionalHold) {
        // redirect to patron's holds view, usual POST + redirect + GET technique
        redirect("/patronHolds", Map.of("patronId", patronWithAdditionalHold.getPatronId().getId()));
    }

    @Override
    public void presentErrorIfUserIsNotAuthenticated() {
        // TODO: not yet implemented
        throw new UnsupportedOperationException("Present error...");
    }
}
