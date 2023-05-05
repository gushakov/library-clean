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
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

public class HoldTest {

    @Test
    void must_not_construct_hold_with_invalid_isbn() {

        // given
        // when
        Exception error = catchException(() -> Hold.of(null, LocalDate.now()));
        // then
        assertThat(error)
                .isNotNull()
                .isInstanceOf(InvalidDomainObjectError.class);

    }

    @Test
    void must_not_construct_hold_with_invalid_from_date() {

        // given
        // when
        Exception error = catchException(() -> Hold.of(Isbn.of("0134494164"), null));
        // then
        assertThat(error)
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
        assertThat(active).isTrue();
    }

    @Test
    void cancel_active_hold() {
        // given
        LocalDate fromDate = LocalDate.now();
        Hold originalHold = Hold.of(Isbn.of("0134494164"), fromDate);
        // when
        Hold canceledHold = originalHold.cancel(fromDate.plusDays(1));
        // then
        assertThat(originalHold.isActive()).isTrue();
        assertThat(canceledHold.isActive()).isFalse();
        assertThat(canceledHold.wasCanceled()).isTrue();
    }

    @Test
    void must_not_cancel_hold_with_null_cancel_date() {
        // given
        Hold originalHold = Hold.of(Isbn.of("0134494164"), LocalDate.now());
        // when
        Exception error = catchException(() -> originalHold.cancel(null));
        // then
        assertThat(error)
                .isNotNull()
                .isInstanceOf(InvalidDomainObjectError.class);
    }

    @Test
    void must_not_cancel_hold_with_cancel_date_before_from_date() {
        // given
        LocalDate fromDate = LocalDate.now();
        Hold originalHold = Hold.of(Isbn.of("0134494164"), fromDate);
        // when
        Exception error = catchException(() -> originalHold.cancel(fromDate.minusDays(1)));
        // then
        assertThat(error)
                .isNotNull()
                .isInstanceOf(InvalidHoldStateError.class);
    }
}
