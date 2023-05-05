/*
    COPYRIGHT DISCLAIMER
    --------------------

    The code in this file may be based on the original work from
    [ddd-by-examples/library](https://github.com/ddd-by-examples/library).

    Please see the original licence at
    https://github.com/ddd-by-examples/library/blob/master/LICENSE

    and the copyright disclaimer notice in "README.md" (in this repository).
 */

package com.github.libraryclean.core;

public abstract class GenericLibraryError extends RuntimeException {
    public GenericLibraryError(String message) {
        super(message);
    }

    public GenericLibraryError(String message, Throwable cause) {
        super(message, cause);
    }
}
