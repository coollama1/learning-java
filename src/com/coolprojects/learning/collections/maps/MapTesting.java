package com.coolprojects.learning.collections.maps;

import com.coolprojects.learning.collections.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MapTesting {

    private static final int NUMBER_OF_PEOPLE = 10_000;
    private static final int ITERATIONS = 8;
    private static final List<Person> people = generateListOfPeople();

    private static List<Person> generateListOfPeople() {
        List<Person> listOfPeople = new ArrayList<>();
        for(int c = 0; c < NUMBER_OF_PEOPLE; c++){
            listOfPeople.add(new Person("Person" + c, Integer.toString(c)));
        }
        Collections.shuffle(listOfPeople);
        Collections.shuffle(listOfPeople);
        Collections.shuffle(listOfPeople);
        return listOfPeople;
    }

    public static void main(String[] args) {
        runLookupTableTests(new ListPersonLookupTable());
        runLookupTableTests(new MapPersonLookupTable());
    }

    private static void runLookupTableTests(PersonLookupTable lookupTable){
        System.out.println("Running the tests for the " +  lookupTable.getClass() + " class");

        for(int c = 0; c < ITERATIONS; c++){
            long startTime = System.currentTimeMillis();
            for(Person person : people){
                lookupTable.addPerson(person);
            }
            for(Person person : people){
                Person result = lookupTable.lookupByName(person.getName());
                if(person != result){
                    throw new IllegalStateException("Specified person cannot be found in lookup table");
                }
            }
            lookupTable.clear();
            System.out.println("Runtime: " + (System.currentTimeMillis() - startTime) + "ms");
        }
    }
}
