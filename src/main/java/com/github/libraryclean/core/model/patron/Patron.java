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

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

import static com.github.libraryclean.core.Validator.*;

/**
 * Patron is a person who can check out a {@code Book} from a library. Patron
 * can place {@code Hold}s on books.
 *
 * @see com.github.libraryclean.core.model.book.Book
 * @see Hold
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Patron {

    /**
     * ID of the patron.
     */
    @EqualsAndHashCode.Include
    PatronId patronId;

    /**
     * Name of the patron.
     */
    String fullName;

    /**
     * Patron level or status at the library.
     */
    PatronLevel level;

    /**
     * Set of currently active holds placed by the patron.
     */
    Set<Hold> holds;

    /**
     * Set of books currently checked out by the patron.
     */
    Set<CheckOut> checkOuts;

    public static Patron of(PatronId patronId, String fullName, PatronLevel level) {
        return Patron.builder()
                .patronId(patronId)
                .fullName(fullName)
                .level(level)
                .holds(Set.of())
                .checkOuts(Set.of())
                .build();
    }

    @Builder
    private Patron(PatronId patronId, String fullName, PatronLevel level, Set<Hold> holds, Set<CheckOut> checkOuts) {
        this.patronId = notNull(patronId);
        this.fullName = notBlank(fullName);
        this.level = notNull(level);
        this.holds = copy(holds);
        this.checkOuts = copy(checkOuts);
    }

    private PatronBuilder newPatron() {
        return Patron.builder()
                .patronId(patronId)
                .fullName(fullName)
                .level(level)
                .holds(holds)
                .checkOuts(checkOuts);
    }

}
