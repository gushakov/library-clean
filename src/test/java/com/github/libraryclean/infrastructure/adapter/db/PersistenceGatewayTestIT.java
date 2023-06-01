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
import com.github.libraryclean.core.model.catalog.Isbn;
import com.github.libraryclean.core.model.catalog.SampleCatalog;
import com.github.libraryclean.core.model.patron.Patron;
import com.github.libraryclean.core.model.patron.SamplePatrons;
import com.github.libraryclean.infrastructure.LibraryCleanApplication;
import com.github.libraryclean.infrastructure.adapter.db.jdbc.PersistenceGateway;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static com.github.libraryclean.core.model.catalog.SampleCatalog.catalogEntry;

/*
    This is an integration test which requires a running instance
    of a database.
 */
@SpringBootTest(classes = {LibraryCleanApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class PersistenceGatewayTestIT {

    @Autowired
    private PersistenceGateway persistenceGateway;

    @Test
    void save_sample_catalog_entry() {

        // given

        // a sample catalog entry
        String isbn = "0134494164";
        CatalogEntry sampleEntry = catalogEntry(isbn);

        // when

        persistenceGateway.saveCatalogEntry(sampleEntry);
        CatalogEntry loadedCatalogEntry = persistenceGateway.loadCatalogEntry(Isbn.of(isbn));

        // then

        catalogEntriesMatch(sampleEntry, loadedCatalogEntry);
    }

    @Test
    void save_sample_patron_with_hold() {

        // given

        // a catalog entry in the DB
        String isbn = "0134494164";
        persistenceGateway.saveCatalogEntry(SampleCatalog.catalogEntry(isbn));

        // and a sample patron with a hold for the same catalog entry
        Patron samplePatron = SamplePatrons.patronWithHoldStartingAt("hLARqY",
                isbn, LocalDate.of(2023, 5, 30));

        // when

        persistenceGateway.savePatron(samplePatron);
        Patron loadedPatron = persistenceGateway.loadPatron(samplePatron.getPatronId());

        // then
        patronsMatch(samplePatron, loadedPatron);

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
