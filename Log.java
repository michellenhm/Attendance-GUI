package application;

import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Collection;



public class Log {

    private String name;

    private ArrayList<String> records;



    public Log(String name) {

        this.name = name;

        this.records = new ArrayList<String>();

    }



    public String getName() {

        return name;

    }



    public void addRecord(String record) {

        records.add(record);

    }



    public Collection<String> getRecords() {

        return records;

    }
    public boolean hasStudent(Student student) {
        for (String record : records) {
            String[] fields = record.split(", ");
            if (fields[1].equals(student.getFirstName()) && fields[0].equals(student.getLastName())) {
                return true;
            }
        }
        return false;
    }
    public String getDate(String record) {
    	String[] parts = record.split(",");
    	return parts[3].trim();
    }

}

