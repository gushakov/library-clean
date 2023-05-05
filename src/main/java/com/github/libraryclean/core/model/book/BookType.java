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

/**
 * Type of the book.
 *
 * @see Book
 */
public enum BookType {

    /**
     * Regular, circulating book.
     */
    CIRCULATING,

    /**
     * Special or restricted book.
     */
    RESTRICTED

}
