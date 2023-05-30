/*
    COPYRIGHT DISCLAIMER
    --------------------

    The code in this file may be based on the original work from
    [ddd-by-examples/library](https://github.com/ddd-by-examples/library).

    Please see the original licence at
    https://github.com/ddd-by-examples/library/blob/master/LICENSE

    and the copyright disclaimer notice in "README.md" (in this repository).
 */

package com.github.libraryclean.core.model.catalog;

import java.util.Map;
import java.util.Optional;

public class SampleCatalog {

    private static Map<String, CatalogEntry> catalogEntries() {
        return Map.of(
                "0134494164",
                CatalogEntry.of(Isbn.of("0134494164"), "Clean Architecture", "Robert Martin"),

                "0321125215",
                CatalogEntry.of(Isbn.of("0321125215"), "Domain-Driven Design", "Eric Evans"),

                "0134757599",
                CatalogEntry.of(Isbn.of("0134757599"), "Refactoring", "Martin Fowler")
        );
    }


    public static CatalogEntry catalogEntry(String isbn) {
        return Optional.ofNullable(catalogEntries().get(isbn)).orElseThrow();
    }

    public static Isbn anyIsbn() {
        return Isbn.of("0134494164");
    }

    public static Isbn anyOtherIsbn(Isbn isbn) {
        return catalogEntries().values().stream().filter(entry -> !entry.getIsbn().equals(isbn)).findAny()
                .orElseThrow().getIsbn();
    }

    public static CatalogEntry catalogEntry1FromDb() {
        return catalogEntry("0134494164");
    }


}
