package com.coolprojects.learning.collections;


import java.util.Comparator;
import java.util.Objects;

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

    public String getFirstName(){
        String [] names = getNames();
        return (names.length == 2) ? names[0] : "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && height == person.height && name.equals(person.name) && Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, age, height);
    }

    public String getLastName(){
        String [] names = getNames();
        return (names.length == 2) ? names[1] : "";
    }

    private String [] getNames(){
        return name.split(" ", 0);
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
        return String.format("(%s , %d)", name, age);
    }

    public final static Comparator<Person> BY_AGE = Comparator.comparingInt(Person::getAge);
}
