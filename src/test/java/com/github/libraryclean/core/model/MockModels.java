package com.github.libraryclean.core.model;

import com.github.libraryclean.core.model.catalog.Book;

import java.util.Map;

public final class MockModels {

    public static Map<String, Book> allBooks() {
        return Map.of("0134494164", Book.of("0134494164", "Clean Architecture", "Robert Martin"),
                "0321125215", Book.of("0321125215", "Domain-Driven Design", "Eric Evans"));
    }

    public static Book aBook(String isbn) {
        return allBooks().get(isbn);
    }


}
