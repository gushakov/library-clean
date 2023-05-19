/*
    COPYRIGHT DISCLAIMER
    --------------------

    The code in this file may be based on the original work from
    [ddd-by-examples/library](https://github.com/ddd-by-examples/library).

    Please see the original licence at
    https://github.com/ddd-by-examples/library/blob/master/LICENSE

    and the copyright disclaimer notice in "README.md" (in this repository).
 */

package com.github.libraryclean.core.model;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

import java.security.SecureRandom;

/**
 * Random IDs generator based on JNano ID library.
 *
 * @see NanoIdUtils
 * @see <a href="Nano ID Collision Calculatror">https://zelark.github.io/nano-id-cc/</a>
 */
public class Ids {

    private final static char[] alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    private final static SecureRandom random = new SecureRandom();

    private final static int length = 6;

    public static String next() {
        return NanoIdUtils.randomNanoId(random, alphabet, length);
    }

}
