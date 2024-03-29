/*
    COPYRIGHT DISCLAIMER
    --------------------

    The code in this file may be based on the original work from
    [ddd-by-examples/library](https://github.com/ddd-by-examples/library).

    Please see the original licence at
    https://github.com/ddd-by-examples/library/blob/master/LICENSE

    and the copyright disclaimer notice in "README.md" (in this repository).
 */

package com.github.libraryclean.core.model.book;


import com.github.libraryclean.core.model.catalog.Isbn;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static com.github.libraryclean.core.Validator.notNull;

/**
 * Actual copy or a book as can be found on a shelf. Each {@code Book}
 * has a reference to a catalog entry via an ISBN. Each {@code Book} has {@code type}
 * attribute which can be "Circulating" or "Restricted".
 * <p>
 * This entity corresponds to "an instance of a book" in the original domain description.
 *
 * @see BookId
 * @see BookType
 * @see Book
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book {

    @EqualsAndHashCode.Include
    // unique identifier for the book
    BookId bookId;

    // ISBN of the corresponding catalog entry
    Isbn isbn;

    // type of the book
    BookType type;

    public static Book of(BookId bookId, Isbn isbn, BookType type) {
        return Book.builder()
                .bookId(bookId)
                .isbn(isbn)
                .type(type)
                .build();
    }

    @Builder
    public Book(BookId bookId, Isbn isbn, BookType type, Integer version) {
        this.bookId = notNull(bookId);
        this.isbn = notNull(isbn);
        this.type = notNull(type);
    }
}
