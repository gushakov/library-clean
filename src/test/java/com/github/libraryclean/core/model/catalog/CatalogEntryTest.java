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

import com.github.libraryclean.core.model.InvalidDomainObjectError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

public class CatalogEntryTest {

    @Test
    void isbn_equal_by_value() {
        // given
        Isbn isbn1 = Isbn.of("0134494164");
        Isbn isbn2 = Isbn.of("0134494164");

        // when
        // then
        assertThat(isbn1).isEqualTo(isbn2);
    }

    @Test
    void isbn_not_equal_by_value() {
        // given
        Isbn isbn1 = Isbn.of("0134494164");
        Isbn isbn2 = Isbn.of("0321125215");

        // when
        // then
        assertThat(isbn1).isNotEqualTo(isbn2);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " ", "\t"})
    void must_not_construct_catalog_entry_with_invalid_title(String title) {
        // given

        // when
        Exception error = catchException(() ->
                CatalogEntry.builder()
                        .isbn(Isbn.of("123456789X"))
                        .title(title)
                        .author("author")
                        .build());

        // then
        assertThat(error)
                .isInstanceOf(InvalidDomainObjectError.class);
    }
}
