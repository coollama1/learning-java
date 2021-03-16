package com.coolprojects.learning.collections.maps;

import com.coolprojects.learning.collections.Person;

public interface PersonLookupTable {
    Person lookupByName(String name);

    void addPerson(Person person);

    void clear();
}
