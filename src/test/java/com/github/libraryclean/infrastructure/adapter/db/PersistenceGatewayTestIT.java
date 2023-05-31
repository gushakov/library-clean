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
import com.github.libraryclean.core.model.patron.Patron;
import com.github.libraryclean.core.model.patron.SamplePatrons;
import com.github.libraryclean.infrastructure.LibraryCleanApplication;
import com.github.libraryclean.infrastructure.adapter.db.jdbc.PersistenceGateway;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static com.github.libraryclean.core.model.catalog.SampleCatalog.catalogEntry;

/*
    This is an integration test which requires a running instance
    of a database. See also "V2.0__Add_sample_data.sql" Flyway migration
    script.
 */
@SpringBootTest(classes = {LibraryCleanApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PersistenceGatewayTestIT {

    @Autowired
    private PersistenceGateway persistenceGateway;

    @Test
    void load_sample_catalog_entry() {

        // given

        // a catalog entry matching the one in the database

        CatalogEntry sampleEntry = catalogEntry("0134494164");

        // when

        CatalogEntry loadedEntry = persistenceGateway.loadCatalogEntry(sampleEntry.getIsbn());

        // then

        catalogEntriesMatch(loadedEntry, sampleEntry);
    }


    @Test
    void load_sample_partner_with_hold() {

        // given

        // a patron matching the patron from DB

        Patron samplePatron = SamplePatrons.patronWithHoldStartingAt("hLARqY",
                "0134494164", LocalDate.of(2023, 5, 30));

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
