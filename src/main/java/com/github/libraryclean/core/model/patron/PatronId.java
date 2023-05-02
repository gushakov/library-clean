package com.github.libraryclean.core.model.patron;

import lombok.Builder;
import lombok.Value;

import static com.github.libraryclean.core.Validator.notBlank;

/**
 * Unique ID of a patron.
 */
@Value
public class PatronId {


    String id;

    public static PatronId of(String id) {
        return PatronId.builder()
                .id(id)
                .build();
    }

    @Builder
    public PatronId(String id) {
        this.id = notBlank(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
