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
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

/**
 * This test generates some random unique IDs used in mock models. It is based on JNano ID library.
 * @see NanoIdUtils
 * @see <a href="Nano ID Collision Calculatror">https://zelark.github.io/nano-id-cc/</a>
 */
public class IdsTest {

    private final static char[] alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    private final static SecureRandom random = new SecureRandom();

    private final static int length = 6;

    private String next() {
        return NanoIdUtils.randomNanoId(random, alphabet, length);
    }

    @Test
    void generate_some_ids() {
        for (int i = 0; i < 10; i++) {
            System.out.println(next());
        }
    }
}
