package application;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        AttendanceLog attendanceLog = new AttendanceLog();
        //attendanceLog.loadLog("./src/application/data/dataAllShow1stAnd2ndClass.txt");

        StudentRoster studentRoster = new StudentRoster();
        //studentRoster.loadRoster("./src/application/data/rosters.txt");

        AttendanceApp app = new AttendanceApp("./src/application/data/rosters.txt", "./src/application/data/dataAllShow1stAnd2ndClass.txt");
       
        System.out.println("Choose an option from the menu: ");
    	Scanner input = new Scanner(System.in);
        boolean exit = false;



        while (!exit) {
        	System.out.println("Attendance Log Options:");
            System.out.println("A - load_log()");
            System.out.println("B - print_collection()");
            System.out.println("C - print_count()");
            
            System.out.println("Student Roster Options:");
            System.out.println("D - load_log()");
            System.out.println("E - print_collection()");
            System.out.println("F - print_count()");
            
            System.out.println("Attendance App Options:");
            System.out.println("G - list_students_not_in_class()");
            System.out.println("H - list_all_times_checking_in_and_out()");
            System.out.println("I - list_all_times_checked_in()");
            System.out.println("J - list_students_late_to_class()");
            System.out.println("K - get_first_student_to_enter()");
            System.out.println("L - print_attendance_data_for_student()");
            System.out.println("M - is_present()");
            System.out.println("N - list_all_students_checked_in()");
            System.out.println("O - list_all_students_checked_in_before()");
            System.out.println("P - list_students_attendance_count()");
            System.out.println("Q - get_first_student_to_enter()");
            System.out.println("R - print_query_list()");
            System.out.println("S - print_count()");
            System.out.println("0 - Exit program");

            String choice = input.next().toUpperCase();
   
            switch (choice) {
	            case "A":
	                System.out.println("Enter log filename:");
	                String logFilename = input.next();
	                attendanceLog.loadLog(logFilename);
	                //app.log.loadLog(logFilename);
	                break;
	            case "B":
	            	if (attendanceLog.get_count() == 0 || studentRoster.get_count() == 0) {
                        System.out.println("Please load the attendance log data file and roster file first.\n");
                        
                    }
	            	else {
	            		System.out.println("\n");
                		System.out.println("****************");
	            		attendanceLog.printCollection();
	            		
                		System.out.println("****************");
                		System.out.println("\n");
	            	}
	                //app.log.printCollection();
	                break;
	            case "C":
	            	if (attendanceLog.get_count() == 0 || studentRoster.get_count() == 0) {
                        System.out.println("Please load the attendance log data file and roster file first.\n");
                        
                    }
	            	
	            	else {
	            		System.out.println("\n");
	            		System.out.println("****************");
	            		attendanceLog.printCount();
	            		System.out.println("****************");
	            		System.out.println("\n");
	            	}
	                //app.log.printCount();
	            	break;
	            case "D":
	                System.out.println("Enter roster filename:");
	                String rosterFilename = input.next();
	                studentRoster.loadRoster(rosterFilename);
	                break;
	            case "E":
	            	if (attendanceLog.get_count() == 0 || studentRoster.get_count() == 0) {
                        System.out.println("Please load the attendance log data file and roster file first.\n");
                        
                    }
	            	else {
	            		System.out.println("\n");
	            		System.out.println("****************");
	            		studentRoster.printCollection();
	            		
	            		System.out.println("****************");
	            		System.out.println("\n");
	            	}
	                break;
	            case "F":
	            	if (attendanceLog.get_count() == 0 || studentRoster.get_count() == 0) {
                        System.out.println("Please load the attendance log data file and roster file first.\n");
                        
                    }
	            	else {
	            		System.out.println("\n");
	            		System.out.println("****************");
	            		studentRoster.printCount();
	            		
	            		System.out.println("****************");
	            		System.out.println("\n");
	            	}
	                break;
                case "G": 
                	if (attendanceLog.get_count() == 0 || studentRoster.get_count() == 0) {
                        System.out.println("Please load the attendance log data file and roster file first.\n");
                        
                    }
                	else
                		app.list_students_not_in_class(attendanceLog.getLogs(), studentRoster.getStudents());
                    
                    break;
                case "H":
//                	System.out.println("Enter first name:");
//                    String firstName = input.next();
//                    System.out.println("Enter last name:");
//                    String lastName = input.next();
                	
                	if (attendanceLog.get_count() == 0 || studentRoster.get_count() == 0) {
                        System.out.println("Please load the attendance log data file and roster file first.\n");
                        
                    }
                	else {
	                    String nameRegex2 = "[a-zA-Z]+";
	                	
	                	System.out.println("Enter student's first name: ");
	                	String firstName = input.next();
	                	while (!firstName.matches(nameRegex2)) {
	                	    System.out.println("Invalid input format. Please enter a valid first name: ");
	                	    firstName = input.next();
	                	}
	
	                	System.out.println("Enter student's last name: ");
	                	String lastName = input.next();
	                	while (!lastName.matches(nameRegex2)) {
	                	    System.out.println("Invalid input format. Please enter a valid last name: ");
	                	    lastName = input.next();
	                	}
	                    
	                    app.list_all_times_checking_in_and_out(studentRoster, attendanceLog, firstName, lastName);
                	}
                    
                    break;   
                    
                case "I":
                	
                	if (attendanceLog.get_count() == 0 || studentRoster.get_count() == 0) {
                        System.out.println("Please load the attendance log data file and roster file first.\n");
                        
                    }
                	else
                		app.list_all_times_checked_in(studentRoster, attendanceLog); ////??
                    break;
                  
                case "J":
//                	System.out.print("Enter timestamp (hh:mm:ss :");
//                	String timestamp = input.next();
//                	System.out.print("Enter date (MM/DD/YYYY): ");
//                    String date1 = input.next();
//                    String timestamp = "09:45:04";
//                	String date1 = "11/2/2022";
                    //app.list_students_late_to_class(timestamp,date1,attendanceLog); ///??
                	
                	if (attendanceLog.get_count() == 0 || studentRoster.get_count() == 0) {
                        System.out.println("Please load the attendance log data file and roster file first.\n");
                        
                    }
                	
                	else {
	                	System.out.print("Enter timestamp (HH:MM:SS): ");
	                	String timestamp = "";
	                	while (!timestamp.matches("\\d{2}:\\d{2}:\\d{2}")) {
	                	    timestamp = input.next();
	                	    if (!timestamp.matches("\\d{2}:\\d{2}:\\d{2}")) {
	                	        System.out.println("Invalid format. Please enter timestamp in HH:MM:SS format.");
	                	    }
	                	}
	
	                	System.out.println("Enter date  (MM/dd/yyyy): ");
	                	String date1 = "";
	                	while (true) {
	                	    try {
	                	        date1 = input.next();
	                	        String[] dateParts = date1.split("/");
	                	        int month = Integer.parseInt(dateParts[0]);
	                	        int day = Integer.parseInt(dateParts[1]);
	                	        int year = Integer.parseInt(dateParts[2]);
	                	        if (month >= 1 && month <= 12 && day >= 1 && day <= 31 && year >= 1900 && year <= 2100) {
	                	            break;
	                	        }
	                	    } catch (Exception e) {
	                	        // Do nothing
	                	    }
	                	    System.out.println("Please enter the date in the correct format (MM/dd/yyyy): ");
	                	    input.nextLine();
	                	}
	
	                	app.list_students_late_to_class(timestamp, date1, attendanceLog);
                	}

                    break;
                    
                case "K":
                	//System.out.println("Enter a date (MM/dd/yyyy): ");
                	//String date2 = input.next();
                	if (attendanceLog.get_count() == 0 || studentRoster.get_count() == 0) {
                        System.out.println("Please load the attendance log data file and roster file first.\n");
                        
                    }
                	else {
	                	System.out.println("Enter date (MM/dd/yyyy): ");
	                	String date2 = "";
	                	while (true) {
	                	    try {
	                	        date2 = input.next();
	                	        String[] dateParts = date2.split("/");
	                	        int month = Integer.parseInt(dateParts[0]);
	                	        int day = Integer.parseInt(dateParts[1]);
	                	        int year = Integer.parseInt(dateParts[2]);
	                	        if (month >= 1 && month <= 12 && day >= 1 && day <= 31 && year >= 1900 && year <= 2100) {
	                	            break;
	                	        }
	                	    } catch (Exception e) {
	                	        // Do nothing
	                	    }
	                	    System.out.println("Please enter the date in the correct format (MM/dd/yyyy): ");
	                	    input.nextLine();
	                	}
	                    app.get_first_student_to_enter(date2, attendanceLog);
                	}
                    break;

                
                case "L":
//                    System.out.println("Enter student's first name: ");
//                    String firstName1 = input.next();
//                    System.out.println("Enter student's last name: ");
//                    String lastName1 = input.next();
                	if (attendanceLog.get_count() == 0 || studentRoster.get_count() == 0) {
                        System.out.println("Please load the attendance log data file and roster file first.\n");
                        
                    }
                	
                	else {
            
	                	String nameRegex = "[a-zA-Z]+";
	                	
	                	System.out.println("Enter student's first name: ");
	                	String firstName1 = input.next();
	                	while (!firstName1.matches(nameRegex)) {
	                	    System.out.println("Invalid input format. Please enter a valid first name: ");
	                	    firstName1 = input.next();
	                	}
	
	                	System.out.println("Enter student's last name: ");
	                	String lastName1 = input.next();
	                	while (!lastName1.matches(nameRegex)) {
	                	    System.out.println("Invalid input format. Please enter a valid last name: ");
	                	    lastName1 = input.next();
	                	}
	                    
	                    
	                    app.print_attendance_data_for_student(firstName1, lastName1, attendanceLog);
                    
                	}
                    break;
                case "M":
//                	System.out.println("Enter student's first name: ");
//                    String stf = input.next();
//                    System.out.println("Enter student's last name: ");
//                    String stl = input.next();
//                    System.out.println("Enter date (MM/dd/yyyy): ");
//                    String dateString = input.next();
//                    
//                    
//                    app.is_present(stf, stl, dateString, attendanceLog);
//                  // Define regular expressions for expected formats
                	
                	if (attendanceLog.get_count() == 0 || studentRoster.get_count() == 0) {
                        System.out.println("Please load the attendance log data file and roster file first.\n");
                        
                    }
                	
                	else {
	                	String nameRegex1 = "[a-zA-Z]+";
	                	
	                	System.out.println("Enter student's first name: ");
	                	String stf = input.next();
	                	while (!stf.matches(nameRegex1)) {
	                	    System.out.println("Invalid input format. Please enter a valid first name: ");
	                	    stf = input.next();
	                	}
	
	                	System.out.println("Enter student's last name: ");
	                	String stl = input.next();
	                	while (!stl.matches(nameRegex1)) {
	                	    System.out.println("Invalid input format. Please enter a valid last name: ");
	                	    stl = input.next();
	                	}
	
	                	System.out.println("Enter date (MM/dd/yyyy): ");
	                	String dateString = "";
	                	while (true) {
	                	    try {
	                	        dateString = input.next();
	                	        String[] dateParts = dateString.split("/");
	                	        int month = Integer.parseInt(dateParts[0]);
	                	        int day = Integer.parseInt(dateParts[1]);
	                	        int year = Integer.parseInt(dateParts[2]);
	                	        if (month >= 1 && month <= 12 && day >= 1 && day <= 31 && year >= 1900 && year <= 2100) {
	                	            break;
	                	        }
	                	    } catch (Exception e) {
	                	        // Do nothing
	                	    }
	                	    System.out.println("Please enter the date in the correct format (MM/dd/yyyy): ");
	                	    input.nextLine();
	                	}
	
	                	app.is_present(stf, stl, dateString, attendanceLog);
                	
                	}

                    break;
                case "N":
//                	System.out.println("Enter timestamp (MM/dd/yyyy): ");
//                    String timestamp3 = input.next();
                	
                	if (attendanceLog.get_count() == 0 || studentRoster.get_count() == 0) {
                        System.out.println("Please load the attendance log data file and roster file first.\n");
                        
                    } 
                	
                	else {
	                	System.out.println("Enter date (MM/dd/yyyy): ");
	                	String timestamp3 = "";
	                	while (true) {
	                	    try {
	                	        timestamp3 = input.next();
	                	        String[] dateParts = timestamp3.split("/");
	                	        int month = Integer.parseInt(dateParts[0]);
	                	        int day = Integer.parseInt(dateParts[1]);
	                	        int year = Integer.parseInt(dateParts[2]);
	                	        if (month >= 1 && month <= 12 && day >= 1 && day <= 31 && year >= 1900 && year <= 2100) {
	                	            break;
	                	        }
	                	    } catch (Exception e) {
	                	        // Do nothing
	                	    }
	                	    System.out.println("Please enter the date in the correct format (MM/dd/yyyy): ");
	                	    input.nextLine();
	                	}
	                	
	                	
	                	
	                    app.list_all_students_checked_in(timestamp3, attendanceLog);
	                    
                	}
                    break;
                case "O":
//                	System.out.println("Enter timestamp before (hh:mm:ss): ");
//                    String timeStampBefore = input.next();
//                    System.out.println("Enter date before (MM/dd/yyyy): ");
//                    String timestamp4 = input.next();
//          
//                    app.list_all_students_checked_in_before(timeStampBefore, timestamp4, attendanceLog);
                	
                	if (attendanceLog.get_count() == 0 || studentRoster.get_count() == 0) {
                        System.out.println("Please load the attendance log data file and roster file first.\n");
                        
                    }
                	
                	else {
                	System.out.println("Enter timestamp before (hh:mm:ss): ");
                	String timeStampBefore = "";
                	while (true) {
                	    try {
                	        timeStampBefore = input.next();
                	        String[] timeParts = timeStampBefore.split(":");
                	        int hours = Integer.parseInt(timeParts[0]);
                	        int minutes = Integer.parseInt(timeParts[1]);
                	        int seconds = Integer.parseInt(timeParts[2]);
                	        if (hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59 && seconds >= 0 && seconds <= 59) {
                	            break;
                	        }
                	    } catch (Exception e) {
                	        // Do nothing
                	    }
                	    System.out.println("Please enter the timestamp in the correct format (hh:mm:ss): ");
                	    input.nextLine();
                	}

                	System.out.println("Enter date before (MM/dd/yyyy): ");
                	String timestamp4 = "";
                	while (true) {
                	    try {
                	        timestamp4 = input.next();
                	        String[] dateParts = timestamp4.split("/");
                	        int month = Integer.parseInt(dateParts[0]);
                	        int day = Integer.parseInt(dateParts[1]);
                	        int year = Integer.parseInt(dateParts[2]);
                	        if (month >= 1 && month <= 12 && day >= 1 && day <= 31 && year >= 1900 && year <= 2100) {
                	            break;
                	        }
                	    } catch (Exception e) {
                	        // Do nothing
                	    }
                	    System.out.println("Please enter the date in the correct format (MM/dd/yyyy): ");
                	    input.nextLine();
                	}

                	app.list_all_students_checked_in_before(timeStampBefore, timestamp4, attendanceLog);
                	}


                	
                    break;
                case "P":
                	
                	if (attendanceLog.get_count() == 0 || studentRoster.get_count() == 0) {
                        System.out.println("Please load the attendance log data file and roster file first.\n");
                        
                    }
                	
                	else {
                		
                	System.out.println("Enter a number of days: ");
                    Integer days = input.nextInt();
                    app.list_students_attendance_count(days, attendanceLog, studentRoster);
                    
                	}
                    break;
                case "Q":
//                	System.out.println("Enter a date (MM/dd/yyyy): ");
//                	String date3 = input.next();
//                	
//                	System.out.println("Enter date before (MM/dd/yyyy): ");
                	
                	if (attendanceLog.get_count() == 0 || studentRoster.get_count() == 0) {
                        System.out.println("Please load the attendance log data file and roster file first.\n");
                        
                    }
                	else {
                	System.out.println("Enter date (MM/dd/yyyy): ");
                	String date3 = "";
                	while (true) {
                	    try {
                	        date3 = input.next();
                	        String[] dateParts = date3.split("/");
                	        int month = Integer.parseInt(dateParts[0]);
                	        int day = Integer.parseInt(dateParts[1]);
                	        int year = Integer.parseInt(dateParts[2]);
                	        if (month >= 1 && month <= 12 && day >= 1 && day <= 31 && year >= 1900 && year <= 2100) {
                	            break;
                	        }
                	    } catch (Exception e) {
                	        // Do nothing
                	    }
                	    System.out.println("Please enter the date in the correct format (MM/dd/yyyy): ");
                	    input.nextLine();
                	}
                	
                    app.get_first_student_to_enter(date3, attendanceLog);
                	}
                	
                    break;
                case "R":
                	if (attendanceLog.get_count() == 0 || studentRoster.get_count() == 0) {
                        System.out.println("Please load the attendance log data file and roster file first.\n");
                        
                    }
                	else {
                		
                		app.print_query_list();
                		
                	}
                	
                    break;
                case "S":
                	if (attendanceLog.get_count() == 0 || studentRoster.get_count() == 0) {
                        System.out.println("Please load the attendance log data file and roster file first.\n");
                        
                    }
                	else {
                		System.out.println("\n");
                		System.out.println("****************");
                		app.print_count();
                		System.out.println("****************");
                		System.out.println("\n");
                	}
                	
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }

        System.out.println("Exiting program...");
    }
}
