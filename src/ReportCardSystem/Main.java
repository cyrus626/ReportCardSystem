package ReportCardSystem;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
public class Main {
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Student Report Card System.
		String choice;
		do {
			intro();
			choice = input.nextLine();
			switch (choice) {
			case "1":
				// Add Student.
				AddStudent();
				
				break;
			case "2":
				Student.DisplayStudent();
				//Student.DisplayStudent();
				break;
			case "3":
				// Display student details when ID is entered.
				String studentId = messagePrompt("Enter student Id: ");
				
				SearchStudentById(studentId.toUpperCase());
				break;
			case "4":
				try {
					PrintToFile(messagePrompt("Enter StudentId: ").toUpperCase());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "5":
				print("Exiting application");
				break;
			default:
				messagePrompt("You've entered incorrect option\nPress enter to continue");
			}
		}while(!choice.equals("5"));
	}
	static void PrintToFile(String searchId) throws FileNotFoundException {
		String filePath = "C:\\Users\\Staff\\Desktop\\Java programs\\FilePractice\\";
		
		if(Student.AllStudents.containsValue(searchId)) {
			String fileContent = "";
			for(String studentName: Student.AllStudents.keySet()) {
				// Get Id, Name
				String studentId = Student.AllStudents.get(studentName);
				if(studentId.equals(searchId.toUpperCase())) {
					/* file content starts here: student's name and Id; 
					*Up next table containing subjectTitle and Score*/
					fileContent = studentName.concat(", ") + studentId 
							+ "\nSubject, Score\n";
					//file name is the name of the student and it would be added to the filePath.
					filePath += studentName.concat(studentId.concat(".csv"));
					double totalScore = 0;
					ArrayList<Subject> studentSubjects = Student.AllSubjects.get(studentId);
					for (Subject subject: studentSubjects) {
						// file content continues subject and score
						fileContent += subject.Name + ", " + subject.Score + "\n";
						totalScore += subject.Score;
					}
					double averageScore = totalScore/studentSubjects.size();
					// file contents continues: total score, average Score and grade
					fileContent += "Total Score," + totalScore 
							+ "\nAverage Scoore," + averageScore
							+ "\nGrade," + Student.Grade(averageScore);;
				}
			}
			try {
				FileWriter writer = new FileWriter(filePath);
				writer.write(fileContent);
				messagePrompt("File saved to :" + filePath);
				writer.close();
			}catch(IOException e) {
				println("Error storing in file");
				e.printStackTrace();
			}
		}else {
			messagePrompt("Record not found");
		}
	}
	
	static void SearchStudentById(String searchId) {
		if(Student.AllStudents.containsValue(searchId)) {
			for(String name: Student.AllStudents.keySet()) {
				String id = Student.AllStudents.get(name);
				if(id.equals(searchId)) {
					print(name.concat(">>\n"));
					Student.DisplayScore(name, id);
					break;
				}
			}
			messagePrompt("Press enter to continue");
		}else {
			messagePrompt("Record not found");
		}
		
	}
	
	static void AddStudent() {
		String name = messagePrompt("Enter your name: ");
		String subjectTitle = messagePrompt("Enter Subject: ");
		double score = Double.parseDouble(messagePrompt("Enter Score: "));
		new Student(name, subjectTitle, score);
	}	
	
	static String messagePrompt(String message) {
		print(message);
		return input.nextLine();
	}
	
	static void intro() {
		println("==========STUDENT REPORT CARD SYSTEM==========");
		println("1. Add Student");
		println("2. Display All Students");
		println("3. Search Student by ID");
		println("4. Save to file");
		println("5. Exit");
		println("==============================================");
		print("Type a number from 1 to 5, representing the \noptions above then press enter to execute\n");
		print("Enter your choice: ");
	}
	
	public static void print(String text) {
		System.out.print(text);
	}
	
	public static void println(String text) {
		System.out.println(text);
	}
}