package com.github.libraryclean.core.model.patron;

import com.github.libraryclean.core.GenericLibraryError;

public class InsufficientLevelForHoldTypeError extends GenericLibraryError {
    public InsufficientLevelForHoldTypeError(String message) {
        super(message);
    }
}
