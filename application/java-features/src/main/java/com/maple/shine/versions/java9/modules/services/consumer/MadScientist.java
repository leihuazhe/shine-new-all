package com.maple.shine.versions.java9.modules.services.consumer;

import com.maple.shine.versions.java9.modules.services.provider.Calculator;

import java.util.Iterator;
import java.util.ServiceLoader;

public class MadScientist {
    private static Calculator getImplementation() {
        Iterator<Calculator> impls = ServiceLoader.load(Calculator.class).iterator();
        if (impls.hasNext()) return impls.next();
        throw new RuntimeException("No implementation provided.");
    }

    public static void main(String[] args) {
        Calculator calculator = getImplementation();
        System.out.println("Universe meaning: " + calculator.calculateUniverseMeaning());
    }
}