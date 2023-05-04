package com.github.libraryclean.core;

import com.github.libraryclean.core.model.InvalidDomainObjectError;

import java.util.Set;

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

    public static <T> Set<T> copy(Set<T> original) {
        if (original == null) {
            return null;
        } else {
            return Set.copyOf(original);
        }
    }

    public static int positive(int number){
        if (number <= 0){
            throw new InvalidDomainObjectError("Property must be a strictly positive integer value");
        }
        else {
            return number;
        }
    }

}
