package application;

import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;
public class StudentRoster {

    private Collection<Student> students;
    private Integer count=0;


    public StudentRoster() {
        students = new ArrayList<Student>();

    }



    public void loadRoster(String filename) {

        try {

            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line = reader.readLine();

            while (line != null) {

                String[] name = line.split(", ");
                count+=1;
                students.add(new Student(name[1], name[0]));
                
                line = reader.readLine();

            }

            reader.close();

        } catch (IOException e) {

            System.err.format("IOException: %s%n", e);

        }

    }



    public void printCollection() {

        System.out.println("**** Those students on roster ****");

        for (Student student : students) {

            System.out.println(student.getLastName() + ", " + student.getFirstName());

        }

    }
    
    public Integer get_count() {
    	return count;
    }



    public void printCount() {

        System.out.println("Total number of students: " + count);

    }

   public Collection<Student> getStudents() {
	   return students;
   }

   public boolean contains(Student student) {
	    return students.contains(student);
	}

    // other getters/setters and helper functions as needed
   public List<String> get_student_collection(){
	   List<String> string_collection = new ArrayList<String>();
	   for (Student student : students) {
		   string_collection.add(student.getLastName() + ", " + student.getFirstName());
	   }
	   return string_collection;
	   
   }

}