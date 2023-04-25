package com.github.libraryclean.infrastucture.adapter.db.map;

import com.github.libraryclean.core.model.catalog.Book;
import com.github.libraryclean.core.model.catalog.BookId;
import com.github.libraryclean.core.model.catalog.BookInstance;
import com.github.libraryclean.core.model.catalog.Isbn;
import com.github.libraryclean.infrastucture.adapter.db.catalog.BookDbEntity;
import com.github.libraryclean.infrastucture.adapter.db.catalog.BookInstanceDbEntity;
import com.github.libraryclean.infrastucture.utils.IgnoreForMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class MapStructDbMapper implements DbMapper {

    // Converters

    String isbnToString(Isbn isbn) {
        return isbn.getNumber();
    }

    Isbn stringToIsbn(String number) {
        return Isbn.of(number);
    }

    BookId stringToBookId(String id) {
        return BookId.builder()
                .id(id)
                .build();
    }

    String bookIdToString(BookId bookId) {
        return bookId.getId();
    }

    // Mappers for model entities

    @Mapping(target = "bookId", source = "id")
    abstract BookInstance map(BookInstanceDbEntity bookInstanceDbEntity);

    @Mapping(target = "id", source = "bookId")
    abstract BookInstanceDbEntity map(BookInstance bookInstance);

    abstract Book map(BookDbEntity bookDbEntity);

    abstract BookDbEntity map(Book book);

    // Public interface methods

    @Override
    @IgnoreForMapping
    public Book convert(BookDbEntity bookDbEntity) {
        return map(bookDbEntity);
    }

    @Override
    @IgnoreForMapping
    public BookDbEntity convert(Book book) {
        return map(book);
    }

    @Override
    @IgnoreForMapping
    public BookInstance convert(BookInstanceDbEntity bookInstanceDbEntity) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    @IgnoreForMapping
    public BookInstanceDbEntity convert(BookInstance bookInstance) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
