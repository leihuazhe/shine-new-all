package com.github.wesleyegberto.mathlib.impl;

import com.maple.shine.versions.java9.modules.simple_deps.math_api.Operation;

/**
 * This implementation we won't export, but we can access it
 * through its interface - Operation
 */
public class AdditionOperation implements Operation<Integer, Integer> {
    public Integer apply(Integer x, Integer y) {
        return x + y;
    }
}