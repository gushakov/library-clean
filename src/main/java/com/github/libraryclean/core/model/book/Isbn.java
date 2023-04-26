package com.github.libraryclean.core.model.book;

import lombok.Builder;
import lombok.Value;

import static com.github.libraryclean.core.Validator.notBlank;

/**
 * International book identifier used by libraries. We are using format ISBN (10) in
 * our example.
 */
@Value
public class Isbn {
    String number;

    @Builder
    public Isbn(String number) {
        this.number = notBlank(number);
    }

    public static Isbn of(String number) {
        return Isbn.builder()
                .number(number)
                .build();
    }

    @Override
    public String toString() {
        return number;
    }
}
