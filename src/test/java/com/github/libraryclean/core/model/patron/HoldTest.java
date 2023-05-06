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
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class HoldTest {

    @Test
    void must_not_construct_hold_with_invalid_isbn() {

        // given
        // when
        Exception error = catchException(() -> Hold.of(null, anyDate()));
        // then
        assertThat(error)
                .isNotNull()
                .isInstanceOf(InvalidDomainObjectError.class);

    }

    @Test
    void must_not_construct_hold_with_invalid_from_date() {

        // given
        // when
        Exception error = catchException(() -> Hold.of(anyIsbn(), null));
        // then
        assertThat(error)
                .isNotNull()
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

        LocalDate fromDate = anyDate();
        LocalDate laterDate = dayLater(fromDate);
        Hold originalHold = Hold.of(anyIsbn(), fromDate);

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
                .isNotNull()
                .isInstanceOf(InvalidDomainObjectError.class);
    }

    @Test
    void must_not_cancel_hold_with_cancel_date_before_from_date() {
        // given
        LocalDate fromDate = anyDate();
        LocalDate dateCanceled = fromDate.minusDays(1);
        Hold hold = Hold.of(anyIsbn(), fromDate);

        // when
        Optional<Exception> error = tryToCancel(hold, dateCanceled);

        // then
        assertThat(error).isPresent();
    }

    private static Optional<Exception> tryToCancel(Hold hold, LocalDate dateCanceled) {
        return Optional.ofNullable(catchThrowableOfType(() -> hold.cancel(dateCanceled), InvalidHoldStateError.class));
    }

    private static LocalDate anyDate() {
        return LocalDate.now();
    }

    private static LocalDate dayLater(LocalDate date){
        return date.plusDays(1);
    }
}
