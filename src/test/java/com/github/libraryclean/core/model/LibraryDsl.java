package com.github.libraryclean.core.model;

import com.github.libraryclean.core.model.book.Book;
import com.github.libraryclean.core.model.book.BookId;
import com.github.libraryclean.core.model.book.BookType;
import com.github.libraryclean.core.model.catalog.CatalogEntry;
import com.github.libraryclean.core.model.catalog.Isbn;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

public class LibraryDsl {

    private static Map<String, CatalogEntry> catalogEntries() {
        return Map.of(
                "0134494164",
                CatalogEntry.of(Isbn.of("0134494164"), "Clean Architecture", "Robert Martin"),

                "0321125215",
                CatalogEntry.of(Isbn.of("0321125215"), "Domain-Driven Design", "Eric Evans")
        );
    }

    private static Map<String, Book> books() {
        return Map.of(
                "ZuWLBP",
                Book.of(BookId.of("ZuWLBP"), Isbn.of("0134494164"), BookType.RESTRICTED),
                "ejVnPM",
                Book.of(BookId.of("ejVnPM"), Isbn.of("0134494164"), BookType.CIRCULATING)
        );
    }

    public static CatalogEntry catalogEntry(String isbn) {
        return Optional.ofNullable(catalogEntries().get(isbn)).orElseThrow();
    }

    public static Book book(String bokId) {
        return Optional.ofNullable(books().get(bokId)).orElseThrow();
    }

    public static Isbn anyIsbn() {
        return Isbn.of("0134494164");
    }

    public static BookId anyBookId() {
        return BookId.of("ZuWLBP");
    }

    public static LocalDate anyDate() {
        return LocalDate.now();
    }

    public static LocalDate dayLater(LocalDate date) {
        return date.plusDays(1);
    }

}
