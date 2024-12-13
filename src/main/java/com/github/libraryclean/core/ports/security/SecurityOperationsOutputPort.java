package com.github.libraryclean.core.ports.security;

import com.github.libraryclean.core.model.patron.PatronId;

public interface SecurityOperationsOutputPort {

    /**
     * Returns ID of the patron corresponding to the currently authenticated user.
     *
     * @return {@code PatronId} corresponding to the currently authenticated user
     * @throws UserNotAuthenticatedError if user is not authenticated
     */
    PatronId usernameOfLoggedInUser();

}
