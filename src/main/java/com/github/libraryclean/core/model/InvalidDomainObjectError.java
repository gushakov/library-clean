package com.github.libraryclean.core.model;

import com.github.libraryclean.core.GenericLibraryError;

public class InvalidDomainObjectError extends GenericLibraryError {
    public InvalidDomainObjectError(String message) {
        super(message);
    }
}
