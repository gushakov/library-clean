package com.github.libraryclean.core.model;

import com.github.libraryclean.core.model.catalog.*;

import java.util.Map;
import java.util.Set;

/*
    Point of interest
    -----------------

    This class provides valid complete examples of domain models which
    can be used in unit tests and mock adapters for prototyping.
 */

public final class MockModels {

    public static Map<String, Book> allBooks() {
        return Map.of(
                "0134494164",

                Book.builder()
                        .isbn(Isbn.of("0134494164"))
                        .title("Clean Architecture")
                        .author("Robert Martin")
                        .version(1)
                        .instances(Set.of(
                                BookInstance.builder()
                                        .bookId(BookId.of("ZuWLBP"))
                                        .isbn(Isbn.of("0134494164"))
                                        .type(BookType.RESTRICTED)
                                        .build(),

                                BookInstance.builder()
                                        .bookId(BookId.of("ejVnPM"))
                                        .isbn(Isbn.of("0134494164"))
                                        .type(BookType.CIRCULATING)
                                        .build()
                        ))
                        .build(),

                "0321125215",

                Book.builder()
                        .isbn(Isbn.of("0321125215"))
                        .title("Domain-Driven Design")
                        .author("Eric Evans")
                        .version(1)
                        .build()
        );
    }

    public static Book aBook(String isbn) {
        return allBooks().get(isbn);
    }

}
