package com.coolprojects.learning.collections.maps;

import com.coolprojects.learning.collections.Person;

import java.util.ArrayList;
import java.util.List;

public class ListPersonLookupTable implements PersonLookupTable {

    private final List<Person> people = new ArrayList<>();

    @Override
    public Person lookupByName(String name) {
        for(Person person : people)
            if(person.getName().equals(name))
                return person;

        return null;
    }

    @Override
    public void addPerson(Person personToAdd) {
        String name = personToAdd.getName();
        for(Person currentPerson : people)
            if(currentPerson.getName().equals(name))
                throw new IllegalArgumentException("Cannot add a person with a name that already exists in the lookup table");

        people.add(personToAdd);
    }

    @Override
    public void clear() {
        people.clear();
    }
}
