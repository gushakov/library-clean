package com.github.libraryclean.core.usecase.profile;

import com.github.libraryclean.core.model.catalog.CatalogEntry;
import com.github.libraryclean.core.model.patron.PatronId;
import com.github.libraryclean.core.ports.ErrorHandlingOutputPort;

import java.util.Set;

/**
 * Presentation port for "consult patron's profile" use case.
 */
public interface ConsultPatronProfilePresenterOutputPort extends ErrorHandlingOutputPort {

    void presentCurrentHoldsOfPatron(PatronId patronId, Set<CatalogEntry> holdsEntries);
}
