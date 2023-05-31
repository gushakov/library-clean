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

import static com.github.libraryclean.core.model.SampleDates.anyDate;
import static com.github.libraryclean.core.model.SampleDates.daysBefore;
import static com.github.libraryclean.core.model.catalog.SampleCatalog.anyIsbn;
import static com.github.libraryclean.core.model.catalog.SampleCatalog.anyOtherIsbn;
import static com.github.libraryclean.core.model.patron.SamplePatrons.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

public class PatronTest {

    @Test
    void must_not_be_able_to_construct_invalid_patron() {

        // given

        // when

        Exception error = catchException(() -> Patron.builder().build());

        // then

        assertThat(error).isInstanceOf(InvalidDomainObjectError.class);

    }

    @Test
    void patron_must_not_have_duplicate_holds() {

        // given

        Hold hold = anyHold(anyDate());
        Patron patron = aPatronWithHold(hold);

        // when

        Exception error = catchException(() -> patron.holdBook(hold.getIsbn(), hold.getStartDate(),
                hold.getDuration(), anyNumberOfMaxOverdueCheckouts()));

        // then

        assertThat(error)
                .isInstanceOf(DuplicateHoldError.class);

        // and

        assertThat(patron.getHolds()).hasSize(1);

    }

    @Test
    void patron_must_not_hold_checked_out_book() {

        // given

        CheckOut checkOut = anyCheckOut(anyDate(), Days.of(30));
        Patron patron = aPatronWithCheckOut(checkOut);

        // when

        Exception error = catchException(() -> patron.holdBook(checkOut.getIsbn(), anyDate(), null, 2));

        // then

        assertThat(error).isInstanceOf(HoldingCheckedOutBookError.class);

    }

    @Test
    void regular_patron_must_not_place_open_ended_holds() {

        // given

        Isbn isbn = anyIsbn();
        Patron regularPatron = anyRegularPatron();

        // when

        Exception error = catchException(() ->
                regularPatron.holdBook(isbn, anyDate(),
                        holdDurationNotSpecified(), anyNumberOfMaxOverdueCheckouts()));

        // then

        assertThat(error)
                .isInstanceOf(InsufficientPatronLevelForHoldTypeError.class);

        // and

        Hold holdFromError = ((InsufficientPatronLevelForHoldTypeError) error).getHold();
        assertThat(holdFromError.getIsbn()).isEqualTo(isbn);

        // and

        assertThat(regularPatron.getHolds()).isEmpty();
    }

    @Test
    void patron_must_not_place_hold_if_too_many_overdue_checkouts() {

        // given

        LocalDate checkoutStartDate = daysBefore(anyDate(), 40);
        CheckOut overdueCheckOut = anyCheckOut(checkoutStartDate, Days.of(30));
        Patron patron = aPatronWithCheckOut(overdueCheckOut);
        Isbn holdIsbn = anyOtherIsbn(overdueCheckOut.getIsbn());
        Days holdDuration = Days.of(10);
        int maxNumOverdueCheckouts = 0;

        // when

        Exception error = catchException(() -> patron.holdBook(holdIsbn, anyDate(), holdDuration, maxNumOverdueCheckouts));

        // then

        assertThat(error)
                .isInstanceOf(TooManyOverdueCheckoutsError.class);

    }

    @Test
    void hold_book() {

        // given

        Patron patron = anyPatron();
        Isbn isbn = anyIsbn();
        LocalDate holdStartDate = anyDate();
        Days holdDuration = Days.of(30);
        int maxNumOverdueCheckouts = 2;

        // when

        Patron patronWithHold = patron.holdBook(isbn, holdStartDate, holdDuration, maxNumOverdueCheckouts);

        // then

        patronHasNewHoldWithIsbn(patronWithHold, isbn);
    }

    private static void patronHasNewHoldWithIsbn(Patron patronWithHold, Isbn isbn) {
        assertThat(patronWithHold.getHolds())
                .extracting(Hold::getIsbn)
                .containsExactly(isbn);
    }
}
