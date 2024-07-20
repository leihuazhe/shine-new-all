package com.maple.shine.versions.java12;

public class SwitchCaseTest {

    public static void main(String[] args) {
        test1(13);
        test1("1321");
        System.out.println(args[0]);
    }


    public static void test1(Object local) {
        String formatted = switch (local) {
            case Integer i when i > 10 -> String.format("a large Integer %d", i);
            case Integer i -> String.format("a small Integer %d", i);
            case Long l -> String.format("a Long %d", l);
            default -> local.toString();
        };
        System.out.println(formatted);
    }
}
