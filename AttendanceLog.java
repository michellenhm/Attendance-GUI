package application;

import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;

public class AttendanceLog {

    private Collection<Log> logs;
    private Integer count=0;



    public AttendanceLog() {

        logs = new ArrayList<Log>();

    }



    public void loadLog(String filename) {
    	logs.clear();
        try {

            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line = reader.readLine();

            Log log = new Log(filename);

            while (line != null) {

                log.addRecord(line);

                line = reader.readLine();

            }

            logs.add(log);
            count+=1;
            reader.close();

        } catch (IOException e) {

            System.err.format("IOException: %s%n", e);

        }

    }



    public void printCollection() {

        System.out.println("** This is the entire Collection Data currently stored **");

        for (Log log : logs) {

            System.out.println(log.getName() + ":");

            for (String record : log.getRecords()) {
            	count+=1;
                System.out.println(record);

            }

        }

    }



    public void printCount() {

        //int count = 0;

//        for (Log log : logs) {
//
//            count += log.getRecords().size();
//
//        }
    	Integer count_fix = count-1;
        System.out.println("Total number of logs: " + count_fix);

    }
    
    public Integer get_count() {
    	return count;
    }
    
    public Collection<Log> getLogs() {
    	return logs;
    }
    
//    public Collection<Log> getLogsForDate(String date) {
//        Collection<Log> logsForDate = new ArrayList<>();
//        for (Log log : logs) {
//            for (String record : log.getRecords()) {
//                String[] recordArray = record.split(", ");
//                if (recordArray[2].endsWith(date) && !logsForDate.contains(log)) {
//                    logsForDate.add(log);
//                    break;
//                }
//            }
//        }
//        return logsForDate;
//    }
    public Collection<String> getLogsForDate(String date) {
        Collection<String> matchingLogs = new ArrayList<String>();
        for (Log log : logs) {
            for (String record : log.getRecords()) {
                if (log.getDate(record).equals(date)) {
                    matchingLogs.add(record);
                }
            }
        }
        return matchingLogs;
    }
    
    //testing getlogsfordate
//    Collection<String> matchingLogs = attendanceLog.getLogsForDate("11/2/2022");
//	for (String record : matchingLogs) {
//	    System.out.println(record);
//	}
//	break;





    // other getters/setters and helper functions as needed
    public List<String> get_log_collection(){
 	   List<String> string_collection = new ArrayList<String>();
 	  for (Log log : logs) {

          string_collection.add(log.getName() + ":");

          for (String record : log.getRecords()) {

        	  string_collection.add(record);

          }

      }
 	   return string_collection;
 	   
    }
    
    

}