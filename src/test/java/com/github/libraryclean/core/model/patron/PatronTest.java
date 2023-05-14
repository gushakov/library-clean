package com.github.libraryclean.core.model.patron;

import org.junit.jupiter.api.Test;

import static com.github.libraryclean.core.model.LibraryDsl.anyDate;
import static com.github.libraryclean.core.model.patron.PatronDsl.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

public class PatronTest {

    @Test
    void partner_must_not_have_duplicate_holds() {

        // given

        Hold hold = anyHold(anyDate());
        Patron patron = aRegularPatronWithActiveHold(hold);

        // when

        Exception error = catchException(() -> patron.holdBook(hold.getIsbn(), hold.getStartDate(),
                holdDurationNotSpecified(), anyNumberOfMaxOverdueCheckouts()));

        // then

        assertThat(error)
                .isInstanceOf(DuplicateHoldError.class);

        // and

        assertThat(patron.getHolds()).hasSize(1);

    }
}
