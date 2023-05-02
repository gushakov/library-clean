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

    @Builder
    public Patron(PatronId patronId, String fullName, PatronLevel level, Set<Hold> holds, Set<CheckOut> checkOuts) {
        this.patronId = notNull(patronId);
        this.fullName = notBlank(fullName);
        this.level = notNull(level);
        this.holds = copy(holds);
        this.checkOuts = copy(checkOuts);
    }
}
