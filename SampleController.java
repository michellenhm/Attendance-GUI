package application;
import javafx.scene.control.*;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//rewrite each function in attendanceapp
public class SampleController {
	private AttendanceLog attendanceLog;
    private StudentRoster studentRoster;
    private AttendanceApp app;
    //public List<String> this_query = new ArrayList<String>();
    private String studentRosterFilename;
    private String attendanceLogFilename;
    
    public String firstNamefield;
    public String lastNamefield;
    public String datefield;
    public String timefield;
    public Integer dayfield;
    
	@FXML public TextField firstName;
	@FXML public TextField lastName;
	@FXML public TextField date;
	@FXML public TextField time;
	@FXML public TextField day;
	@FXML private TextArea outputTextArea;
	@FXML private Label myInfo;
	@FXML private Label whatToDo;
	@FXML private MenuButton menuButton;
	@FXML private Button exitButton;
	
//get input from textfields, put into app.<some function>
	// figure out how to call app constructor
	
	public void initialize() {
        myInfo.setText("Michelle Nguyen, UIN 630002763, michellenguyen6@tamu.edu"); // Replace with your name
        whatToDo.setText("Enter your inputs, in their respective fields, prior to selecting the functions that require them" + "\n" +  "Then select option R to see the results" + "\n" + "For example, get_first_student_to_enter() requires a date. Fill in the date field before clicking the function.");
        firstName.setText("");
        lastName.setText("");
        date.setText("");
        time.setText("");
        day.setText("");
        
        
        AttendanceLog attendanceLog = new AttendanceLog();
        //attendanceLog.loadLog("./src/application/data/data.txt");

        StudentRoster studentRoster = new StudentRoster();
        //studentRoster.loadRoster("./src/application/data/test_roster.txt");

        //AttendanceApp app = new AttendanceApp("", "");
        AttendanceApp app = new AttendanceApp("./src/application/data/rosters.txt", "./src/application/data/dataAllShow1stAnd2ndClass.txt");
        
    }
	@FXML private void handleExitButton() {
	    System.exit(0);
	}
	@FXML public void handleMenuSelection() {
//		firstNamefield = firstName.getText();
//		lastNamefield = lastName.getText();
//		datefield = date.getText();
//		timefield = time.getText();
		
        MenuItem menuItem = menuButton.getItems().get(menuButton.getItems().indexOf(menuButton.getItems().get(0)));
        String letter = menuItem.getText().substring(0, 1);
        switch (letter) {
	        case "A":
	            loadLog();
	            break;
	        case "B":
	        	print_collection_attendancelog();
	            break;
	        case "C":
	        	print_count_attendacelog();
	        	break;
	        case "D":
	            loadRoster();
	            break;
	        case "E":
	            print_collection_roster();
	            break;
	        case "F":
	            print_count_roster();
	            break;
	            
	            
	        case "G":  
	            studentNotInClass();
	            break;
	       
	        case "H":
	            ListAllTimesCheckInOut();
	            break;              
	        case "I":
	        	ListAllTimesCheckIn();
	            break;
	          
	        case "J":
	        	studentsLateToClass();
	            break;
//	            
	        case "K":
	        	firstStudentToEnter();
	            break;
//	
//	        
	        case "L":
	            attendanceData();
	            break;
	        case "M":
	            IsPresent();	            
	            break;
	        case "N":
	            allStudentsCheckedIn();
	            break;
	        case "O":
	        	checkedInBefore();
	            break;
	        case "P":
	        	attendanceCount();
	            break;
	        case "Q":
	        	firstToEnter();
	            break;
	            
	            
	        case "R":
//	        	for(String s : app.get_query()) {
//	        		outputTextArea.setText(s);
//	        	}
	        	printQuery();
	            break;
	        case "S":
	        	printCount();
//	            outputTextArea.setText("Total count: " + app.get_count());
	        	break;
            case "0":
            	exiting();
            	break;
            default:
              System.out.println("Invalid choice, please try again.");
        }
        
		
    }
	
