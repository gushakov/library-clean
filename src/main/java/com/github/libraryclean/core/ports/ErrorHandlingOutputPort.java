package com.github.libraryclean.core.ports;

public interface ErrorHandlingOutputPort {

    void presentError(Throwable e);

}
