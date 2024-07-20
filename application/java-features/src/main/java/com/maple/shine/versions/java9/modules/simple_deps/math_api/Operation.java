package com.maple.shine.versions.java9.modules.simple_deps.math_api;

/**
 * with exported public interface
 * we can access a non-exported class
 */
public interface Operation<T, E> {
    T apply(E x, E y);
}