	@FXML public void exiting() {
		outputTextArea.setText("End of program...");
		
	}
	
	
	@FXML public void loadLog() {
	    outputTextArea.setText("");
	    if (attendanceLog == null) {
	        FileChooser fileChooser = new FileChooser();
	        fileChooser.setTitle("Open Attendance Log Data File");
	        File file = fileChooser.showOpenDialog(new Stage());
	        if (file != null) {
	            attendanceLog = new AttendanceLog();
	            attendanceLog.loadLog(file.getAbsolutePath());
	            outputTextArea.setText("Attendance log data file loaded successfully.\n");
	            attendanceLogFilename = file.getAbsolutePath();
	        }
	    }
	    
	}

	@FXML public void loadRoster() {
	    outputTextArea.setText("");
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Open Roster File");
	    File file = fileChooser.showOpenDialog(new Stage());
	    if (file != null) {
	        studentRosterFilename = file.getAbsolutePath();
	        studentRoster = new StudentRoster();
	        studentRoster.loadRoster(studentRosterFilename);
	        outputTextArea.setText("Roster file loaded successfully.\n");
	    }
	    if (attendanceLog != null && studentRoster != null) {
	        app = new AttendanceApp(studentRosterFilename, attendanceLogFilename);
	    }
	}

    @FXML public void print_collection_attendancelog() {
    	if (attendanceLog == null || studentRoster == null) {
            outputTextArea.setText("Please load the attendance log data file and roster file first.\n");
            return;
        }
    	outputTextArea.setText("");
    	for(String s : attendanceLog.get_log_collection()) {
    		outputTextArea.appendText(s+ "\n");
    	}
    }
    @FXML public void print_count_attendacelog() {
    	if (attendanceLog == null || studentRoster == null) {
            outputTextArea.setText("Please load the attendance log data file and roster file first.\n");
            return;
        }
    	outputTextArea.setText("");
    	Integer counting=0;
    	for(String s : attendanceLog.get_log_collection()) {
    		counting +=1;
    	}
    	Integer counting_fixed = counting-1;
    	outputTextArea.appendText("There were "+ counting_fixed + " records for this query");
    }
    @FXML public void print_collection_roster() {
    	if (attendanceLog == null || studentRoster == null) {
            outputTextArea.setText("Please load the attendance log data file and roster file first.\n");
            return;
        }
    	outputTextArea.setText("");
    	for(String s : studentRoster.get_student_collection()) {
    		outputTextArea.appendText(s+ "\n");
    	}
    }
    @FXML public void print_count_roster() {
    	if (attendanceLog == null || studentRoster == null) {
            outputTextArea.setText("Please load the attendance log data file and roster file first.\n");
            return;
        }
    	outputTextArea.setText("");
    	Integer counting=0;
    	for(String s : studentRoster.get_student_collection()) {
    		counting +=1;
    	}
    	//Integer counting_fixed = counting-1;
    	outputTextArea.appendText("There were "+ counting + " records for this query");
    }

    
    
    //attendanceapp functions:
    
    
    @FXML public void studentNotInClass() {
    	if (attendanceLog == null || studentRoster == null) {
            outputTextArea.setText("Please load the attendance log data file and roster file first.\n");
            return;
        }
    	outputTextArea.setText("");
    	app.list_students_not_in_class(attendanceLog.getLogs(), studentRoster.getStudents());
    }
    
