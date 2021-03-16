package com.coolprojects.learning.collections.streams;

import com.coolprojects.learning.collections.Person;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class StreamTesting {

    private final static List<Person> people = generateListOfPeople();
    private final static int NUMBER_OF_PEOPLE = 20;


    public static void main(String[] args) {
        printPeople();
        printSortedTeenagers();
        printNumberOfTeenagers();
        printAverageAge();
        printPeopleByLastName();
    }



    private static void printPeopleByLastName() {
        people
            .stream()
            .collect(groupingBy(Person::getLastName))
            .forEach((key,val)-> System.out.println(key + " " + val));
    }


    private static void printNumberOfTeenagers() {
        long numOfTeenagers = people
                                .stream()
                                .filter(person -> person.getAge() > 12 && person.getAge() < 20)
                                .collect(counting());
        System.out.println(numOfTeenagers);
    }

    private static void printAverageAge() {
        double avg =  people
                        .stream()
                        .collect(averagingInt(Person::getAge));

        System.out.println(String.format("%.2f",avg));

    }

    private static void printSortedTeenagers() {
        System.out.println("\nName of Teenagers\n");
        people
            .stream()
            .filter(person -> person.getAge() > 12 && person.getAge() < 20)
            .sorted(comparing(Person::getName))
            .map(Person::toString)
            .forEach(System.out::println);
    }

    private static void testRandomNumber() {
        for (int c = 0; c < 100; c++)
            System.out.println(randomNumber(0,5));
    }

    private static void printPeople() {
        people
            .stream()
            .map(Person::toString)
            .forEach(System.out::println);
    }

    //upper exclusive
    public static int randomNumber(int lower, int upper){
        return (int) (lower + (Math.random() * (upper-lower)));
    }

    private static List<Person> generateListOfPeople() {
        return Stream
                    .generate(StreamTesting::generateRandomPerson)
                    .limit(NUMBER_OF_PEOPLE)
                    .collect(toList());

    }

    private static Person generateRandomPerson(){
        String [] possibleFirstNames = {"Marvin", "George", "Nate", "Clark", "Simon"};
        String [] possibleLastNames = {"Smith", "Brown", "Estime", "Gagarin" };
        String randomFirstName = possibleFirstNames[randomNumber(0,5)];
        String randomLastName = possibleLastNames[randomNumber(0,4)];
        int randomAge = randomNumber(5,31);
        Person newPerson =new Person(randomFirstName + " " + randomLastName, randomAge);

        return newPerson;
    }

}
