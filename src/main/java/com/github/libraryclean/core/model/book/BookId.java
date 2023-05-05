/*
    COPYRIGHT DISCLAIMER
    --------------------

    The code in this file may be based on the original work from
    [ddd-by-examples/library](https://github.com/ddd-by-examples/library).

    Please see the original licence at
    https://github.com/ddd-by-examples/library/blob/master/LICENSE

    and the copyright disclaimer notice in "README.md" (in this repository).
 */

package com.github.libraryclean.core.model.book;

import lombok.Builder;
import lombok.Value;

import static com.github.libraryclean.core.Validator.notBlank;

/**
 * Unique ID of the book. This ID is different from ISBN since
 * two {@code Book}s with different IDs may have the same ISBN.
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
