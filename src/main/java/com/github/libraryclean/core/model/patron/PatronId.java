/*
    COPYRIGHT DISCLAIMER
    --------------------

    The code in this file may be based on the original work from
    [ddd-by-examples/library](https://github.com/ddd-by-examples/library).

    Please see the original licence at
    https://github.com/ddd-by-examples/library/blob/master/LICENSE

    and the copyright disclaimer notice in "README.md" (in this repository).
 */

package com.github.libraryclean.core.model.patron;

import lombok.Builder;
import lombok.Value;

import static com.github.libraryclean.core.Validator.notBlank;

/**
 * Unique ID of a patron.
 */
@Value
public class PatronId {


    String id;

    public static PatronId of(String id) {
        return PatronId.builder()
                .id(id)
                .build();
    }

    @Builder
    public PatronId(String id) {
        this.id = notBlank(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
