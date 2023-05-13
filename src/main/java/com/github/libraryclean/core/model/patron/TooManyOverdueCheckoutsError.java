package com.github.libraryclean.core.model.patron;

import com.github.libraryclean.core.GenericLibraryError;

public class TooManyOverdueCheckoutsError extends GenericLibraryError {
    public TooManyOverdueCheckoutsError(String message) {
        super(message);
    }
}
