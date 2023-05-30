/*
    COPYRIGHT DISCLAIMER
    --------------------

    The code in this file may be based on the original work from
    [ddd-by-examples/library](https://github.com/ddd-by-examples/library).

    Please see the original licence at
    https://github.com/ddd-by-examples/library/blob/master/LICENSE

    and the copyright disclaimer notice in "README.md" (in this repository).
 */

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
