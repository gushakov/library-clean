/*
    COPYRIGHT DISCLAIMER
    --------------------

    The code in this file may be based on the original work from
    [ddd-by-examples/library](https://github.com/ddd-by-examples/library).

    Please see the original licence at
    https://github.com/ddd-by-examples/library/blob/master/LICENSE

    and the copyright disclaimer notice in "README.md" (in this repository).
 */

package com.github.libraryclean.infrastructure.adapter.db.jdbc;

import com.github.libraryclean.core.model.book.Book;
import com.github.libraryclean.core.model.book.BookType;
import com.github.libraryclean.core.model.catalog.CatalogEntry;
import com.github.libraryclean.core.model.catalog.Isbn;
import com.github.libraryclean.core.model.patron.Patron;
import com.github.libraryclean.core.model.patron.PatronId;
import com.github.libraryclean.core.ports.db.PersistenceError;
import com.github.libraryclean.core.ports.db.PersistenceGatewayOutputPort;
import com.github.libraryclean.infrastructure.adapter.db.jdbc.catalog.CatalogEntryDbEntityRepository;
import com.github.libraryclean.infrastructure.adapter.db.map.DbMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

/**
 * Secondary adapter for working with the persistence store.
 */
@Service
@RequiredArgsConstructor
public class PersistenceGateway implements PersistenceGatewayOutputPort {

    // mapper from persistent entities to models

    private final DbMapper dbMapper;

    // Spring Data repositories for each "aggregate" (persistent root entity)

    private final CatalogEntryDbEntityRepository catalogRepo;

    @Override
    public boolean existsInCatalog(Isbn isbn) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public CatalogEntry loadCatalogEntry(Isbn isbn) {
        return catalogRepo.findById(isbn.getNumber())
                .map(dbMapper::convert)
                .orElseThrow(() -> new PersistenceError("Cannot load catalog entry with ISBN: %s"
                        .formatted(isbn.getNumber())));
    }

    @Override
    public Set<Book> findAvailableBooks(Isbn isbn, BookType type, LocalDate date) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Patron loadPatron(PatronId patronId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void rollback() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void savePatron(Patron patron) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
