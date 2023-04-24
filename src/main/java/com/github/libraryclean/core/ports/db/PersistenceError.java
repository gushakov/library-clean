package com.github.libraryclean.core.ports.db;

import com.github.libraryclean.core.GenericLibraryError;

public class PersistenceError extends GenericLibraryError {
    public PersistenceError(String message) {
        super(message);
    }
}
