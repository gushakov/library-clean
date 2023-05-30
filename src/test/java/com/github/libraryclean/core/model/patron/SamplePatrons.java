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

import com.github.libraryclean.core.model.Ids;
import com.github.libraryclean.core.model.book.Book;
import com.github.libraryclean.core.model.catalog.Isbn;

import java.time.LocalDate;

import static com.github.libraryclean.core.model.book.SampleBooks.anyBook;
import static com.github.libraryclean.core.model.catalog.SampleCatalog.anyIsbn;

public class SamplePatrons {

    public static Days holdDurationNotSpecified() {
        return null;
    }

    public static int anyNumberOfMaxOverdueCheckouts() {
        return 2;
    }

    public static Hold anyHold(LocalDate holdStartDate) {
        return Hold.of(anyIsbn(), holdStartDate);
    }

    public static Hold anyOpenEndedHold(LocalDate holdStartDate) {
        return Hold.of(anyIsbn(), holdStartDate);
    }

    public static CheckOut anyCheckOut(LocalDate checkOutStartDate, Days checkOutDuration) {
        Book book = anyBook();
        return CheckOut.of(book.getBookId(), book.getIsbn(), checkOutStartDate, checkOutDuration);
    }

    public static PatronId anyPatronId() {
        return PatronId.of(Ids.next());
    }

    public static Patron anyRegularPatron() {
        return Patron.of(anyPatronId(), "George Clooney", PatronLevel.REGULAR);
    }

    public static Patron anyResearcherPatron() {
        return Patron.of(anyPatronId(), "Brad Pitt", PatronLevel.RESEARCHER);
    }

    public static Patron anyPatron() {
        return anyRegularPatron();
    }

    public static Patron aPatronWithHold(Hold hold) {
        return anyPatron().withAdditionalHold(hold);
    }

    public static Patron aPatronWithCheckOut(CheckOut checkOut) {
        return anyPatron().withAdditionalCheckOut(checkOut);
    }

    public static Patron patron1FromDb(){
       return Patron.of(PatronId.of("hLARqY"), "George Clooney", PatronLevel.REGULAR)
                .withAdditionalHold(Hold.of(Isbn.of("0134494164"),
                        LocalDate.of(2023, 5, 30)));
    }

}
