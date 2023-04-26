package com.github.libraryclean.core.model;

/*
    Point of interest
    -----------------

    This class provides valid complete examples of domain models which
    can be used in unit tests and mock adapters for prototyping.
 */

import com.github.libraryclean.core.model.book.*;

import java.util.Map;

public final class MockModels {

    public static Map<String, CatalogEntry> catalogEntries() {
        return Map.of(
                "0134494164",
                CatalogEntry.of(Isbn.of("0134494164"), "Clean Architecture", "Robert Martin"),

                "0321125215",
                CatalogEntry.of(Isbn.of("0321125215"), "Domain-Driven Design", "Eric Evans")
        );
    }

    public static CatalogEntry catalogEntry(String isbn) {
        return catalogEntries().get(isbn);
    }

    public static Map<String, Book> books() {
        return Map.of(
                "ZuWLBP",
                Book.of(BookId.of("ZuWLBP"), Isbn.of("0134494164"), BookType.RESTRICTED),
                "ejVnPM",
                Book.of(BookId.of("ejVnPM"), Isbn.of("0134494164"), BookType.CIRCULATING)
        );
    }

}
