package com.github.libraryclean.core.model.patron;

import java.time.LocalDate;

import static com.github.libraryclean.core.model.LibraryDsl.anyDate;
import static com.github.libraryclean.core.model.catalog.CatalogDsl.anyIsbn;

public class PatronDsl {

    public static Hold anyHold(LocalDate holdStartDate) {
        return Hold.of(anyIsbn(), holdStartDate);
    }

    public static PatronId anyPatronId() {
        return PatronId.of("2YgHOT");
    }

    public static Patron anyRegularPatron() {
        return Patron.of(anyPatronId(), "George Clooney", PatronLevel.REGULAR);
    }

    public static Patron aRegularPatronWithActiveHold() {
        return anyRegularPatron().withAdditionalHold(anyHold(anyDate()));
    }

}
