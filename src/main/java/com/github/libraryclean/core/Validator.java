package com.github.libraryclean.core;

import com.github.libraryclean.core.model.InvalidDomainObjectError;

public class Validator {

    public static <T> T notNull(T anything) {
        if (anything == null) {
            throw new InvalidDomainObjectError("Property cannot be null");
        }
        return anything;
    }

    public static String notBlank(String anyString) {
        if (notNull(anyString).matches("\\s*")) {
            throw new InvalidDomainObjectError("Property cannot be blank");
        }
        return anyString;
    }

}
