package com.github.libraryclean.core;

import org.junit.jupiter.api.Test;

public class IdsTest {

    @Test
    void generate_some_ids() {
        System.out.println(Ids.next());
        System.out.println(Ids.next());
        System.out.println(Ids.next());
        System.out.println(Ids.next());
    }
}
