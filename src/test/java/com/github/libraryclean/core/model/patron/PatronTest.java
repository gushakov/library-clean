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
import com.github.libraryclean.core.model.book.Book;
import com.github.libraryclean.core.model.catalog.Isbn;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.github.libraryclean.core.model.SampleDates.anyDate;
import static com.github.libraryclean.core.model.SampleDates.daysBefore;
import static com.github.libraryclean.core.model.book.SampleBooks.book;
import static com.github.libraryclean.core.model.catalog.SampleCatalog.anyIsbn;
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
                hold.getDuration()));

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
        Patron patron = aPatronWithCheckOuts(checkOut);

        // when

        Exception error = catchException(() -> patron.holdBook(checkOut.getIsbn(), anyDate(), null));

        // then

        assertThat(error).isInstanceOf(HoldingCheckedOutBookError.class);

    }

    @Test
    void regular_patron_must_not_place_open_ended_holds() {

        // given

        Isbn isbn = anyIsbn();
        Patron regularPatron = anyRegularPatron();

        // when

        // placing a hold on a book with unspecified hold diration

        Exception error = catchException(() ->
                regularPatron.holdBook(isbn, anyDate(), null));

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
        Book checkedOutBook1 = book("ejVnPM");
        Book checkedOutBook2 = book("J88Psg");
        Book checkedOutBook3 = book("9Vf5QN");

        CheckOut overdueCheckOut1 = anyCheckOut(checkedOutBook1, checkoutStartDate, Days.of(30));
        CheckOut overdueCheckOut2 = anyCheckOut(checkedOutBook2, checkoutStartDate, Days.of(30));
        CheckOut overdueCheckOut3 = anyCheckOut(checkedOutBook3, checkoutStartDate, Days.of(30));
        Patron patron = aPatronWithCheckOuts(overdueCheckOut1, overdueCheckOut2, overdueCheckOut3);

        // when

        // trying to hold a book with ISBN other than ISBNs of the checked out books

        Exception error = catchException(() -> patron.holdBook(Isbn.of("173210221X"), anyDate(), Days.of(20)));

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

        // when

        Patron patronWithHold = patron.holdBook(isbn, holdStartDate, holdDuration);

        // then

        patronHasNewHoldWithIsbn(patronWithHold, isbn);
    }

    private static void patronHasNewHoldWithIsbn(Patron patronWithHold, Isbn isbn) {
        assertThat(patronWithHold.getHolds())
                .extracting(Hold::getIsbn)
                .containsExactly(isbn);
    }
}
