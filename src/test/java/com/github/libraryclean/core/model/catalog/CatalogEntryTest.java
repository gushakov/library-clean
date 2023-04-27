package com.github.libraryclean.core.model.catalog;

import com.github.libraryclean.core.model.InvalidDomainObjectError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

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
        Throwable t = catchThrowable(() ->
                CatalogEntry.builder()
                        .isbn(Isbn.of("123456789X"))
                        .title(title)
                        .author("author")
                        .build());

        // then
        assertThat(t)
                .isNotNull()
                .isInstanceOf(InvalidDomainObjectError.class);
    }
}
