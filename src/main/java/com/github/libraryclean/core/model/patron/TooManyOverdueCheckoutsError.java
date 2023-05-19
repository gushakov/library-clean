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

import com.github.libraryclean.core.GenericLibraryError;
import lombok.Getter;

public class TooManyOverdueCheckoutsError extends GenericLibraryError {

    @Getter
    private final Hold hold;

    public TooManyOverdueCheckoutsError(Hold hold, String message) {
        super(message);
        this.hold = hold;
    }
}
