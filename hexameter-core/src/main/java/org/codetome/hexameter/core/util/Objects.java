/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.codetome.hexameter.core.util;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Utility methods for objects.
 *
 * @since 1.6
 */
public final class Objects {

    private Objects() {
    }

    /**
     * Returns 0 if {@code a == b}, or {@code c.compare(a, b)} otherwise. That
     * is, this makes {@code c} null-safe.
     */
    public static <T> int compare(T left, T right, Comparator<? super T> comparator) {
        if (left == right) {
            return 0;
        }
        return comparator.compare(left, right);
    }

    /**
     * Returns true if both arguments are null, the result of
     * {@link Arrays#equals} if both arguments are primitive arrays, the result
     * of {@link Arrays#deepEquals} if both arguments are arrays of reference
     * types, and the result of {@link #equals} otherwise.
     */
    public static boolean deepEquals(Object obj1, Object obj2) {
        if (obj1 == null || obj2 == null) {
            return obj1 == obj2;
        } else if (obj1 instanceof Object[] && obj2 instanceof Object[]) {
            return Arrays.deepEquals((Object[]) obj1, (Object[]) obj2);
        } else if (obj1 instanceof boolean[] && obj2 instanceof boolean[]) {
            return Arrays.equals((boolean[]) obj1, (boolean[]) obj2);
        } else if (obj1 instanceof byte[] && obj2 instanceof byte[]) {
            return Arrays.equals((byte[]) obj1, (byte[]) obj2);
        } else if (obj1 instanceof char[] && obj2 instanceof char[]) {
            return Arrays.equals((char[]) obj1, (char[]) obj2);
        } else if (obj1 instanceof double[] && obj2 instanceof double[]) {
            return Arrays.equals((double[]) obj1, (double[]) obj2);
        } else if (obj1 instanceof float[] && obj2 instanceof float[]) {
            return Arrays.equals((float[]) obj1, (float[]) obj2);
        } else if (obj1 instanceof int[] && obj2 instanceof int[]) {
            return Arrays.equals((int[]) obj1, (int[]) obj2);
        } else if (obj1 instanceof long[] && obj2 instanceof long[]) {
            return Arrays.equals((long[]) obj1, (long[]) obj2);
        } else if (obj1 instanceof short[] && obj2 instanceof short[]) {
            return Arrays.equals((short[]) obj1, (short[]) obj2);
        }
        return obj1.equals(obj2);
    }

    /**
     * Null-safe equivalent of {@code a.equals(b)}.
     */
    public static boolean equals(Object obj1, Object obj2) {
        return obj1 == null ? obj2 == null : obj1.equals(obj2);
    }

    /**
     * Convenience wrapper for {@link Arrays#hashCode}, adding varargs. This can
     * be used to compute a hash code for an object's fields as follows:
     * {@code Objects.hash(a, b, c)}.
     */
    public static int hash(Object... values) {
        return Arrays.hashCode(values);
    }

    /**
     * Returns 0 for null or {@code o.hashCode()}.
     */
    public static int hashCode(Object obj) {
        return (obj == null) ? 0 : obj.hashCode();
    }

    /**
     * Returns {@code o} if non-null, or throws {@code NullPointerException}.
     */
    public static <T> T requireNonNull(T value) {
        if (value == null) {
            throw new NullPointerException();
        }
        return value;
    }

    /**
     * Returns {@code o} if non-null, or throws {@code NullPointerException}
     * with the given detail message.
     */
    public static <T> T requireNonNull(T value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
        return value;
    }

    /**
     * Returns "null" for null or {@code o.toString()}.
     */
    public static String toString(Object obj) {
        return (obj == null) ? "null" : obj.toString();
    }

    /**
     * Returns {@code nullString} for null or {@code o.toString()}.
     */
    public static String toString(Object obj, String nullString) {
        return (obj == null) ? nullString : obj.toString();
    }
}
