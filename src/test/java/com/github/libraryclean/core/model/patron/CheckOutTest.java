package com.github.libraryclean.core.model.patron;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.github.libraryclean.core.model.LibraryDsl.*;
import static org.assertj.core.api.Assertions.*;

public class CheckOutTest {

    @Test
    void must_not_return_checkout_on_date_before_start_date() {

        // given

        LocalDate startDate = anyDate();
        LocalDate invalidReturnDate = dayBefore(startDate);
        CheckOut checkOut = CheckOut.of(anyBookId(), startDate, Days.of(1));

        // when

        Exception error = catchException(() -> checkOut.returnBook(invalidReturnDate));

        // then

        assertThat(error)
                .isInstanceOf(InvalidCheckOutStateError.class);
    }

    @Test
    void actual_return_date_must_be_set_after_book_is_returned() {
        // given

        LocalDate startDate = anyDate();
        LocalDate returnDate = dayLater(startDate);
        CheckOut checkOut = CheckOut.of(anyBookId(), startDate, Days.of(10));

        // when

        CheckOut completedCheckout = checkOut.returnBook(returnDate);

        // then

        assertThat(completedCheckout.getActualReturnDate())
                .isCloseTo(returnDate, within(1, ChronoUnit.DAYS));

        // and

        assertThat(completedCheckout.isBookReturned()).isTrue();
    }

    @Test
    void overdue_at_date_later_then_scheduled_return_date() {

        // given

        LocalDate startDate = anyDate();
        Days duration = Days.of(10);
        LocalDate atDate = startDate.plusDays(20);
        CheckOut checkOut = CheckOut.of(anyBookId(), startDate, duration);

        // when
        boolean overdue = checkOut.isOverdue(atDate);

        // then
        assertThat(overdue).isTrue();

    }
}
