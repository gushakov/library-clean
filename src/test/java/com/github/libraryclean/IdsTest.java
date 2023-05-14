/*
    COPYRIGHT DISCLAIMER
    --------------------

    The code in this file may be based on the original work from
    [ddd-by-examples/library](https://github.com/ddd-by-examples/library).

    Please see the original licence at
    https://github.com/ddd-by-examples/library/blob/master/LICENSE

    and the copyright disclaimer notice in "README.md" (in this repository).
 */

package com.github.libraryclean;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.github.libraryclean.core.model.Ids;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * This test generates some random unique IDs used in mock models. It is based on JNano ID library.
 *
 * @see NanoIdUtils
 * @see <a href="Nano ID Collision Calculatror">https://zelark.github.io/nano-id-cc/</a>
 */
public class IdsTest {

    @Test
    void generates_unique_ids() {
        Set<String> ids = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            String next = Ids.next();
            System.out.println(next);
            ids.add(next);
        }
        // assert all IDs are unique
        assertThat(ids).hasSize(10);
    }
}
