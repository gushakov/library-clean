package com.github.libraryclean.core.model.patron;

import com.github.libraryclean.core.GenericLibraryError;

public class IllegalHoldAttemptError extends GenericLibraryError {
    public IllegalHoldAttemptError(String message) {
        super(message);
    }
}
