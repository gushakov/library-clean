package com.github.libraryclean.core.model.patron;

import com.github.libraryclean.core.model.InvalidDomainObjectError;
import com.github.libraryclean.core.model.catalog.Isbn;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class HoldTest {

    @Test
    void must_not_construct_hold_with_invalid_isbn() {

        // given
        // when
        Exception error = Assertions.catchException(() -> Hold.of(null, LocalDate.now()));
        // then
        Assertions.assertThat(error)
                .isNotNull()
                .isInstanceOf(InvalidDomainObjectError.class);

    }

    @Test
    void must_not_construct_hold_with_invalid_from_date() {

        // given
        // when
        Exception error = Assertions.catchException(() -> Hold.of(Isbn.of("0134494164"), null));
        // then
        Assertions.assertThat(error)
                .isNotNull()
                .isInstanceOf(InvalidDomainObjectError.class);

    }
}
