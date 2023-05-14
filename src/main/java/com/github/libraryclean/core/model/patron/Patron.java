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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
     * @param isbn                   ISBN of the corresponding catalog entry
     * @param holdStartDate          date on which a hold starts
     * @param holdDuration           number of days for a hold (closed-ended), {@code null}
     *                               for an open-ended hold
     * @param maxNumOverdueCheckOuts maximum number of overdue checkouts allowed for a hold
     *                               to be registered successfully
     * @return new {@code Patron} with an additional hold registered
     */
    public Patron holdBook(Isbn isbn, LocalDate holdStartDate, Days holdDuration, int maxNumOverdueCheckOuts) {

        /*
            Point of interest
            -----------------
            We have asserted preconditions external to this instance of "Patron" aggregate
            in the use case (caller of this method). So here we can concentrate on actually
            asserting intra-aggregate invariants and on actually performing the logic of
            creating and registering a new book hold.
         */

        // create new hold
        Hold hold = Hold.of(isbn, holdStartDate, holdDuration);

        // check if patron has a hold on a book with the same ISBN
        if (hasHold(isbn).isPresent()){
            throw new DuplicateHoldError(hold, "Illegal hold error: patron already has an active hold for " +
                    "catalog entry with the same ISBN");
        }

        // regular patron cannot issue open-ended holds
        if (level == PatronLevel.REGULAR && hold.type() == HoldType.OPEN_ENDED) {
            throw new InsufficientPatronLevelForHoldTypeError(hold, "Regular patron cannot issue an open-ended holds");
        }

        // cannot exceed the maximum number of overdue checkouts for a successful hold
        if (overdueCheckOuts(holdStartDate).size() > maxNumOverdueCheckOuts) {
            throw new TooManyOverdueCheckoutsError(hold,"Cannot issue any holds after the maximum number of " +
                    "overdue check-outs has been reached");
        }

        /*
            Now we can proceed with registering a new active hold for this partner. Since
            all our domain objects are immutable, we create a new copy of "Patron" entity
            with a new set of active holds containing the additional hold.
         */
        return newPatron()
                .holds(addOne(this.holds, hold))
                .build();
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

    /**
     * Returns a copy of a hold among this patron's active holds with the
     * matching {@code isbn}.
     *
     * @param isbn ISBN of the book on hold
     * @return optional with a copy of the matching hold or an empty optional
     */
    public Optional<Hold> hasHold(Isbn isbn) {
        return holds.stream()
                .filter(hold -> notNull(isbn).equals(hold.getIsbn()))
                .findFirst();
    }

    private PatronBuilder newPatron() {
        return Patron.builder()
                .patronId(patronId)
                .fullName(fullName)
                .level(level)
                .holds(holds)
                .checkOuts(checkOuts);
    }

    private Set<Hold> addOne(Set<Hold> holds, Hold additionalHold){
        return Stream.concat(holds.stream(), Stream.of(additionalHold))
                .collect(Collectors.toUnmodifiableSet());
    }
}
