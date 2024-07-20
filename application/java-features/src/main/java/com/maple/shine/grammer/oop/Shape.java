package com.maple.shine.grammer.oop;

public abstract class Shape {
    private final int surface;

    public Shape() {
        this.surface = computeSurface();
    }

    public abstract int computeSurface();

    public int getSurface() {
        return surface;
    }
}

