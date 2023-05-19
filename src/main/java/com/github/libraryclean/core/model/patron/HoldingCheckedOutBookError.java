package com.github.libraryclean.core.model.patron;

import com.github.libraryclean.core.GenericLibraryError;
import lombok.Getter;

public class HoldingCheckedOutBookError extends GenericLibraryError {

    @Getter
    private final Hold hold;

    public HoldingCheckedOutBookError(Hold hold, String message) {
        super(message);
        this.hold = hold;
    }
}
