package com.github.libraryclean.core;

public abstract class GenericLibraryError extends RuntimeException {
    public GenericLibraryError(String message) {
        super(message);
    }

    public GenericLibraryError(String message, Throwable cause) {
        super(message, cause);
    }
}
