package com.maple.shine.versions.java8.model;

public class Warrior {
    private final Person person;
    private final String type;

    public Warrior(Person person) {
        this.person = person;
        type = "Warrior " + person.getAge();
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return type;
    }

}