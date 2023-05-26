/*
    COPYRIGHT DISCLAIMER
    --------------------

    The code in this file may be based on the original work from
    [ddd-by-examples/library](https://github.com/ddd-by-examples/library).

    Please see the original licence at
    https://github.com/ddd-by-examples/library/blob/master/LICENSE

    and the copyright disclaimer notice in "README.md" (in this repository).
 */

package com.github.libraryclean.core.model.catalog;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static com.github.libraryclean.core.Validator.notBlank;
import static com.github.libraryclean.core.Validator.notNull;

/**
 * Description of a book in a library catalog. An entry in a catalog is
 * uniquely identified by an ISBN (10) number. It specifies some general
 * information about the work: the title, the author, etc. {@code CatalogEntry}
 * may exist in the system even if there are no actual {@code Book}s (copies
 * or instances) exist on the shelf.
 *
 * @see Isbn
 * @see com.github.libraryclean.core.model.book.Book
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CatalogEntry {

    @EqualsAndHashCode.Include
    Isbn isbn;

    String title;

    String author;

    // needed for Spring Data JDBC
    Integer version;

    public static CatalogEntry of(Isbn isbn, String title, String author) {
        return CatalogEntry.builder()
                .isbn(isbn)
                .title(title)
                .author(author)
                .build();
    }

    @Builder
    public CatalogEntry(Isbn isbn, String title, String author, Integer version) {
        // validate domain invariants in the constructor
        this.isbn = notNull(isbn);
        this.title = notBlank(title);
        this.author = notBlank(author);

        // version will null for entries which were not yet persisted
        this.version = version;
    }

}
