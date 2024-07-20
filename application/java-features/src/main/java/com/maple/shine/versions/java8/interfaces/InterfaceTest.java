package com.maple.shine.versions.java8.interfaces;

interface A {
    static void staticMethod() {
        System.out.println("In interface A static method");
    }

    void abstractMethod();

    default void concreteMethod() {
        System.out.println("In interface A concrete method");
    }
}

interface B extends A {
    static void staticMethod() {
        System.out.println("In interface B static method");
    }

    default void concreteMethod() {
        System.out.println("In interface B concrete method");
    }
}

interface C {
    default void concreteMethod() {
        System.out.println("In interface C concrete method");
    }
}

/**
 * @author Wesley Egberto
 */
public class InterfaceTest implements B/*, C*/ {
    public static void main(String[] args) {
        new InterfaceTest().go();
    }

    @Override
    public void abstractMethod() {
        System.out.println("In class implemented method");
    }

    public void go() {
        concreteMethod();
        A.staticMethod();
        B.staticMethod();
        // staticMethod(); isn't inherited
    }
}