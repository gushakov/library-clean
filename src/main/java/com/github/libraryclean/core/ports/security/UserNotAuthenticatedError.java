package com.github.libraryclean.core.ports.security;

import com.github.libraryclean.core.GenericLibraryError;

public class UserNotAuthenticatedError extends GenericLibraryError {
    public UserNotAuthenticatedError(String message) {
        super(message);
    }
}
