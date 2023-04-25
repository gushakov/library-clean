package com.github.libraryclean.infrastucture.adapter.db.map;

import com.github.libraryclean.core.model.catalog.Book;
import com.github.libraryclean.core.model.catalog.BookInstance;
import com.github.libraryclean.infrastucture.adapter.db.catalog.BookDbEntity;
import com.github.libraryclean.infrastucture.adapter.db.catalog.BookInstanceDbEntity;

public interface DbMapper {

    Book convert(BookDbEntity bookDbEntity);
    BookDbEntity convert(Book book);

    BookInstance convert(BookInstanceDbEntity bookInstanceDbEntity);

    BookInstanceDbEntity convert(BookInstance bookInstance);

}
