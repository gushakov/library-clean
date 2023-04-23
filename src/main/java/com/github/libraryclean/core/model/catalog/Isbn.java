package com.github.libraryclean.core.model.catalog;

import lombok.Builder;
import lombok.Value;

import static com.github.libraryclean.core.Validator.notBlank;

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
