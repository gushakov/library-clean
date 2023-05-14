package com.github.libraryclean.core.model.patron;

import java.time.LocalDate;

import static com.github.libraryclean.core.model.LibraryDsl.anyDate;
import static com.github.libraryclean.core.model.catalog.CatalogDsl.anyIsbn;

public class PatronDsl {

    public static Days holdDurationNotSpecified(){
        return null;
    }

    public static int anyNumberOfMaxOverdueCheckouts(){
        return 2;
    }

    public static Hold anyHold(LocalDate holdStartDate) {
        return Hold.of(anyIsbn(), holdStartDate);
    }

    public static PatronId anyPatronId() {
        return PatronId.of("2YgHOT");
    }

    public static Patron anyRegularPatron() {
        return Patron.of(anyPatronId(), "George Clooney", PatronLevel.REGULAR);
    }

    public static Patron aRegularPatronWithActiveHold(Hold hold) {
        return anyRegularPatron().withAdditionalHold(hold);
    }

}
