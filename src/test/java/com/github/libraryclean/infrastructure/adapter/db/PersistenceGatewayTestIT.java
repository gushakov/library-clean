package com.github.libraryclean.infrastructure.adapter.db;

import com.github.libraryclean.core.model.catalog.CatalogEntry;
import com.github.libraryclean.core.model.catalog.SampleCatalog;
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

        CatalogEntry sampleEntry = SampleCatalog.catalogEntry("0134494164");
        // and successfully loaded sample entries with Flyway migration script

        // when

        CatalogEntry loadedEntry = persistenceGateway.loadCatalogEntry(sampleEntry.getIsbn());

        // then

        entriesMatch(loadedEntry, sampleEntry);
    }

    private void entriesMatch(CatalogEntry catalogEntry, CatalogEntry anotherCatalogEntry) {
        Assertions.assertThat(catalogEntry)
                .extracting(CatalogEntry::getIsbn, CatalogEntry::getTitle, CatalogEntry::getAuthor)
                .containsExactly(anotherCatalogEntry.getIsbn(), anotherCatalogEntry.getTitle(), anotherCatalogEntry.getAuthor());
    }
}