    @FXML public void ListAllTimesCheckIn(){
    	if (attendanceLog == null || studentRoster == null) {
            outputTextArea.setText("Please load the attendance log data file and roster file first.\n");
            return;
        }
    	
    	outputTextArea.setText(""); 	
    	app.list_all_times_checked_in(studentRoster, attendanceLog);
    }
    @FXML public void ListAllTimesCheckInOut(){
    	if (attendanceLog == null || studentRoster == null) {
            outputTextArea.setText("Please load the attendance log data file and roster file first.\n");
            return;
        }
    	outputTextArea.setText("");
        firstNamefield = firstName.getText();
        lastNamefield = lastName.getText();
               
    	app.list_all_times_checking_in_and_out(studentRoster, attendanceLog, firstNamefield, lastNamefield);
    }
    @FXML public void studentsLateToClass() {
    	if (attendanceLog == null || studentRoster == null) {
            outputTextArea.setText("Please load the attendance log data file and roster file first.\n");
            return;
        }
    	outputTextArea.setText("");
    	timefield = time.getText();
    	datefield = date.getText();
    	
    	
    	app.list_students_late_to_class(timefield,datefield,attendanceLog); 
    	
    }
    @FXML public void firstStudentToEnter() {
    	if (attendanceLog == null || studentRoster == null) {
            outputTextArea.setText("Please load the attendance log data file and roster file first.\n");
            return;
        }
    	outputTextArea.setText("");
    	datefield = date.getText();
    	app.get_first_student_to_enter(datefield, attendanceLog);
    }
    
    @FXML public void attendanceData() {
    	if (attendanceLog == null || studentRoster == null) {
            outputTextArea.setText("Please load the attendance log data file and roster file first.\n");
            return;
        }
    	outputTextArea.setText("");
    	firstNamefield = firstName.getText();
        lastNamefield = lastName.getText();
    	app.print_attendance_data_for_student(firstNamefield, lastNamefield, attendanceLog);
    }
    @FXML public void IsPresent(){
    	if (attendanceLog == null || studentRoster == null) {
            outputTextArea.setText("Please load the attendance log data file and roster file first.\n");
            return;
        }
    	outputTextArea.setText("");
    	firstNamefield = firstName.getText();
        lastNamefield = lastName.getText();
        datefield = date.getText();
    	app.is_present(firstNamefield, lastNamefield, datefield, attendanceLog);
    }
    @FXML public void allStudentsCheckedIn() {
    	if (attendanceLog == null || studentRoster == null) {
            outputTextArea.setText("Please load the attendance log data file and roster file first.\n");
            return;
        }
    	outputTextArea.setText("");
    	datefield = date.getText();
    	app.list_all_students_checked_in(datefield, attendanceLog);
    }
    @FXML public void checkedInBefore(){
    	if (attendanceLog == null || studentRoster == null) {
            outputTextArea.setText("Please load the attendance log data file and roster file first.\n");
            return;
        }
    	outputTextArea.setText("");
    	timefield = time.getText();
    	datefield = date.getText();
    	app.list_all_students_checked_in_before(timefield, datefield, attendanceLog);
    }
    @FXML public void attendanceCount() { 
    	if (attendanceLog == null || studentRoster == null) {
            outputTextArea.setText("Please load the attendance log data file and roster file first.\n");
            return;
        }
    	outputTextArea.setText("");
    	dayfield = Integer.parseInt(day.getText());
    	app.list_students_attendance_count(dayfield, attendanceLog, studentRoster);
    }
    
    @FXML public void firstToEnter() {
    	if (attendanceLog == null || studentRoster == null) {
            outputTextArea.setText("Please load the attendance log data file and roster file first.\n");
            return;
        }
    	outputTextArea.setText("");
    	datefield = date.getText();
    	app.get_first_student_to_enter(datefield, attendanceLog);
    }
    
    
    @FXML public void printCount() {
    	if (attendanceLog == null || studentRoster == null) {
            outputTextArea.setText("Please load the attendance log data file and roster file first.\n");
            return;
        }
    	outputTextArea.setText("");
    	Integer counting_query = app.get_query().size() -1 ;
       outputTextArea.appendText("There were " + counting_query + " records for this query");
        
    }
    @FXML public void printQuery() {
    	if (attendanceLog == null || studentRoster == null) {
            outputTextArea.setText("Please load the attendance log data file and roster file first.\n");
            return;
        }
    	outputTextArea.setText("");
        for(String s : app.get_query()) {
        	outputTextArea.appendText(s + "\n");
        } 
     }
    
}