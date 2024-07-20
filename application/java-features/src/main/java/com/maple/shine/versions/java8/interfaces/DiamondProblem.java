package com.maple.shine.versions.java8.interfaces;

interface Father {
    default void walk() {
        System.out.println("Walking like the father");
    }
}

interface Mother {
    default void walk() {
        System.out.println("Walking like the mother");
    }
}

public class DiamondProblem {
    public static void main(String[] args) {
        new TooMuch().walk();
    }
}

/*
 * When the interfaces that we are implementing has
 * the same methods signatures then we must override them.
 */
class TooMuch implements Father, Mother {
    public void walk() {
        System.out.println("Walking like myself");
    }
}