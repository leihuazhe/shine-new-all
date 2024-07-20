package com.maple.shine.grammer.oop;

public class SquareShape extends Shape {

    private final int size;

    public SquareShape(int size) {
        this.size = size;
    }

    @Override
    public int computeSurface() {
        return size * size;
    }

    public static void main(String[] args) {
        testOop1();
    }

    private static void testOop1() {
        SquareShape squareShape = new SquareShape(10);
        System.out.println(squareShape.getSurface());
    }
}
