package com.github.libraryclean.infrastucture.adapter.db.map;

import com.github.libraryclean.core.model.catalog.Book;
import com.github.libraryclean.core.model.catalog.Isbn;
import com.github.libraryclean.infrastucture.adapter.db.catalog.BookDbEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class MapStructDbMapper implements DbMapper {

    protected String map(Isbn isbn) {
        return isbn.getNumber();
    }

    protected Isbn map(String number) {
        return Isbn.of(number);
    }

    protected abstract Book map(BookDbEntity bookDbEntity);

    @Override
    public Book convert(BookDbEntity bookDbEntity) {
        return map(bookDbEntity);
    }

    @Override
    public BookDbEntity convert(Book book) {
        return null;
    }
}
