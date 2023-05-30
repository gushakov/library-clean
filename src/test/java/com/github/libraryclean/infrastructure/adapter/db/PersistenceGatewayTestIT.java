/*
    COPYRIGHT DISCLAIMER
    --------------------

    The code in this file may be based on the original work from
    [ddd-by-examples/library](https://github.com/ddd-by-examples/library).

    Please see the original licence at
    https://github.com/ddd-by-examples/library/blob/master/LICENSE

    and the copyright disclaimer notice in "README.md" (in this repository).
 */

package com.github.libraryclean.infrastructure.adapter.db;

import com.github.libraryclean.core.model.catalog.CatalogEntry;
import com.github.libraryclean.core.model.catalog.SampleCatalog;
import com.github.libraryclean.core.model.patron.Patron;
import com.github.libraryclean.core.model.patron.SamplePatrons;
import com.github.libraryclean.infrastructure.LibraryCleanApplication;
import com.github.libraryclean.infrastructure.adapter.db.jdbc.PersistenceGateway;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {LibraryCleanApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PersistenceGatewayTestIT {

    @Autowired
    private PersistenceGateway persistenceGateway;

    @Test
    void load_sample_catalog_entry() {

        // given

        CatalogEntry sampleEntry = SampleCatalog.catalogEntry1FromDb();
        // and successfully loaded sample entries with Flyway migration script

        // when

        CatalogEntry loadedEntry = persistenceGateway.loadCatalogEntry(sampleEntry.getIsbn());

        // then

        catalogEntriesMatch(loadedEntry, sampleEntry);
    }


    @Test
    void load_sample_partner_with_hold() {

        // given

        Patron samplePatron = SamplePatrons.patron1FromDb();

        // when

        Patron loadedPatron = persistenceGateway.loadPatron(samplePatron.getPatronId());

        // then

        patronsMatch(loadedPatron, samplePatron);

    }

    private void patronsMatch(Patron patron, Patron anotherPatron) {

        Assertions.assertThat(patron)
                .extracting(Patron::getPatronId, Patron::getFullName, Patron::getLevel)
                .containsExactly(anotherPatron.getPatronId(), anotherPatron.getFullName(),
                        anotherPatron.getLevel());

    }

    private void catalogEntriesMatch(CatalogEntry catalogEntry, CatalogEntry anotherCatalogEntry) {
        Assertions.assertThat(catalogEntry)
                .extracting(CatalogEntry::getIsbn, CatalogEntry::getTitle, CatalogEntry::getAuthor)
                .containsExactly(anotherCatalogEntry.getIsbn(), anotherCatalogEntry.getTitle(), anotherCatalogEntry.getAuthor());
    }
}
