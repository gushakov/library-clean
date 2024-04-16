package com.github.libraryclean.core.usecase.browse;

import com.github.libraryclean.core.model.catalog.CatalogEntry;
import com.github.libraryclean.core.ports.db.PersistenceGatewayOutputPort;
import lombok.RequiredArgsConstructor;

import java.util.Set;

/**
 * Use case for browsing the catalog for entries available to be placed on hold
 * by a patron. For this example we simply load all catalog entries and present
 * them.
 */
@RequiredArgsConstructor
public class BrowseCatalogUseCase implements BrowseCatalogInputPort {

    private final BrowseCatalogPresenterOutputPort presenter;

    private final PersistenceGatewayOutputPort gatewayOps;

    @Override
    public void browseCatalogEntries() {
        final Set<CatalogEntry> entries;
        try {
            entries = gatewayOps.loadAllCatalogEntries();
        } catch (Exception e) {
            presenter.presentError(e);
            return;
        }
        presenter.presentListOfCatalogEntriesAvailableForHolding(entries);
    }
}
