package com.github.libraryclean.core.model.catalog;


import com.github.libraryclean.core.Ids;
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

    /**
     * Creates a new book instance for a book from the catalog.
     * ID of the instance will be assigned automatically to a unique random identifier.
     *
     * @param isbn existing ISBN of a book in the catalog
     * @param type type of this book instance
     * @return new {@code BookInstance}
     * @see Ids#next()
     */
    public static BookInstance of(String isbn, BookType type) {
        return BookInstance.builder()
                .bookId(BookId.of(Ids.next()))
                .isbn(Isbn.of(isbn))
                .type(type)
                .build();
    }

    /**
     * Creates new book instance with provided ID.
     *
     * @param bookId ID of the book instance
     * @param isbn   ISBN of the book in the catalog
     * @param type   type of the book instance
     * @return new {@code BookInstance}
     */
    public static BookInstance of(String bookId, String isbn, BookType type) {
        return BookInstance.builder()
                .bookId(BookId.of(bookId))
                .isbn(Isbn.of(isbn))
                .type(type)
                .build();
    }

    @Builder
    public BookInstance(BookId bookId, Isbn isbn, BookType type) {
        this.bookId = notNull(bookId);
        this.isbn = notNull(isbn);
        this.type = notNull(type);
    }

}
