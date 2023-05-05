/*
    COPYRIGHT DISCLAIMER
    --------------------

    The code in this file may be based on the original work from
    [ddd-by-examples/library](https://github.com/ddd-by-examples/library).

    Please see the original licence at
    https://github.com/ddd-by-examples/library/blob/master/LICENSE

    and the copyright disclaimer notice in "README.md" (in this repository).
 */

package com.github.libraryclean.core.model.catalog;

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
