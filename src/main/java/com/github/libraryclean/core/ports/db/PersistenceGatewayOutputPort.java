/*
    COPYRIGHT DISCLAIMER
    --------------------

    The code in this file may be based on the original work from
    [ddd-by-examples/library](https://github.com/ddd-by-examples/library).

    Please see the original licence at
    https://github.com/ddd-by-examples/library/blob/master/LICENSE

    and the copyright disclaimer notice in "README.md" (in this repository).
 */

package com.github.libraryclean.core.ports.db;

import com.github.libraryclean.core.model.book.Book;
import com.github.libraryclean.core.model.book.BookType;
import com.github.libraryclean.core.model.catalog.Isbn;
import com.github.libraryclean.core.model.patron.Patron;
import com.github.libraryclean.core.model.patron.PatronId;

import java.time.LocalDate;
import java.util.Set;

public interface PersistenceGatewayOutputPort {

    /**
     * Returns {@code true} if a catalog entry exists for the given ISBN.
     *
     * @param isbn ISBN
     * @return {@code true} if a catalog entry exists for the given ISBN
     */
    boolean existsInCatalog(Isbn isbn);

    /**
     * Returns a set of available books with the corresponding ISBN on a particular
     * date. Available books are books which are not currently held or checkout.
     *
     * @param isbn ISBN
     * @param type type of the book to restrict the search, if {@code null}, search for any type
     * @param date date to check
     * @return set of available books, empty set if no books are available
     */
    Set<Book> findAvailableBooks(Isbn isbn, BookType type, LocalDate date);

    /**
     * Loads a {@code Patron} with corresponding ID.
     *
     * @param patronId ID of the patron
     * @return {@code Patron} instance
     * @throws PersistenceError if no patron with matching ID could be found
     */
    Patron loadPatron(PatronId patronId);

    /**
     * Rollbacks current transaction.
     */
    void rollback();

    /**
     * Saves patron in the persistence store.
     * @param patron patron to save
     */
    void savePatron(Patron patron);
}
