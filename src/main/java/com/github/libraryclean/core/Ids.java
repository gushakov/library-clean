package com.github.libraryclean.core;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

import java.security.SecureRandom;

/**
 * Random IDs generator using JNano ID library.
 *
 * @see NanoIdUtils
 * @see <a href="Nano ID Collision Calculatror">https://zelark.github.io/nano-id-cc/</a>
 */
public final class Ids {

    private final static char[] alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    private final static SecureRandom random = new SecureRandom();

    private final static int length = 6;

    public static String next() {
        return NanoIdUtils.randomNanoId(random, alphabet, length);
    }

}
