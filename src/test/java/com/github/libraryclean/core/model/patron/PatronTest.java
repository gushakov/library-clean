package com.github.libraryclean.core.model.patron;

import com.github.libraryclean.core.model.catalog.Isbn;
import org.junit.jupiter.api.Test;

import static com.github.libraryclean.core.model.LibraryDsl.anyDate;
import static com.github.libraryclean.core.model.catalog.CatalogDsl.anyIsbn;
import static com.github.libraryclean.core.model.patron.PatronDsl.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

public class PatronTest {

    @Test
    void patron_must_not_have_duplicate_holds() {

        // given

        Hold hold = anyHold(anyDate());
        Patron patron = anyPatronWithActiveHold(hold);

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
}
