package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AttendanceApp {
    public StudentRoster roster;
    public AttendanceLog log;
    private List<String> query = new ArrayList<String>();
    private Integer count=0;
    
    public AttendanceApp(String rosterFilename, String logFilename) {
        roster = new StudentRoster();
        log = new AttendanceLog();

        roster.loadRoster(rosterFilename);
		log.loadLog(logFilename);
    }


    public void list_students_not_in_class(Collection<Log> collection, Collection<Student> collection2) {
    	query.clear();
    	this.count=0;
    	query.add("****** Students missing in class *************");
        //List<Student> missingStudents = new ArrayList<>();
    	///List<String> missing= new ArrayList<>();
        for (Student student : collection2) {
            boolean isPresent = false;

            for (Log log : collection) {
                if (log.hasStudent(student)) {
                    isPresent = true;
                    break;
                }
            }

            if (!isPresent) {
                //missingStudents.add(student);
            	this.query.add(student.getLastName() + ", " + student.getFirstName());
            	this.count+=1;
            }
        }
        //System.out.println("****** Students missing in class *************");
//        for (Student student : missingStudents) {
//            System.out.println(student.getLastName() + ", " + student.getFirstName());
//        }
        ///for(String s : missing) {
        	///System.out.println(s);
        ///}
        //return missing;
        //return missingStudents;
    }
    


    public void list_all_times_checking_in_and_out(StudentRoster roster, AttendanceLog log, String firstName, String lastName) {
        query.clear();
        this.count = 0;
        boolean studentFound=false;
        query.add("****** List all swipe in and out for a student *******");
        for (Log attendance : log.getLogs()) {
            for (String record : attendance.getRecords()) {
                String[] fields = record.split(", ");
                if (fields[0].equals(lastName) && fields[1].equals(firstName)) {
                    //System.out.println(record);
                    this.query.add(record);
                    this.count+=1;
                    studentFound=true;
                }
            }
        }
        if(!studentFound) {
        	query.add("No student of this name in the attendance log");
        }
        //System.out.println("********************************\n");
    }


    
    public void list_all_times_checked_in(StudentRoster roster, AttendanceLog log) {
    	query.clear();
    	this.count=0;

        query.add("****** Check in times for all students who attended***");
        for (Student student : roster.getStudents()) {
            String firstName = student.getFirstName();
            String lastName = student.getLastName();
            for (Log attendance : log.getLogs()) {
                for (String record : attendance.getRecords()) {
                    String[] fields = record.split(", ");
                    if (fields[0].equals(lastName) && fields[1].equals(firstName)) {
                        //System.out.println(record);
                        this.query.add(record);
                        this.count+=1;
                        
                        break;
                    }
                }
            }
        }
      
        //System.out.println("********************************\n");
    }


    
    
//
//    public void list_students_late_to_class(String timestamp, AttendanceLog log) {
//        List<String> lateStudents = new ArrayList<String>();
//        Collection<String> logsForDate = log.getLogsForDate(getDateFromTimestamp(timestamp));
//
//        for (String record : logsForDate) {
//            String[] fields = record.split(", ");
//            if (fields[2].compareTo(timestamp) > 0) {
//                lateStudents.add(String.join(", ", fields[0], fields[1], fields[2], fields[3]));
//            }
//        }
//
//        if (lateStudents.size() > 0) {
//            System.out.println("****** Students that arrived late ********************");
//            for (String student : lateStudents) {
//                System.out.println(student);
//            }
//            System.out.println("***************************************************\n");
//        }
//
//        //return lateStudents;
//    }

    private String getDateFromTimestamp(String timestamp) {
    	
        return timestamp.substring(timestamp.indexOf(", ") + 2);
    }
    
    
    
//    public void list_students_late_to_class1(String timestamp, String date, AttendanceLog log) {
//    	query.clear();
//    	this.count=0;
//    	query.add("****** Students that arrived late ********************");
//    	//List<String> lateStudents = new ArrayList<String>();
//    	Collection<String> logsForDate = log.getLogsForDate(date);
//        for (String record : logsForDate) {
//        	String[] fields = record.split(", ");
//            if (fields[2].compareTo(timestamp) > 0) {
//                //lateStudents.add(String.join(", ", fields[0], fields[1], fields[2], fields[3]));
//                this.query.add(String.join(", ", fields[0], fields[1], fields[2], fields[3]));
//                this.count+=1;
//            }
//        }
//        //if (lateStudents.size() > 0) {
////        if (this.query.size() > 0) {
////            System.out.println("****** Students that arrived late ********************");
////            //for (String student : lateStudents) {
////            for (String student : this.query) {
////                System.out.println(student);
////            }
////            
////        }
//        //System.out.println("*****************************************************");
//    }
    
    public void list_students_late_to_class(String timestamp, String date, AttendanceLog log) {
        query.clear();
        count = 0;
        query.add("****** Students that arrived late ********************");
        String[] startFields = timestamp.split(":");
        int startHour = Integer.parseInt(startFields[0]);
        int startMinute = Integer.parseInt(startFields[1]);
        int startSecond = Integer.parseInt(startFields[2]);
        Collection<String> logsForDate = log.getLogsForDate(date);
        for (String record : logsForDate) {
            String[] fields = record.split(", ");
            String[] arrivalFields = fields[2].split(":");
            int arrivalHour = Integer.parseInt(arrivalFields[0]);
            int arrivalMinute = Integer.parseInt(arrivalFields[1]);
            int arrivalSecond = Integer.parseInt(arrivalFields[2]);
            if (arrivalHour == startHour && (arrivalMinute > startMinute || (arrivalMinute == startMinute && arrivalSecond > startSecond))) {
                query.add(String.join(", ", fields));
                count++;
            }
        }
    }

    
    public Collection<String> getLogsForDate(String timestamp, AttendanceLog log) {
        Collection<String> matchingLogs = new ArrayList<>();
        String date = getDateFromTimestamp(timestamp);
        for (Log l : log.getLogs()) {
            for (String record : l.getRecords()) {
                if (l.getDate(record).equals(date)) {
                    if (record.split(", ")[2].compareTo(timestamp) <= 0) {
                        matchingLogs.add(record);
                        //this.query.add(record);
                        //count+=1;
                    }
                }
            }
        }
        
        return matchingLogs;
    }

    
    

    

    
    public void print_attendance_data_for_student(String firstname, String lastname, AttendanceLog attendanceLog) {
    	query.clear();
    	this.count=0;
        query.add("********* Looking up Student Attendance Data ***********");
        
        Student student = new Student(firstname, lastname);
        boolean foundStudent = false;
        //List<String> records = new ArrayList<>();
        for (Log log : attendanceLog.getLogs()) {
            if (log.hasStudent(student)) {
                foundStudent = true;
                for (String record : log.getRecords()) {
                    String[] fields = record.split(", ");
                    if (fields[1].equals(firstname) && fields[0].equals(lastname)) {
                        String date = log.getDate(record);
                        //records.add(fields[2] + ", " + date);
                        this.query.add(fields[2] + ", " + date);
                        count+=1;
                    }
                }
            }
        }

        // Print a message if the student wasn't found
        if (!foundStudent) {
            query.add("No student of this name in the attendance log");
       } 
            //else {
//            System.out.println(lastname + ", " + firstname + " " + records.toString());
//        }
    }





    

    public void is_present(String firstName, String lastName, String date, AttendanceLog attendanceLog) {
    	query.clear();
    	this.count=0;
    	query.add("**** Looking to see if Student was present on date ****");
    	boolean present = false;
        for (Log log : attendanceLog.getLogs()) {
            if (log.hasStudent(new Student(firstName, lastName))) {
                
            	for (String record : log.getRecords()) {
                    if (log.getDate(record).equals(date)) {
                    	present=true;
                        //return true;
                    }
                }
            }
        }
        if(present) {
        	this.query.add("True");
        }
        this.count=1;
        //return false;
    }



    public void list_all_students_checked_in(String date, AttendanceLog attendanceLog) {
    	query.clear();
    	this.count=0;
        query.add("**** Students present on this date ****");
        //int count = 0;
        for (Log log : attendanceLog.getLogs()) {
            for (String record : log.getRecords()) {
                if (log.getDate(record).equals(date)) {
                    String[] fields = record.split(", ");
                    String lastName = fields[0];
                    String firstName = fields[1];
                    //System.out.println(firstName + " " + lastName);
                    this.query.add(firstName + " " + lastName);
                    this.count++;
                }
            }
        }
        //System.out.println("Total number of students: " + count);
    }

    
    public void get_first_student_to_enter(String date, AttendanceLog log) {////////////////////
    	query.clear();
    	this.count=0;
        Collection<String> logsForDate = log.getLogsForDate(date);

        List<String> sortedLogs = new ArrayList<>(logsForDate);
        Collections.sort(sortedLogs, new Comparator<String>() {
            public int compare(String log1, String log2) {
                String[] fields1 = log1.split(", ");
                String[] fields2 = log2.split(", ");
                return fields1[2].compareTo(fields2[2]);
            }
        });

        String firstStudent = sortedLogs.get(0);
        String[] fields = firstStudent.split(", ");

        //return "**** First student to enter on " + date + " ****\n" + fields[1] + ", " + fields[0];
        
        this.query.add("**** First student to enter on " + date + " ****\n" + fields[1] + ", " + fields[0]);
        this.count=1;
    }


    
//TODO
    public void list_all_students_checked_in_before(String time, String date, AttendanceLog log) {
    	query.clear();
    	this.count=0;
    	query.add("**** Those present on date & before a time assigned ****");
        //List<String> presentStudents = new ArrayList<>();
        for (Log classLog : log.getLogs()) {
            for (String record : classLog.getRecords()) {
                String recordDate = classLog.getDate(record);
                if (recordDate.equals(date)) {
                    String[] fields = record.split(", ");
                    String recordTime = fields[2];
                    if (recordTime.compareTo(time) < 0) {
                        String fullName = fields[0] + ", " + fields[1];
                        //presentStudents.add(fullName);
                        this.query.add(fullName);
                        this.count+=1;
                    }
                }
            }
        }
//        System.out.println("**** Those present on date & before a time assigned ****");
//        for (String student : presentStudents) {
//            System.out.println(student);
//        }
//        System.out.println("There were " + presentStudents.size() + " records for this query");
        //return presentStudents;
    }

    public void list_students_attendance_count(int days, AttendanceLog attendanceLog, StudentRoster studentRoster) { 
        query.clear();
        this.count = 0;

        query.add("**** Those who attended " + days + " classes ****\r\n");
        List<String> students = new ArrayList<>();
        for (Student student : studentRoster.getStudents()) {
            String lastName = student.getLastName();
            String firstName = student.getFirstName();
            boolean foundInLog = false;
            for (Log log : attendanceLog.getLogs()) {
                for (String record : log.getRecords()) {
                    String[] fields = record.split(", ");
                    if (fields[0].equals(lastName) && fields[1].equals(firstName)) {
                        foundInLog = true;
                        break;
                    }
                }
                if (foundInLog) {
                    break;
                }
            }
            if ((foundInLog && days > 0) || (!foundInLog && days == 0)) {
                String studentName = firstName + " " + lastName;
                if (!students.contains(studentName)) {
                    this.query.add(studentName);
                    students.add(studentName);
                    this.count++;
                }
            }
        }
    }



   



    public void print_query_list() {
        System.out.println("**** Query Results ****");
        for(String s : this.query) {
        	System.out.println(s);
        }
        System.out.println("***********************");
        System.out.println("\n");
    }




    public void print_count() {
    	Integer fix = query.size()-1;
        System.out.println("There were " + fix + " records for this query");
    }


	public List<String> get_query() {
		// TODO Auto-generated method stub
		return this.query;
	}
	
	public Integer get_count() {
		return query.size();
	}

 

    
}
