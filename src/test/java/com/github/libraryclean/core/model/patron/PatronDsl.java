package com.github.libraryclean.core.model.patron;

import com.github.libraryclean.core.model.Ids;
import com.github.libraryclean.core.model.book.Book;
import com.github.libraryclean.core.model.book.BookDsl;
import com.github.libraryclean.core.model.catalog.CatalogDsl;

import java.time.LocalDate;

import static com.github.libraryclean.core.model.book.BookDsl.*;
import static com.github.libraryclean.core.model.catalog.CatalogDsl.anyIsbn;

public class PatronDsl {

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

    public static Patron aPatronWithCheckOut(CheckOut checkOut){
        return anyPatron().withAdditionalCheckOut(checkOut);
    }

}
