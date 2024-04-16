package com.github.libraryclean.core.usecase.profile;


import com.github.libraryclean.core.model.catalog.CatalogEntry;
import com.github.libraryclean.core.model.catalog.Isbn;
import com.github.libraryclean.core.model.patron.Hold;
import com.github.libraryclean.core.model.patron.Patron;
import com.github.libraryclean.core.model.patron.PatronId;
import com.github.libraryclean.core.ports.db.PersistenceGatewayOutputPort;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Use case for a patron consulting her profile. Lists all holds currently placed by the patron.
 */
@RequiredArgsConstructor
public class ConsultPatronProfileUseCase implements ConsultPatronProfileInputPort {

    private final ConsultPatronProfilePresenterOutputPort presenter;

    private final PersistenceGatewayOutputPort gatewayOps;

    @Override
    public void listCurrentHolds(String patronId) {

        Patron patron;
        Set<CatalogEntry> holdsEntries;

        try {
            // load patron
            patron = gatewayOps.loadPatron(PatronId.of(patronId));

            // Load all catalog entries corresponding to the ISBNs of the holds. We need them for
            // presentation. We may want to sort them by hold start date.

            Set<Isbn> holdsIsbns = patron.getHolds().stream().map(Hold::getIsbn).collect(Collectors.toUnmodifiableSet());
            holdsEntries = gatewayOps.loadAllCatalogEntries()
                    .stream()
                    .filter(catalogEntry -> holdsIsbns.contains(catalogEntry.getIsbn()))
                    .collect(Collectors.toUnmodifiableSet());

        } catch (Exception e) {

            // Normally we would want to distinguish errors here and present them differently.
            // There are could be "InvalidDomainObjectError", "PersistenceError".

            presenter.presentError(e);
            return;
        }

        presenter.presentCurrentHoldsOfPatron(patron.getPatronId(), holdsEntries);

    }
}
