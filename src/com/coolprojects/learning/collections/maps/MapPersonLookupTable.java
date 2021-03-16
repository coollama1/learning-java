package com.coolprojects.learning.collections.maps;

import com.coolprojects.learning.collections.Person;

import java.util.HashMap;
import java.util.Map;

public class MapPersonLookupTable implements PersonLookupTable {

    private final Map<String, Person> nameToPerson = new HashMap<>();

    @Override
    public Person lookupByName(String name) {
        return nameToPerson.get(name);
    }

    @Override
    public void addPerson(Person personToAdd) {
        String name = personToAdd.getName();
        if(nameToPerson.containsKey(name))
            throw new IllegalArgumentException("Cannot add an entry which contains a key that already exists");
        nameToPerson.put(name,personToAdd);
    }

    @Override
    public void clear() {
        nameToPerson.clear();
    }
}
