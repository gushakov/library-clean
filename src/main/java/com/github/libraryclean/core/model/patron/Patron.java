package com.github.libraryclean.core.model.patron;

import com.sun.source.doctree.SeeTree;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

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
     * Patron level or status at the library.
     */
    PatronLevel level;

    /**
     * Set of currently active holds placed by the patron.
     */
    Set<Hold> holds;

    @Builder
    public Patron(PatronId patronId, PatronLevel level, Set<Hold> holds) {
        this.patronId = patronId;
        this.level = level;
        this.holds = holds;
    }
}
