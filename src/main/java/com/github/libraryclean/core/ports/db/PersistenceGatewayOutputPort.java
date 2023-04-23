package com.github.libraryclean.core.ports.db;

import com.github.libraryclean.core.model.catalog.Book;
import com.github.libraryclean.core.model.catalog.Isbn;

public interface PersistenceGatewayOutputPort {
    Book obtainBookByIsbn(Isbn isbn);
}
