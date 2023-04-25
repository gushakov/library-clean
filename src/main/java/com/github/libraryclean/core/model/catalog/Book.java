package com.github.libraryclean.core.model.catalog;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Optional;
import java.util.Set;

import static com.github.libraryclean.core.Validator.notBlank;
import static com.github.libraryclean.core.Validator.notNull;

/**
 * Description of a book in the library catalog. A book in the catalog is different from
 * any actual instances or copies of the book (on a shelf). Each book in the catalog is
 * uniquely identified by an ISBN (10) number.
 *
 * @see Isbn
 * @see BookInstance
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book {

    @EqualsAndHashCode.Include
    Isbn isbn;

    String title;

    String author;

    Set<BookInstance> instances;

    // needed by Spring Data JDBC, see {@code BookDbEntity}
    Integer version;

    @Builder
    public Book(Isbn isbn, String title, String author, Set<BookInstance> instances, Integer version) {
        // validate domain invariants in the constructor
        this.isbn = notNull(isbn);
        this.title = notBlank(title);
        this.author = notBlank(author);

        // can be "null" for a book in the catalog without any instances (copies on the shelf)
        this.instances = instances;

        // can be null for a book which has never been persisted in the database
        this.version = version;
    }

    private Book.BookBuilder newBook() {
        return Book.builder()
                .isbn(isbn)
                .title(title)
                .author(author)
                .version(version)
                .instances(Optional.ofNullable(instances).map(Set::copyOf).orElse(null));
    }

}
