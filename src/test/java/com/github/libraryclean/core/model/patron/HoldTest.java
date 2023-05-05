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


    @Test
    void open_ended_hold_not_completed_not_canceled_is_active() {
        // given
        Hold openEndedHold = Hold.of(Isbn.of("0134494164"), LocalDate.now());
        // when
        boolean active = openEndedHold.isActive();
        // then
        Assertions.assertThat(active).isTrue();
    }
}
