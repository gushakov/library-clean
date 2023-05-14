package com.github.libraryclean.core.model;

import java.time.LocalDate;

public class LibraryDsl {

    public static LocalDate anyDate() {
        return LocalDate.now();
    }

    public static LocalDate dayLater(LocalDate date) {
        return date.plusDays(1);
    }

    public static LocalDate dayBefore(LocalDate date) {
        return date.minusDays(1);
    }

}
