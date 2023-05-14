package com.github.libraryclean.core.model.catalog;

import java.util.Map;
import java.util.Optional;

public class CatalogDsl {

    private static Map<String, CatalogEntry> catalogEntries() {
        return Map.of(
                "0134494164",
                CatalogEntry.of(Isbn.of("0134494164"), "Clean Architecture", "Robert Martin"),

                "0321125215",
                CatalogEntry.of(Isbn.of("0321125215"), "Domain-Driven Design", "Eric Evans")
        );
    }


    public static CatalogEntry catalogEntry(String isbn) {
        return Optional.ofNullable(catalogEntries().get(isbn)).orElseThrow();
    }


    public static Isbn anyIsbn() {
        return Isbn.of("0134494164");
    }


}
