package com.github.libraryclean.core.usecase.profile;

/**
 * Port for "consult patron's profile" use case.
 */
public interface ConsultPatronProfileInputPort {

    void listCurrentHolds(String patronId);

}
