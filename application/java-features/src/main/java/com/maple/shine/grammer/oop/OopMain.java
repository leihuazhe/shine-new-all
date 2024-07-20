package com.maple.shine.grammer.oop;

public class OopMain {


    public static void main(String[] args) {
        testOop1();
    }

    private static void testOop1() {
        SquareShape squareShape = new SquareShape(10);
        System.out.println(squareShape.getSurface());
    }
}
