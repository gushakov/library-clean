package com.github.libraryclean.infrastucture.adapter.db.map;

import com.github.libraryclean.core.model.catalog.Book;
import com.github.libraryclean.infrastucture.adapter.db.catalog.BookDbEntity;

public interface DbMapper {

    Book convert(BookDbEntity bookDbEntity);
    BookDbEntity convert(Book book);

}
