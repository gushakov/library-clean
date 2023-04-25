package com.github.libraryclean.core.model.catalog;

import lombok.Builder;
import lombok.Value;

import static com.github.libraryclean.core.Validator.notBlank;

/**
 * Unique ID of each book instance.
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
