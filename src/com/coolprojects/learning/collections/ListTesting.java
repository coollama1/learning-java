package com.coolprojects.learning.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListTesting {

    public static final int CAP_AGE = 20;

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Marvin", 23));
        people.add(new Person("Karnowsky", 24));
        people.add(new Person("Carl", 15));
        int size = people.size();

        Iterator<Person> iterator = people.iterator();

        //using the iterator.remove() method to remove objects from collections
/*
        while(iterator.hasNext()){
            Person currentPerson = iterator.next();
            if(currentPerson.getAge() > CAP_AGE){
                System.out.println(currentPerson);
                iterator.remove();
            }
        }
        System.out.println(people);
*/

        people.sort(Person.BY_AGE);

        System.out.println(people);


    }
}
