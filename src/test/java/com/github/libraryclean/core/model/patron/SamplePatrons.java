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

import com.github.libraryclean.core.model.book.Book;
import com.github.libraryclean.core.model.catalog.Isbn;

import java.time.LocalDate;
import java.util.Map;

import static com.github.libraryclean.core.model.book.SampleBooks.anyBook;
import static com.github.libraryclean.core.model.catalog.SampleCatalog.anyIsbn;

public class SamplePatrons {

    private static Map<String, Patron> patrons() {
        return Map.of(
                "hLARqY",
                Patron.of(PatronId.of("hLARqY"), "George Clooney", PatronLevel.REGULAR)
        );
    }

    public static Hold anyHold(LocalDate holdStartDate) {
        return Hold.of(anyIsbn(), holdStartDate);
    }

    public static CheckOut anyCheckOut(LocalDate checkOutStartDate, Days checkOutDuration) {
        Book book = anyBook();
        return CheckOut.of(book.getBookId(), book.getIsbn(), checkOutStartDate, checkOutDuration);
    }

    public static CheckOut anyCheckOut(Book book, LocalDate checkOutStartDate, Days checkOutDuration) {
        return CheckOut.of(book.getBookId(), book.getIsbn(), checkOutStartDate, checkOutDuration);
    }

    public static PatronId anyPatronId() {
        return PatronId.of(patrons().keySet().stream().findAny().orElseThrow());
    }

    public static Patron anyRegularPatron() {
        return patrons().values().stream()
                .filter(patron -> patron.getLevel() == PatronLevel.REGULAR)
                .findAny().orElseThrow();
    }

    public static Patron anyPatron() {
        return patrons().values().stream().findAny().orElseThrow();
    }

    public static Patron aPatronWithHold(Hold hold) {
        return anyPatron().withAdditionalHold(hold);
    }

    public static Patron aPatronWithCheckOuts(CheckOut... checkOuts) {
        Patron p = anyPatron();
        for (CheckOut checkOut : checkOuts) {
            p = p.withAdditionalCheckOut(checkOut);
        }
        return p;
    }

    public static Patron patronWithHoldStartingAt(String patronId, String isbn, LocalDate holdStartDate) {
        return patrons().get(patronId)
                .withAdditionalHold(Hold.of(Isbn.of(isbn), holdStartDate));
    }

}
