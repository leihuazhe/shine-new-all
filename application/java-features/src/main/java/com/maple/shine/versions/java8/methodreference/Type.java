package com.maple.shine.versions.java8.methodreference;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Wesley Egberto
 */
public class Type {
    public static Type create(Supplier<Type> supplier) {
        return supplier.get();
    }

    public static void test(Type t) {
        System.out.println(t.toString());
    }

    public static void main(String[] args) {
        Type t1 = Type.create(Type::new);

        List<Type> list = Collections.singletonList(t1);

        list.forEach(Type::printModel);

        list.forEach(Type::test);
    }

    public void printModel() {
        System.out.println(hashCode());
    }
}
