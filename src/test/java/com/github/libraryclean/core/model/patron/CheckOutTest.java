package com.github.libraryclean.core.model.patron;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.github.libraryclean.core.model.LibraryDsl.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

public class CheckOutTest {

    @Test
    void must_not_return_checkout_on_date_before_start_date() {

        // given

        LocalDate startDate = anyDate();
        LocalDate invalidReturnDate = dayLater(startDate);
        CheckOut checkOut = CheckOut.of(anyBookId(), startDate, Days.of(1));

        // when

        Exception error = catchException(() -> checkOut.returnBook(invalidReturnDate));

        // then

        assertThat(error)
                .isInstanceOf(InvalidCheckOutStateError.class);
    }
}
