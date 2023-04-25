package com.github.libraryclean.core.model.catalog;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static com.github.libraryclean.core.Validator.notNull;

/**
 * Actual copy or instance of a book (as can be found on a shelf). Each {@code Book}
 * in the catalog can have a number of {@code BookInstance}s (each referring to the same ISBN).
 * Each book instance has a {@code type}: "Circulating" or "Restricted".
 *
 * @see BookId
 * @see BookType
 * @see Book
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BookInstance {

    @EqualsAndHashCode.Include
    // unique identifier for the instance
    BookId bookId;

    @EqualsAndHashCode.Include
    // ISBN of the related book from the catalog
    Isbn isbn;

    BookType type;

    @Builder
    public BookInstance(BookId bookId, Isbn isbn, BookType type) {
        this.bookId = notNull(bookId);
        this.isbn = notNull(isbn);
        this.type = notNull(type);
    }
}
