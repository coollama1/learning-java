package com.coolprojects.learning.collections;


import java.util.Comparator;

public class Person {
    private String name;
    private String id;
    private int age;
    private int height;

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age= age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString(){
        return String.format("(%s,%d)", name, age);
    }

    public final static Comparator<Person> BY_AGE = Comparator.comparingInt(Person::getAge);
}
