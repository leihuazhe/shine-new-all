package com.maple.shine.versions.java9.modules.simple_deps.math_lib;

import com.github.wesleyegberto.mathlib.impl.*;
import com.maple.shine.versions.java9.modules.simple_deps.math_api.Operation;

/**
 * We can expose our implementation using an exported public interface
 */
public class MathIntegerOperations {
    public Operation<Integer, Integer> getAdditionOperation() {
        return new AdditionOperation();
    }

    public Operation<Integer, Integer> getSubtractionOperation() {
        return new SubtractionOperation();
    }
}