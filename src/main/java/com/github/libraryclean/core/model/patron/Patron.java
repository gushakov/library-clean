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

import com.github.libraryclean.core.model.catalog.Isbn;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

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

    /**
     * Puts a hold on any books with corresponding ISBN.
     *
     * @param isbn          ISBN of the corresponding catalog entry
     * @param holdStartDate date on which a hold starts
     * @param holdDuration  number of days for a hold (close-ended), {@code null}
     *                      for an open-ended hold
     * @return new {@code Patron} with an additional hold registered
     */
    public Patron holdBook(Isbn isbn, LocalDate holdStartDate, Days holdDuration) {

        /*
            Point of interest
            -----------------

            Since we only call this method from the "hold book" use case, we can assume
            that ISBN corresponds to some existing catalog entry and that there are no
            currently any books available (for checkout) with this ISBN.
            Hence, here we must assert only the invariants related to this patron.
         */

        // create new hold
        Hold hold = Hold.of(isbn, holdStartDate, holdDuration);

        // regular parton cannot issue open-ended holds
        if (level == PatronLevel.REGULAR && hold.type() == HoldType.OPEN_ENDED) {
            throw new IllegalHoldAttemptError("Regular patron cannot issue an open-ended holds");
        }


        return null;
    }

    /**
     * Returns a set of any overdue checkouts.
     *
     * @return set of any overdue checkouts or an empty set if there are none
     */
    public Set<CheckOut> overdueCheckOuts(LocalDate atDate) {
        return checkOuts.stream()
                .filter(checkOut -> checkOut.isOverdue(atDate))
                .collect(Collectors.toUnmodifiableSet());
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
