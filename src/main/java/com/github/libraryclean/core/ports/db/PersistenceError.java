/*
    COPYRIGHT DISCLAIMER
    --------------------

    The code in this file may be based on the original work from
    [ddd-by-examples/library](https://github.com/ddd-by-examples/library).

    Please see the original licence at
    https://github.com/ddd-by-examples/library/blob/master/LICENSE

    and the copyright disclaimer notice in "README.md" (in this repository).
 */

package com.github.libraryclean.core.ports.db;

import com.github.libraryclean.core.GenericLibraryError;

public class PersistenceError extends GenericLibraryError {
    public PersistenceError(String message) {
        super(message);
    }

    public PersistenceError(String message, Exception e) {
        super(message, e);
    }
}
