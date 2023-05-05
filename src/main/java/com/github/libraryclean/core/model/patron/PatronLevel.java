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

/**
 * Status or level of a {@link Patron} in the library.
 * Researcher patron can place open-ended holds and holds
 * for longer periods. Researcher patron can
 */
public enum PatronLevel {
    REGULAR,
    RESEARCHER
}
