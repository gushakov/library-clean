package com.github.libraryclean.core.model.catalog;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static com.github.libraryclean.core.Validator.notBlank;
import static com.github.libraryclean.core.Validator.notNull;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book {

    @EqualsAndHashCode.Include
    Isbn isbn;
    String title;
    String author;

    public static Book of(String isbn,
                          String title,
                          String author) {
        return Book.builder()
                .isbn(Isbn.of(isbn))
                .title(title)
                .author(author)
                .build();
    }

    @Builder
    public Book(Isbn isbn, String title, String author) {
        this.isbn = notNull(isbn);
        this.title = notBlank(title);
        this.author = notBlank(author);
    }
}
