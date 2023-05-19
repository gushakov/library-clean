/*
    COPYRIGHT DISCLAIMER
    --------------------

    The code in this file may be based on the original work from
    [ddd-by-examples/library](https://github.com/ddd-by-examples/library).

    Please see the original licence at
    https://github.com/ddd-by-examples/library/blob/master/LICENSE

    and the copyright disclaimer notice in "README.md" (in this repository).
 */

package com.github.libraryclean.core.model.book;

import com.github.libraryclean.core.model.Ids;
import com.github.libraryclean.core.model.catalog.Isbn;

import java.util.Map;
import java.util.Optional;

public class SampleBooks {

    private static Map<String, Book> books() {
        return Map.of(
                "ZuWLBP",
                Book.of(BookId.of("ZuWLBP"), Isbn.of("0134494164"), BookType.RESTRICTED),
                "ejVnPM",
                Book.of(BookId.of("ejVnPM"), Isbn.of("0134494164"), BookType.CIRCULATING),
                "J88Psg",
                Book.of(BookId.of("J88Psg"), Isbn.of("0321125215"), BookType.CIRCULATING),
                "9Vf5QN",
                Book.of(BookId.of("9Vf5QN"), Isbn.of("0134757599"), BookType.CIRCULATING)
        );
    }

    public static Book book(String bookId) {
        return Optional.ofNullable(books().get(bookId)).orElseThrow();
    }

    public static BookId anyBookId() {
        return BookId.of(Ids.next());
    }

    public static Book anyBook() {
        return book("ZuWLBP");
    }

}
