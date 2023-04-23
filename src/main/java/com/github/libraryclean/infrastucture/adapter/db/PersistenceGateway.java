package com.github.libraryclean.infrastucture.adapter.db;

import com.github.libraryclean.core.model.catalog.Book;
import com.github.libraryclean.core.model.catalog.Isbn;
import com.github.libraryclean.core.ports.db.PersistenceError;
import com.github.libraryclean.core.ports.db.PersistenceGatewayOutputPort;
import com.github.libraryclean.infrastucture.adapter.db.catalog.BookDbEntityRepository;
import com.github.libraryclean.infrastucture.adapter.db.map.DbMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PersistenceGateway implements PersistenceGatewayOutputPort {

    DbMapper dbMapper;
    BookDbEntityRepository bookRepo;

    @Override
    public Book obtainBookByIsbn(Isbn isbn){
       return bookRepo.findById(isbn.getNumber())
                .map(dbMapper::convert)
                .orElseThrow(() -> new PersistenceError("Book with ISBN: %s is not in the database"
                        .formatted(isbn)));
    }

}
