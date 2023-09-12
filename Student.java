package application;

import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Collection;



public class Student {

    private String firstName;

    private String lastName;



    public Student(String firstName, String lastName) {

        this.firstName = firstName;

        this.lastName = lastName;

    }



    public String getFirstName() {

        return firstName;

    }



    public String getLastName() {

        return lastName;

    }
    public String getName() {
    	String fullname = firstName + lastName;
    	return fullname;
    }

}