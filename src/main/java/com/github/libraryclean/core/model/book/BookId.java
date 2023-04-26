package com.github.libraryclean.core.model.book;

import lombok.Builder;
import lombok.Value;

import static com.github.libraryclean.core.Validator.notBlank;

/**
 * Unique ID of the book.
 *
 * @see Book
 */
@Value
public class BookId {

    String id;

    public static BookId of(String id) {
        return BookId.builder().id(id).build();
    }

    @Builder
    public BookId(String id) {
        this.id = notBlank(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
