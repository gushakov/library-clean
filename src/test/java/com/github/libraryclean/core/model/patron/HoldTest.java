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

import static com.github.libraryclean.core.model.SampleDates.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

public class HoldTest {

    @Test
    void must_not_construct_hold_with_invalid_isbn() {

        // given
        // when
        Exception error = catchException(() -> Hold.of(null, anyDate()));
        // then
        assertThat(error)
                .isInstanceOf(InvalidDomainObjectError.class);

    }

    @Test
    void must_not_construct_hold_with_invalid_from_date() {

        // given
        // when
        Exception error = catchException(() -> Hold.of(anyIsbn(), null));
        // then
        assertThat(error)
                .isInstanceOf(InvalidDomainObjectError.class);

    }

    @Test
    void open_ended_hold_not_completed_not_canceled_is_active() {
        // given
        Hold openEndedHold = anyOpenEndedHold();
        // when
        boolean active = openEndedHold.isActive();
        // then
        assertThat(active).isTrue();
    }

    private static Hold anyOpenEndedHold() {
        return Hold.of(anyIsbn(), anyDate());
    }

    private static Isbn anyIsbn() {
        return Isbn.of("0134494164");
    }

    @Test
    void cancel_active_hold() {
        // given

        LocalDate startDate = anyDate();
        LocalDate laterDate = dayLater(startDate);
        Hold originalHold = Hold.of(anyIsbn(), startDate);

        // when

        Hold canceledHold = originalHold.cancel(laterDate);

        // then

        // original hold has not changed
        assertThat(originalHold.isActive()).isTrue();
        assertThat(originalHold.wasCanceled()).isFalse();
        assertThat(originalHold.wasCompleted()).isFalse();

        // and
        assertThat(canceledHold.isActive()).isFalse();
        assertThat(canceledHold.wasCanceled()).isTrue();
        assertThat(canceledHold.wasCompleted()).isFalse();
    }

    @Test
    void must_not_cancel_hold_with_null_cancel_date() {
        // given

        Hold originalHold = anyOpenEndedHold();

        // when

        Exception error = catchException(() -> originalHold.cancel(null));

        // then
        assertThat(error)
                .isInstanceOf(InvalidDomainObjectError.class);
    }

    @Test
    void must_not_complete_hold_with_null_complete_date() {
        // given

        Hold originalHold = anyOpenEndedHold();

        // when

        Exception error = catchException(() -> originalHold.complete(null));

        // then
        assertThat(error)
                .isInstanceOf(InvalidDomainObjectError.class);
    }

    @Test
    void must_not_cancel_hold_with_cancel_date_before_start_date() {
        // given
        LocalDate startDate = anyDate();
        LocalDate invalidDateCanceled = dayBefore(startDate);
        Hold hold = Hold.of(anyIsbn(), startDate);

        // when
        Exception error = catchException(() -> hold.cancel(invalidDateCanceled));

        // then
        assertThat(error).isInstanceOf(InvalidHoldStateError.class);
    }

    @Test
    void must_not_complete_hold_with_complete_date_before_start_date() {
        // given
        LocalDate startDate = anyDate();
        LocalDate invalidDateCompleted = dayBefore(startDate);
        Hold hold = Hold.of(anyIsbn(), startDate);

        // when
        Exception error = catchException(() -> hold.complete(invalidDateCompleted));

        // then
        assertThat(error).isInstanceOf(InvalidHoldStateError.class);
    }
}
