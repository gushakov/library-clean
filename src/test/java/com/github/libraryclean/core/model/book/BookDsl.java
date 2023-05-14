package com.github.libraryclean.core.model.book;

import com.github.libraryclean.core.model.catalog.Isbn;

import java.util.Map;
import java.util.Optional;

public class BookDsl {

    private static Map<String, Book> books() {
        return Map.of(
                "ZuWLBP",
                Book.of(BookId.of("ZuWLBP"), Isbn.of("0134494164"), BookType.RESTRICTED),
                "ejVnPM",
                Book.of(BookId.of("ejVnPM"), Isbn.of("0134494164"), BookType.CIRCULATING)
        );
    }

    public static Book book(String bokId) {
        return Optional.ofNullable(books().get(bokId)).orElseThrow();
    }

    public static BookId anyBookId() {
        return BookId.of("ZuWLBP");
    }


}
