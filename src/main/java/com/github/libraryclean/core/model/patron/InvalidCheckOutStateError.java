package com.github.libraryclean.core.model.patron;

import com.github.libraryclean.core.GenericLibraryError;

public class InvalidCheckOutStateError extends GenericLibraryError {
    public InvalidCheckOutStateError(String message) {
        super(message);
    }
}
