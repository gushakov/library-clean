package com.github.libraryclean.core.model.patron;

import com.github.libraryclean.core.GenericLibraryError;

public class InvalidHoldStateError extends GenericLibraryError {
    public InvalidHoldStateError(String message) {
        super(message);
    }
}
