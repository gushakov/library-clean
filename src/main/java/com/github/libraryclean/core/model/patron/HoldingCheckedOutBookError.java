package com.github.libraryclean.core.model.patron;

import com.github.libraryclean.core.GenericLibraryError;
import lombok.Getter;

public class HoldingCheckedOutBookError extends GenericLibraryError {

    @Getter
    private final Hold hold;

    @Getter
    private final CheckOut checkOut;

    public HoldingCheckedOutBookError(Hold hold, CheckOut checkOut, String message) {
        super(message);
        this.hold = hold;
        this.checkOut = checkOut;
    }
}
