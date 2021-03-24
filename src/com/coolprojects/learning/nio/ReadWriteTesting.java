package com.coolprojects.learning.nio;

import com.coolprojects.learning.collections.Person;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class ReadWriteTesting {

    private static final String FILE_NAME = "src\\com\\coolprojects\\learning\\nio\\testing.txt";
    private static final List<Person> listOfPeople = readPeopleFromFile("src\\com\\coolprojects\\learning\\nio\\people.txt");
    private static final String TEXT = "Hi\nmy name is\nslim shady";

    public static void main(String[] args) {
        testBufferedReader(FILE_NAME);
        testBufferedWriter(FILE_NAME,TEXT);
        printListOfPeople();
    }

    private static void printListOfPeople() {
        listOfPeople.forEach(System.out::println);
    }

    private static void testBufferedWriter(String pathName, String text) {
        Path path = Paths.get(pathName);
        try{
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write(text);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private static void testBufferedReader(String pathName) {
        Path path = Paths.get(pathName);
        //try with resources
        try(BufferedReader reader = Files.newBufferedReader(path)){
            reader
                .lines()
                .forEach(System.out::println);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static List<Person> readPeopleFromFile(String pathName){
        Path path = Paths.get(pathName);
        try(BufferedReader reader = Files.newBufferedReader(path)){
            return reader
                        .lines()
                        .flatMap(ReadWriteTesting::stringToPerson)
                        .collect(toList());
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static Stream <Person> stringToPerson(String personString){
        String [] tokens = personString.split(" ");

        if(tokens.length >= 2){
            String name = tokens[0];
            String secondString = tokens[1];
            try{
                int age = Integer.parseInt(secondString);
                return Stream.of(new Person(name,age));
            }
            catch(NumberFormatException e){

            }
        }
        return Stream.empty();
    }
}
