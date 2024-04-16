package com.github.libraryclean.core.usecase.browse;

import com.github.libraryclean.core.model.catalog.CatalogEntry;
import com.github.libraryclean.core.ports.ErrorHandlingOutputPort;

import java.util.Set;

/**
 * Presenter port for "browse catalog" use case.
 */
public interface BrowseCatalogPresenterOutputPort extends ErrorHandlingOutputPort {

    void presentListOfCatalogEntriesAvailableForHolding(Set<CatalogEntry> entries);

}
