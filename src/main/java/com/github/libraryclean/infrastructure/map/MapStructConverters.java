package com.github.libraryclean.infrastructure.map;

import com.github.libraryclean.core.model.catalog.Isbn;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class MapStructConverters {

    public String convertIsbnToString(Isbn isbn) {
        if (isbn == null) {
            return null;
        }
        return isbn.getNumber();
    }

    public Isbn convertStringToIsbn(String number) {
        if (number == null) {
            return null;
        }
        return Isbn.of(number);
    }

}
